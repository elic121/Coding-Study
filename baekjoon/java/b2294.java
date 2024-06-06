import java.util.*;
import java.io.*;

public class b2294 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int INF = 100_001;
    static int n;
    static int k;
    static int[] coins;
    static int[] value;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                value[j] = Math.min(value[j], value[j - coins[i]] + 1);
            }
        }

        System.out.println(value[k] == INF ? -1 : value[k]);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        value = new int[k + 1];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= k; i++) {
            value[i] = INF;
        }
    }
}