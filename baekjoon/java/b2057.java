import java.io.*;

public class b2057 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long n;
    static long[] facs;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        if (n == 0) {
            System.out.println("NO");
            return;
        }

        int end = getFactorial(n);

        long num = n;
        for (int i = end; i >= 0; i--) {
            if (num >= facs[i]) {
                num -= facs[i];
            } else {
                continue;
            }

            if (num == 0) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }

    private static int getFactorial(long n) {
        facs[0] = 1;

        for (int i = 1; i < 21; i++) {
            facs[i] = facs[i - 1] * (long) i;

            if (facs[i] > n) {
                return i - 1;
            }
        }

        return -1;
    }

    private static void init() throws IOException {
        n = Long.parseLong(br.readLine());
        facs = new long[21];
    }
}