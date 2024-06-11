import java.util.*;
import java.io.*;

public class b1309 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1] * 2) % 9901;
        }
        System.out.println(dp[n]);
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());

        dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 3;
    }
}