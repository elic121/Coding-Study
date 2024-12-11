import java.util.*;
import java.io.*;

/**
 * b1080
 */
public class b1080 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int cnt;
    static char[][] a;
    static char[][] b;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        if ((n < 3 || m < 3) || isSame()) {
            System.out.println(isSame() ? 0 : -1);
            return;
        }

        System.out.println(simulate() ? cnt : -1);
    }

    private static boolean simulate() {
        for (int i = 0; i < n - 3 + 1; i++) {
            for (int j = 0; j < m - 3 + 1; j++) {
                if (a[i][j] != b[i][j]) {
                    reverse(i, j);
                    cnt++;
                }
                if (isSame()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void reverse(int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[x + i][y + j] = a[x + i][y + j] == '0' ? '1' : '0';
            }
        }
    }

    private static boolean isSame() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cnt = 0;

        a = new char[n][m];
        b = new char[n][m];

        for (int i = 0; i < n; i++) {
            a[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            b[i] = br.readLine().toCharArray();
        }
    }
}