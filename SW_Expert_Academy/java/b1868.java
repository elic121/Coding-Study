import java.io.*;
import java.util.*;

public class b1868 {

  static int size, sol, cnt[][];
  static char[][] arr;
  static int[][] p = {
    { 0, 1 },
    { 0, -1 },
    { -1, -1 },
    { -1, 0 },
    { -1, 1 },
    { 1, -1 },
    { 1, 0 },
    { 1, 1 },
  };

  private static void bfs(int i, int j) {
    Deque<int[]> d = new ArrayDeque<>();
    d.add(new int[] { i, j });
    cnt[i][j] = -1;

    while (!d.isEmpty()) {
      int[] val = d.removeFirst();
      int x = val[0];
      int y = val[1];
      for (int[] q : p) {
        int dx = x + q[0];
        int dy = y + q[1];
        if (dx < 0 || dx >= size) continue;
        if (dy < 0 || dy >= size) continue;
        if (cnt[dx][dy] == -1) continue;
        if (cnt[dx][dy] == 0) {
          d.add(new int[] { dx, dy });
        }
        cnt[dx][dy] = -1;
      }
    }
    return;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 1; i < tc + 1; i++) {
      size = Integer.parseInt(br.readLine());
      arr = new char[size][size];

      for (int j = 0; j < size; j++) {
        String str = br.readLine();
        arr[j] = str.toCharArray();
      }

      cnt = new int[size][size];
      for (int j = 0; j < size; j++) {
        for (int k = 0; k < size; k++) {
          if (arr[j][k] == '*') {
            cnt[j][k] = -1;
            continue;
          }
          int c = 0;
          for (int[] d : p) {
            int dx = j + d[0];
            int dy = k + d[1];
            if (dx < 0 || dx >= size) continue;
            if (dy < 0 || dy >= size) continue;
            if (arr[dx][dy] == '*') c++;
          }
          cnt[j][k] = c;
        }
      }

      sol = 0;
      for (int j = 0; j < size; j++) {
        for (int k = 0; k < size; k++) {
          if (cnt[j][k] == 0) {
            bfs(j, k);
            sol++;
          }
        }
      }

      for (int j = 0; j < size; j++) {
        for (int k = 0; k < size; k++) {
          if (cnt[j][k] > 0) sol++;
        }
      }

      System.out.printf("#%d %d\n", i, sol);
    }
  }
}
