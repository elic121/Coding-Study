import java.util.*;
import java.io.*;

public class b27466 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int p;
    static int a1;
    static int a2;
    static int checker;
    static char[] ch;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = ch.length - 1; i >= 0; i--) {
            if (((1 << (ch[i] - 'A')) & checker) != 0) {
                continue;
            }

            p = i;
            break;
        }

        if (p == 0 || p == 1 || p == 2) {
            System.out.println("NO");
            return;
        }

        for (int i = p - 1; i >= 0; i--) {
            if (ch[i] == 'A') {
                if (a1 != -1) {
                    a2 = i;
                    break;
                }

                a1 = i;
            }
        }

        if (a1 == -1 || a2 == -1) {
            System.out.println("NO");
            return;
        }

        if (a2 < m - 3) {
            System.out.println("NO");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m - 3; i++) {
            sb.append(ch[i]);
        }

        sb.append('A').append('A').append(ch[p]);
        System.out.println("YES");
        System.out.print(sb.toString());
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ch = br.readLine().toCharArray();
        p = n - 1;
        a1 = -1;
        a2 = -1;
        checker = 0;

        checker |= 1 << ('A' - 'A');
        checker |= 1 << ('E' - 'A');
        checker |= 1 << ('I' - 'A');
        checker |= 1 << ('O' - 'A');
        checker |= 1 << ('U' - 'A');
    }
}