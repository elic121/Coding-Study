import java.util.*;
import java.io.*;

public class b22352 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int change;
    static int[][] arr;
    static int[][] res;
    static boolean isPossible;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(isPossible ? "YES" : "NO");
    }

    private static void solve() {
        simulate();
    }

    private static void simulate() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    continue;
                }

                System.out.println(i + " " + j);
                if (!bfs(i, j)) {
                    isPossible = false;
                    return;
                }
            }
        }
    }

    private static boolean bfs(int x, int y) {
        int pivot = arr[x][y];

        visited[x][y] = true;

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        ArrayDeque<int[]> temp = new ArrayDeque<>();
        temp.add(new int[] { x, y });

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

                if (arr[nx][ny] != pivot) {
                    continue;
                }

                dq.add(new int[] { nx, ny });
                temp.add(new int[] { nx, ny });
                visited[nx][ny] = true;
            }
        }

        int[] p = temp.pop();
        int comp = res[p[0]][p[1]];

        while (!temp.isEmpty()) {
            p = temp.pop();

            if (res[p[0]][p[1]] != comp) {
                return false;
            }
        }

        change += (pivot != comp ? 1 : 0);
        return change < 2;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        change = 0;
        isPossible = true;

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                res[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];
    }
}
