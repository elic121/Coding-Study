import java.util.*;
import java.io.*;

public class b17836 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int t;
    static int find;
    static int[][] arr;
    static boolean[][][] visited;
    static ArrayDeque<int[]> dq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(find == -1 ? "Fail" : find);
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        dq = new ArrayDeque<>();
        dq.add(new int[] { 0, 0, 0, 0 });

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == n - 1 && p[1] == m - 1) {
                find = p[3];
                return;
            }

            if (p[3] > t) {
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (visited[nx][ny][p[2]]) {
                    continue;
                }

                if (p[2] == 0 && arr[nx][ny] == 1) {
                    continue;
                }

                int sword = (p[2] == 1 || arr[nx][ny] == 2) ? 1 : 0;

                dq.add(new int[] { nx, ny, sword, p[3] + 1 });
                visited[nx][ny][sword] = true;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        find = -1;

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
