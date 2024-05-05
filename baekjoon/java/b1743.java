import java.util.*;
import java.io.*;

public class b1743 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int k;
    static int max;
    static boolean[][] visited;
    static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && arr[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(max);
    }

    private static void bfs(int i, int j) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { i, j });

        visited[i][j] = true;
        int cnt = 1;
        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (!arr[nx][ny]) {
                    continue;
                }

                dq.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                cnt++;
            }
        }
        max = Math.max(max, cnt);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        max = 0;

        visited = new boolean[n][m];
        arr = new boolean[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a - 1][b - 1] = true;
        }
    }
}
