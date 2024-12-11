import java.util.*;
import java.io.*;

public class b5557 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int ans;
    static int cnt;
    static int[] arr;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(dp[n - 1][ans]);
    }

    private static void solve() {
        calc();
    }

    private static void calc() {
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                int value1 = j + arr[i];
                int value2 = j - arr[i];

                if (value1 <= 20 && value1 >= 0) {
                    dp[i + 1][value1] += dp[i][j];
                }

                if (value2 <= 20 && value2 >= 0) {
                    dp[i + 1][value2] += dp[i][j];
                }
            }
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        cnt = 0;
        arr = new int[n - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ans = Integer.parseInt(st.nextToken());

        dp = new long[n][21];
        dp[1][arr[1 - 1]] = 1;
    }
}