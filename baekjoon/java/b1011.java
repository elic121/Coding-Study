import java.util.*;
import java.io.*;

/**
 * b1011
 */
public class b1011 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int stt;
    static int end;
    static int diff;
    static int max;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            init();
            solve();
        }
    }

    private static void solve() {
        int cnt = (max - 1) << 1;
        int plus = (diff - max * (max - 1)) / max;
        int rest = (diff - max * (max - 1)) % max;
        cnt += plus + (rest != 0 ? 1 : 0);

        System.out.println(cnt);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        stt = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        diff = end - stt;
        max = (int) (Math.sqrt(diff));
    }
}