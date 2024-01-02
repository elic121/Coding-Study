import java.io.*;
import java.util.*;

public class b4193 {

  static int[][] P = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
  static int[][] arr;
  static int SOL, N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc < T + 1; tc++) {
      N = Integer.parseInt(br.readLine());
      SOL = N * N;
      arr = new int[N][N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      StringTokenizer st1 = new StringTokenizer(br.readLine());
      int sx = Integer.parseInt(st1.nextToken());
      int sy = Integer.parseInt(st1.nextToken());

      StringTokenizer st2 = new StringTokenizer(br.readLine());
      int ex = Integer.parseInt(st2.nextToken());
      int ey = Integer.parseInt(st2.nextToken());

      bfs(sx, sy, ex, ey);
      if (SOL == N * N) {
        System.out.printf("#%d %d\n", tc, -1);
      } else {
        System.out.printf("#%d %d\n", tc, SOL);
      }
    }
  }

  private static void bfs(int sx, int sy, int ex, int ey) {
    Deque<int[]> d = new ArrayDeque<>();
    int[][] visited = new int[N][N];
    d.add(new int[] { sx, sy, 0 });
    visited[sx][sy] = 1;

    while (!d.isEmpty()) {
      int[] pop = d.removeFirst();
      int x = pop[0];
      int y = pop[1];
      int sec = pop[2];

      if (x == ex && y == ey) {
        SOL = sec;
        break;
      }

      for (int[] p : P) {
        int dx = x + p[0];
        int dy = y + p[1];

        if (dx < 0 || dx >= N || dy < 0 || dy >= N) continue;
        if (visited[dx][dy] == 1 || arr[dx][dy] == 1) continue;

        if (arr[dx][dy] == 0 || (arr[dx][dy] == 2 && (sec % 3 == 2))) {
          visited[dx][dy] = 1;
          d.add(new int[] { dx, dy, sec + 1 });
        } else {
          visited[x][y] = 1;
          d.add(new int[] { x, y, sec + 1 });
        }
      }
    }
  }
}
