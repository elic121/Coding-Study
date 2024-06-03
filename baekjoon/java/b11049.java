import java.util.*;
import java.io.*;

/**
 * b11049
 */
public class b11049 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        System.out.println(dc(0, n - 1));
    }

    private static int dc(int a, int b) {
        if (a == b) {
            return 0;
        }

        if (dp[a][b] != Integer.MAX_VALUE) {
            return dp[a][b];
        }

        for (int k = a; k < b; k++) {
            dp[a][b] = Math.min(dp[a][b], dc(a, k) + dc(k + 1, b) + product(a, b, k));
        }

        return dp[a][b];
    }

    private static int product(int a, int b, int k) {
        return matrix[a][0] * matrix[k][1] * matrix[b][1];
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][2];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}