import java.util.*;
import java.io.*;

public class b2342 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] arr;
    static int[] integral;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int stt = 1;
        int end = 1_000_000_000;
        int mid = 0;

        while (stt < end) {
            mid = (stt + end) >> 1;
            if (isValid(mid)) {
                end = mid - 1;
            } else {
                stt = mid + 1;
            }
        }

        if (end < mid) {
            System.out.println(end + (isValid(end) ? 0 : 1));
        } else {
            System.out.println(stt + (isValid(stt) ? 0 : 1));
        }
    }

    private static boolean isValid(int k) {
        int stt = 1;

        for (int i = 0; i < m; i++) {
            int pivot = stt - 1;
            while (stt <= n && (integral[stt] - integral[pivot] <= k)) {
                stt++;
            }

            if (stt == n + 1) {
                return true;
            }
        }

        return false;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        integral = new int[n + 1];
        integral[1] = arr[0];
        for (int i = 2; i <= n; i++) {
            integral[i] = integral[i - 1] + arr[i - 1];
        }
    }
}
