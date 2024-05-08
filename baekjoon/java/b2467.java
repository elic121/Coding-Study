import java.util.*;
import java.io.*;

public class b2467 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int minDiff;
    static int minS;
    static int minE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        // int margin = findMargin();

        // if (margin >= n) {
        // System.out.println(arr[n - 2] + " " + arr[n - 1]);
        // return;
        // }

        // if (margin <= 1) {
        // System.out.println(arr[0] + " " + arr[1]);
        // return;
        // }

        int stt = 0;
        int end = n - 1;
        while (stt < end) {
            int diff = arr[stt] + arr[end];
            if (diff == 0) {
                System.out.println(arr[stt] + " " + arr[end]);
                return;
            }

            if (minDiff > Math.abs(diff)) {
                minDiff = Math.abs(diff);
                minS = stt;
                minE = end;
            }

            if (diff > 0) {
                end--;
            } else if (diff < 0) {
                stt++;
            }
        }

        System.out.println(arr[minS] + " " + arr[minE]);
    }

    private static int findMargin() {
        int stt = 0;
        int end = n - 1;
        while (stt < end) {
            int mid = (stt + end) / 2;

            if (arr[mid] > 0) {
                end = mid - 1;
            } else {
                stt = mid + 1;
            }
        }
        return stt + (arr[stt] < 0 ? 1 : 0);
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        minDiff = Integer.MAX_VALUE;
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
