import java.io.*;
import java.util.*;

public class b8972 {

  static int R, C, x, y;
  static char[][] arr;
  static StringTokenizer st;
  static int[] dx = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
  static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    arr = new char[R][C];
    for (int i = 0; i < R; i++) {
      char[] c = br.readLine().toCharArray();
      for (int j = 0; j < C; j++) {
        arr[i][j] = c[j];
        if (c[j] == 'I') {
          x = i;
          y = j;
        }
      }
    }

    char[] command = br.readLine().toCharArray();
    boolean success = true;
    for (int i = 1, size = command.length; i < size + 1; i++) {
      if (!moveJongsoo(command[i - 1] - '0')) {
        System.out.printf("kraj %d\n", i);
        success = false;
        break;
      }
      if (!moveRobot()) {
        System.out.printf("kraj %d\n", i);
        success = false;
        break;
      }
    }

    if (success) {
      for (int i = 0; i < R; i++) {
        StringBuilder sb = new StringBuilder();
        for (char c : arr[i]) {
          sb.append(c);
        }
        System.out.println(sb.toString());
      }
    }
  }

  private static boolean moveJongsoo(int p) {
    int nx = x + dx[p - 1];
    int ny = y + dy[p - 1];

    if (arr[nx][ny] == 'R') {
      return false;
    } else {
      arr[x][y] = '.';
      x = nx;
      y = ny;
      arr[x][y] = 'I';
      return true;
    }
  }

  private static boolean moveRobot() {
    int[][] visited = new int[R][C];
    char[][] tmp = new char[R][C];
    for (int i = 0; i < R; i++) {
      Arrays.fill(tmp[i], '.');
    }
    tmp[x][y] = 'I';

    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (arr[i][j] == 'R') {
          int dist = 201;
          int goX = 0;
          int goY = 0;
          for (int idx = 0; idx < 9; idx++) {
            if (idx == 4) continue;
            int nx = i + dx[idx];
            int ny = j + dy[idx];
            if (!isPossible(nx, ny)) continue;
            int d = distance(nx, ny);
            if (dist > d) {
              dist = d;
              goX = nx;
              goY = ny;
            }
          }
          if (arr[goX][goY] == 'I') return false;
          visited[goX][goY] += 1;
          tmp[goX][goY] = 'R';
        }
      }
    }
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (visited[i][j] >= 2) {
          tmp[i][j] = '.';
        }
      }
    }
    arr = tmp;
    return true;
  }

  private static boolean isPossible(int x, int y) {
    return 0 <= x && x < R && 0 <= y && y < C;
  }

  private static int distance(int x2, int y2) {
    return Math.abs(x - x2) + Math.abs(y - y2);
  }
}
