import java.util.*;
import java.io.*;

public class b17244 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int k;
    static int sx;
    static int sy;
    static char[][] arr;
    static boolean[][][] visited;
    static ArrayDeque<int[]> dq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        dq.add(new int[] { sx, sy, 0, 0 });
        visited[sx][sy][0] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (arr[nx][ny] == '#') {
                    continue;
                }

                if (arr[nx][ny] == 'E' && p[2] != (1 << k) - 1) {
                    continue;
                }

                if (arr[nx][ny] == 'E' && p[2] == (1 << k) - 1) {
                    System.out.println(p[3] + 1);
                    return;
                }

                int next = (arr[nx][ny] != '.') ? p[2] | 1 << arr[nx][ny] : p[2];

                if (visited[nx][ny][next]) {
                    continue;
                }

                visited[nx][ny][next] = true;
                dq.add(new int[] { nx, ny, next, p[3] + 1 });
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = 0;

        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'S') {
                    sx = i;
                    sy = j;
                    arr[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'X') {
                    arr[i][j] = (char) k++;
                }
            }
        }

        visited = new boolean[n][m][1 << k];
        dq = new ArrayDeque<>();
    }
}