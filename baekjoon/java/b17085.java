import java.util.*;
import java.io.*;

public class b17085 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int max;
    static int max1;
    static int max2;
    static boolean[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!arr[i][j]) {
                    continue;
                }

                for (int s = 0; s < 8; s++) {
                    if (!isPossible(i, j, s)) {
                        break;
                    }

                    max1 = 4 * s + 1;
                    solve2();
                    clean(i, j, s);
                }
            }
        }
    }

    private static void solve2() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!arr[i][j] || visited[i][j]) {
                    continue;
                }

                for (int s = 0; s < 8; s++) {
                    if (!isPossible(i, j, s)) {
                        break;
                    }

                    max2 = 4 * s + 1;
                    max = Math.max(max, max1 * max2);
                    clean(i, j, s);
                }
            }
        }
    }

    private static boolean isPossible(int x, int y, int s) {
        for (int i = 1; i <= s; i++) {
            for (int d = 0; d < 4; d++) {
                int nx = x + i * dx[d];
                int ny = y + i * dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    return false;
                }

                if (!arr[nx][ny]) {
                    return false;
                }

                if (visited[nx][ny]) {
                    return false;
                }
            }
        }

        for (int i = 1; i <= s; i++) {
            for (int d = 0; d < 4; d++) {
                int nx = x + i * dx[d];
                int ny = y + i * dy[d];

                visited[nx][ny] = true;
            }
        }

        return visited[x][y] = true;
    }

    private static void print() {
        System.out.println(max);
    }

    private static void clean(int x, int y, int s) {
        for (int i = 1; i <= s; i++) {
            for (int d = 0; d < 4; d++) {
                int nx = x + i * dx[d];
                int ny = y + i * dy[d];

                visited[nx][ny] = false;
            }
        }
        visited[x][y] = false;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        max = 0;
        max1 = 0;
        max2 = 0;

        visited = new boolean[n][m];
        arr = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = ch[j] == '#';
            }
        }
    }
}
