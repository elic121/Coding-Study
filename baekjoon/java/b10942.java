import java.util.*;
import java.io.*;

public class b10942 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] arr;
    static int[][] prob;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(calc(prob[i][0], prob[i][1]) == 1 ? 1 : 0).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int calc(int s, int e) {
        if (dp[s][e] != -1) {
            return dp[s][e];
        }

        return dp[s][e] = (arr[s] == arr[e] && calc(s + 1, e - 1) == 1) ? 1 : 0;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        prob = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            prob[i][0] = Integer.parseInt(st.nextToken());
            prob[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            dp[i][i + 1] = arr[i] == arr[i + 1] ? 1 : 0;
        }
    }
}
