import java.util.*;
import java.io.*;

public class b2234 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 0, -1, 0, 1 };
    static final int[] dy = { -1, 0, 1, 0 };
    static int n;
    static int m;
    static int cnt;
    static int max;
    static int elemMax;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    max = Math.max(max, bfs(i, j));
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 4; k++) {
                    if ((arr[i][j] & 1 << k) != 0) {
                        visited = new boolean[n][m];
                        arr[i][j] ^= 1 << k;
                        elemMax = Math.max(elemMax, bfs(i, j));
                        arr[i][j] ^= 1 << k;
                    }
                }
            }
        }

        System.out.println(elemMax);
    }

    private static int bfs(int x, int y) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        visited[x][y] = true;
        int room = 1;
        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            int b = arr[p[0]][p[1]];
            for (int i = 0; i < 4; i++) {
                int nx = p[0] + dx[i];
                int ny = p[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if ((b & 1 << i) != 0) {
                    continue;
                }
                dq.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                room++;
            }
        }
        return room;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        cnt = 0;
        max = 0;
        elemMax = 0;
        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
