import java.util.*;
import java.io.*;

public class b1937 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static int n;
    static int max;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(max);
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                simulate(i, j);
            }
        }
    }

    private static void simulate(int x, int y) {
        max = Math.max(max, calc(x, y));
    }

    private static int calc(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }

            if (arr[x][y] >= arr[nx][ny]) {
                continue;
            }

            dp[x][y] = Math.max(dp[x][y], calc(nx, ny) + 1);
        }

        return dp[x][y];
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n];
        max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
