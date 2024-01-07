import java.io.*;
import java.util.*;

public class b1248 {

  static int T, V, E, v1, v2;
  static List<Integer>[] arr;
  static int[] cnt, P;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());
    for (int tc = 0; tc < T; tc++) {
      StringTokenizer init = new StringTokenizer(br.readLine());
      V = Integer.parseInt(init.nextToken());
      E = Integer.parseInt(init.nextToken());
      v1 = Integer.parseInt(init.nextToken());
      v2 = Integer.parseInt(init.nextToken());
      cnt = new int[V + 1];
      P = new int[V + 1];
      arr = new ArrayList[V + 1];

      for (int i = 1; i <= V; i++) {
        arr[i] = new ArrayList<>();
      }

      StringTokenizer num = new StringTokenizer(br.readLine());
      for (int e = 0; e < E; e++) {
        int parent = Integer.parseInt(num.nextToken());
        int child = Integer.parseInt(num.nextToken());
        arr[parent].add(child);
      }

      depth(0, 1);
      int LCA = lca(v1, v2);
      int SOL = count(LCA);

      System.out.printf("#%d %d %d\n", tc + 1, LCA, SOL);
    }
  }

  private static int count(int x) {
    int c = 1;
    for (int val : arr[x]) {
      c += count(val);
    }
    return c;
  }

  private static void depth(int n, int node) {
    cnt[node] = n;
    for (int val : arr[node]) {
      P[val] = node;
      depth(n + 1, val);
    }
  }

  private static int lca(int x, int y) {
    while (cnt[x] != cnt[y]) {
      if (cnt[x] > cnt[y]) {
        x = P[x];
      } else {
        y = P[y];
      }
    }
    while (x != y) {
      x = P[x];
      y = P[y];
    }
    return x;
  }
}
