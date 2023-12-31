import java.io.*;
import java.util.*;

public class b7465 {

  static int[] parent;

  private static int find(int x) {
    if (x == parent[x]) {
      return x;
    } else {
      return parent[x] = find(parent[x]);
    }
  }

  private static void union(int a, int b) {
    int p1 = find(a);
    int p2 = find(b);

    if (p1 == p2) {
      return;
    } else if (p1 > p2) {
      parent[p1] = p2;
    } else {
      parent[p2] = p1;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 0 + 1; i < tc + 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      parent = new int[N + 1];
      for (int j = 1; j < N + 1; j++) {
        parent[j] = j;
      }

      for (int j = 0; j < M; j++) {
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st1.nextToken());
        int b = Integer.parseInt(st1.nextToken());
        union(a, b);
      }

      int cnt = 0;
      for (int j = 1; j < N + 1; j++) {
        if (j == find(j)) {
          cnt++;
        }
      }
      System.out.printf("#%d %d\n", i, cnt);
    }
  }
}
