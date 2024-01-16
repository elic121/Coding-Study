import java.io.*;
import java.util.*;

class Point {

  int x, y, cnt;

  Point(int x, int y, int cnt) {
    this.x = x;
    this.y = y;
    this.cnt = cnt;
  }
}

public class b2178 {

  static int N, M;
  static int[] dx = {1,0,-1,0};
  static int[] dy = {0,1,0,-1};
  static boolean arr[][], visited[][];
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new boolean[N][M];
    visited = new boolean[N][M];
    for (int i = 0; i < N; i++) {
      char[] ch = br.readLine().toCharArray();
      for (int j = 0; j < M; j++) {
        if (ch[j] - '0' == 1) {
          arr[i][j] = true;
        } else {
          arr[i][j] = false;
        }
      }
    }
    bfs();
  }

  private static void bfs() {
    Deque<Point> q = new ArrayDeque<>();
    visited[0][0] = true;
    q.add(new Point(0, 0, 1));

    while (!q.isEmpty()) {
        Point p = q.remove();
        int x = p.x;
        int y = p.y;
        int cnt = p.cnt;
        if (x == N-1 && y == M-1) {
            System.out.println(cnt);
            return;
        }
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx<0 || nx>=N) continue;
            if (ny<0 || ny>=M) continue;
            if (!arr[nx][ny]) continue;
            if (visited[nx][ny]) continue;
            visited[nx][ny] = true;
            q.add(new Point(nx, ny, cnt+1));
        }
    }
  }
}
