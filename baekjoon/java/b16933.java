import java.util.*;
import java.io.*;

public class b16933 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int k;
    static boolean[][] arr;
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { 1, 1, 0, 0 });

        visited[1][1][0][0] = true;
        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            int x = p[0];
            int y = p[1];
            int iter = p[2];
            int cnt = p[3];

            if (x == n && y == m) {
                System.out.println(cnt + 1);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                int nc = cnt + 1;

                if (nx <= 0 || nx > n || ny <= 0 || ny > m) {
                    continue;
                }

                boolean isBrighten = cnt % 2 == 0;
                int next = isBrighten ? 1 : 0;
                if (isBrighten) {
                    if (arr[nx][ny] && iter + 1 <= k && !visited[nx][ny][iter + 1][next]) {
                        dq.add(new int[] { nx, ny, iter + 1, nc });
                        visited[nx][ny][iter + 1][next] = true;

                    } else if (!arr[nx][ny] && !visited[nx][ny][iter][next]) {
                        dq.add(new int[] { nx, ny, iter, nc });
                        visited[nx][ny][iter][next] = true;
                    }
                } else {
                    if (arr[nx][ny] && !visited[x][y][iter][next]) {
                        dq.add(new int[] { x, y, iter, nc });
                        visited[x][y][iter][next] = true;

                    } else if (!arr[nx][ny] && !visited[nx][ny][iter][next]) {
                        dq.add(new int[] { nx, ny, iter, nc });
                        visited[nx][ny][iter][next] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1][m + 1][k + 1][2];
        arr = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = c[j - 1] == '1';
            }
        }
    }
}
