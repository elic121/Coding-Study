import java.util.*;
import java.io.*;

/**
 * b2212
 */
public class b2212 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int min;
    static int[] sensors;
    static int[] diff;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(min);
    }

    private static void solve() {
        Arrays.sort(diff);

        if (k >= n) {
            min = 0;
            return;
        }

        int sum = 0;
        for (int i = 0; i < n - k; i++) {
            sum += diff[i];
        }

        min = sum;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;

        sensors = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensors);

        diff = new int[n - 1];
        for (int i = 1; i < n; i++) {
            diff[i - 1] = sensors[i] - sensors[i - 1];
        }
    }
}