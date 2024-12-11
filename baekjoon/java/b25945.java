import java.util.*;
import java.io.*;

public class b25945 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int sum;
    static int cnt;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int min = sum / arr.length;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > min + 1) {
                max += arr[i] - min - 1;
            }
            if (arr[i] < min) {
                cnt += min - arr[i];
            }
        }

        System.out.println(Math.max(max, cnt));
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        cnt = 0;

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
    }
}