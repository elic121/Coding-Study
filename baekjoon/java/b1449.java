import java.util.*;
import java.io.*;

public class b1449 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int l;
    static int cnt;
    static double margin;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(cnt);
    }

    private static void solve() {
        for (int i = 1; i < n; i++) {
            int pos = arr[i];

            if (pos + 0.5 <= margin) {
                continue;
            }

            cnt++;
            margin = pos - 0.5 + l;
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        cnt = 1;

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        margin = arr[0] - 0.5 + l;
    }

}