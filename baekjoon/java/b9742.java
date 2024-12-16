import java.util.*;
import java.io.*;

public class b9742 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int cnt;
    static int seq;
    static char[] ch;
    static String str;

    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                init();
                solve();
                print();
            } catch (Exception e) {
                System.exit(0);
            }
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (char c : ch) {
            sb.append(c);
        }

        System.out.println(sb.toString() + " " + seq + " = " + (str.isEmpty() ? "No permutation" : str));
    }

    private static void solve() {
        perm(0, 0, new StringBuilder());
    }

    private static void perm(int depth, int visited, StringBuilder sb) {
        if (!str.isEmpty()) {
            return;
        }

        if (depth == ch.length) {
            cnt++;

            if (cnt == seq) {
                str = sb.toString();
            }

            return;
        }

        for (int i = 0; i < ch.length; i++) {
            if ((visited & 1 << i) != 0) {
                continue;
            }

            sb.append(ch[i]);
            perm(depth + 1, visited | 1 << i, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static void init() throws IOException {
        cnt = 0;
        str = "";

        st = new StringTokenizer(br.readLine());

        ch = st.nextToken().toCharArray();
        seq = Integer.parseInt(st.nextToken());
    }
}