import java.io.*;
import java.util.*;

public class b4577 {

  static int R, C;
  static int x, y;
  static int num, GOAL;
  static char[][] arr;
  static StringTokenizer st;
  static BufferedReader br;

  public static void main(String[] args) throws IOException {
    num = 1;
    br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      st = new StringTokenizer(br.readLine());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());

      if (R == 0 && C == 0) break;

      arr = new char[R][C];
      GOAL = 0;
      for (int i = 0; i < R; i++) {
        char[] a = br.readLine().toCharArray();
        for (int j = 0; j < C; j++) {
          char var = a[j];
          arr[i][j] = var;
          if (var == 'w' || var == 'W') {
            x = i;
            y = j;
            if (var == 'W') GOAL++;
          } else if (var == 'B' || var == '+') {
            GOAL++;
          }
        }
      }

      String scr = "incomplete";
      char[] command = br.readLine().toCharArray();
      for (char c : command) {
        move(c);
        if (check()) {
          scr = "complete";
          break;
        }
      }

      System.out.printf("Game %d: %s\n", num, scr);
      for (int i = 0; i < R; i++) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < C; j++) {
          sb.append(arr[i][j]);
        }
        System.out.println(sb.toString());
      }

      num++;
    }
    br.close();
  }

  private static void move(char c) {
    int[] d = whereToGO(c);
    int dx = d[0];
    int dy = d[1];

    int nx = x + dx;
    int ny = y + dy;

    if (!isPossible(nx, ny)) return;
    if (arr[nx][ny] == '#') return;

    if (arr[nx][ny] == 'b' || arr[nx][ny] == 'B') {
      int bx = nx + dx;
      int by = ny + dy;
      if (!isPossible(bx, by)) return;
      if (arr[bx][by] == '#') return;
      if (arr[bx][by] == 'b' || arr[bx][by] == 'B') return;

      arr[bx][by] = arr[bx][by] == '+' ? 'B' : 'b';
      arr[nx][ny] = arr[nx][ny] == 'b' ? 'w' : 'W';
    } else {
      arr[nx][ny] = arr[nx][ny] == '+' ? 'W' : 'w';
    }
    arr[x][y] = arr[x][y] == 'W' ? '+' : '.';
    x = nx;
    y = ny;
  }

  private static boolean isPossible(int x, int y) {
    return 0 <= x && x < R && y >= 0 && y < C;
  }

  private static int[] whereToGO(char c) {
    if (c == 'U') {
      return new int[] { -1, 0 };
    } else if (c == 'L') {
      return new int[] { 0, -1 };
    } else if (c == 'R') {
      return new int[] { 0, 1 };
    } else {
      return new int[] { 1, 0 };
    }
  }

  private static boolean check() {
    int CNT = 0;
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (arr[i][j] == '+') return false;
        if (arr[i][j] == 'W') return false;
        if (arr[i][j] == 'B') CNT++;
      }
    }
    return CNT == GOAL;
  }
}