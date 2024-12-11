import java.util.*;
import java.io.*;

public class b24313 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int a1;
    static int a0;
    static int c;
    static int n0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = n0; i <= 100; i++) {
            if (a1 * i + a0 > c * i) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        a1 = Integer.parseInt(st.nextToken());
        a0 = Integer.parseInt(st.nextToken());

        c = Integer.parseInt(br.readLine());
        n0 = Integer.parseInt(br.readLine());
    }

}