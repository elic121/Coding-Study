import java.util.*;
import java.io.*;

public class b14716 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static final int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int n;
    static int m;
    static int cnt;
    static boolean[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] && !visited[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }
        System.out.println(cnt);
    }

    private static void bfs(int x, int y) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        visited[x][y] = true;
        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            for (int d = 0; d < 8; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (!arr[nx][ny] || visited[nx][ny]) {
                    continue;
                }
                dq.add(new int[] { nx, ny });
                visited[nx][ny] = true;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cnt = 0;

        arr = new boolean[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = st.nextToken().equals("1");
            }
        }
    }
}
