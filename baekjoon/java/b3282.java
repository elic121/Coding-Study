import java.util.*;
import java.io.*;

/**
 * b3282
 */
public class b3282 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int n;
    static int k;
    static int[][] dp;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            init();
            solve(i);
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[110][1010];
        for (int i = 0; i < 110; i++) {
            Arrays.fill(dp[i], -1);
        }

        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[i][0] = v;
            arr[i][1] = c;
        }
    }

    private static void solve(int t) {
        System.out.println("#" + t + " " + recur(0, 0));
    }

    private static int recur(int cur, int w) {
        if (w > k) {
            return -10000000;
        }

        if (cur == n) {
            return 0;
        }

        if (dp[cur][w] != -1) {
            return dp[cur][w];
        }

        dp[cur][w] = Math.max(recur(cur + 1, w), recur(cur + 1, w + arr[cur][0]) + arr[cur][1]);
        return dp[cur][w];
    }
}