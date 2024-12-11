import java.util.*;
import java.io.*;

/**
 * b25682
 */
// TODO: next...
public class b25682 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int k;
    static int min;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(min);
    }

    private static void solve() {
        simulate();
    }

    private static void simulate() {
        for (int i = 0; i < n - k + 1; i++) {
            for (int j = 0; j < m - k + 1; j++) {
                verify(i, j);
            }
        }
    }

    private static void verify(int x, int y) {
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                char value = arr[x + i][y + j];
                if ((i + j) % 2 == 0 && value != 'B') {
                    cnt1++;
                }
                if ((i + j) % 2 != 0 && value == 'B') {
                    cnt1++;
                }
                if ((i + j) % 2 == 0 && value == 'W') {
                    cnt2++;
                }
                if ((i + j) % 2 != 0 && value != 'W') {
                    cnt2++;
                }
            }
        }

        int min1 = Math.min(k * k - cnt1, cnt1);
        int min2 = Math.min(k * k - cnt2, cnt2);
        min = Math.min(min, Math.min(min1, min2));
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}