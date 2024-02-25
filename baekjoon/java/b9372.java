import java.io.*;
import java.util.*;

public class b9372 {

  static int T, N, M, cnt;
  static ArrayList<Integer>[] arr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      arr = new ArrayList[N];
      cnt = 0;

      for (int j = 0; j < N; j++) {
        arr[j] = new ArrayList<>();
      }

      for (int j = 0; j < M; j++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        arr[a].add(b);
        arr[b].add(a);
      }
      solution();
    }
  }

  private static void solution() {
    bfs();
    System.out.println(cnt);
  }

  private static void bfs() {
    Deque<Integer> que = new ArrayDeque<>();
    boolean[] check = new boolean[N];

    que.add(0);
    check[0] = true;

    while (!que.isEmpty()) {
      int p = que.remove();
      for (int v : arr[p]) {
        if (check[v]) continue;
        check[v] = true;
        cnt++;
        que.add(v);
      }
    }
  }
}
