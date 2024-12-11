import java.io.*;

public class b12871 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] s;
    static char[] t;
    static char[] extendedS;
    static char[] extendedT;

    public static void main(String[] args) throws IOException {
        init();
        solve();

    }

    private static void solve() {
        for (int i = 0; i < extendedS.length; i++) {
            if (extendedS[i] != extendedT[i]) {
                System.out.println(0);
                System.exit(0);
            }
        }

        System.out.println(1);

    }

    private static int getLCM(int a, int b) {
        int x, y;
        x = a >= b ? a : b;
        y = a >= b ? b : a;

        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }

        return (a * b) / x;
    }

    private static void init() throws IOException {
        s = br.readLine().toCharArray();
        t = br.readLine().toCharArray();

        int lcm = getLCM(s.length, t.length);
        extendedS = new char[lcm];
        extendedT = new char[lcm];

        for (int i = 0; i < lcm / s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                extendedS[i * s.length + j] = s[j];
            }
        }

        for (int i = 0; i < lcm / t.length; i++) {
            for (int j = 0; j < t.length; j++) {
                extendedT[i * t.length + j] = t[j];
            }
        }
    }
}