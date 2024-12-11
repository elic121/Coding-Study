import java.io.*;
import java.util.*;

public class b12904 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] s;
    static char[] t;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(isSame() ? 1 : 0);
    }

    private static void solve() {
        while (t.length != s.length) {
            if (t[t.length - 1] == 'A') {
                changeOne();
            } else {
                changeTwo();
            }
        }
    }

    private static void changeOne() {
        t = Arrays.copyOf(t, t.length - 1);
    }

    private static void changeTwo() {
        char[] temp = new char[t.length - 1];
        for (int i = 0; i < t.length - 1; i++) {
            temp[i] = t[t.length - 1 - i - 1];
        }

        t = Arrays.copyOf(temp, temp.length);
    }

    private static boolean isSame() {
        for (int i = 0; i < s.length; i++) {
            if (s[i] != t[i]) {
                return false;
            }
        }
        return true;
    }

    private static void init() throws IOException {
        s = br.readLine().toCharArray();
        t = br.readLine().toCharArray();
    }
}