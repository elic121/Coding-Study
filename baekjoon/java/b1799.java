import java.util.*;
import java.io.*;

public class b1799 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { -1, -1, 1, 1 };
    static final int[] dy = { -1, 1, -1, 1 };
    static int n;
    static int max1;
    static int max2;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        dfs(0, 0);
        dfs(1, 0);
        System.out.println(max1 + max2);
    }

    private static void dfs(int x, int count) {
        if (x % 2 == 0) {
            max1 = Math.max(max1, count);
        } else {
            max2 = Math.max(max2, count);
        }

        if (x == n * n) {
            return;
        }

        for (int i = x; i < n * n; i += 2) {
            int nx = i / n;
            int ny = (i % n) + ((n % 2 == 0 && nx % 2 != 0) ? (x % 2 == 0 ? 1 : -1) : 0);

            if (isValid(nx, ny)) {
                arr[nx][ny] = 2;
                dfs(i + 2, count + 1);
                arr[nx][ny] = 1;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        if (arr[x][y] != 1) {
            return false;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            while (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (arr[nx][ny] == 2) {
                    return false;
                }
                nx += dx[d];
                ny += dy[d];
            }
        }
        return true;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        max1 = 0;
        max2 = 0;
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}