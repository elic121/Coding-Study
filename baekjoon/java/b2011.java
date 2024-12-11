import java.util.*;
import java.io.*;

/**
 * b2011
 */
public class b2011 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int div = 1000000;
    static char[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        if (arr[0] == '0') {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= arr.length; i++) {
            if (i == 1) {
                dp[1] = dp[0];
                continue;
            }

            int value = 0;

            if (arr[i - 1] != '0') {
                value += dp[i - 1] % div;
            }

            if (arr[i - 2] != '0') {
                int num = Character.getNumericValue(arr[i - 2]) * 10 + Character.getNumericValue(arr[i - 1]);
                if (num <= 26 && num >= 0) {
                    value += dp[i - 2] % div;
                }
            }

            dp[i] += value;
            dp[i] %= div;
        }

        System.out.println(dp[arr.length] % div);
    }

    private static void init() throws IOException {
        arr = br.readLine().toCharArray();
        dp = new int[arr.length + 1];
        dp[0] = 1;
    }
}