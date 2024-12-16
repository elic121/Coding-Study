import java.util.*;
import java.io.*;

public class b13301 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        if (n == 1) {
            System.out.println(4);
        } else if (n == 2) {
            System.out.println(6);
        } else if (n == 3) {
            System.out.println(10);
        } else {
            long ans = arr[n - 1] * 3 + arr[n - 2] * 2 + arr[n - 3] * 2 + arr[n - 4];
            System.out.println(ans);
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new long[n + 1];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
    }
}