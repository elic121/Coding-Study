import java.util.*;
import java.io.*;

public class b2661 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        backTrack(0);
    }

    private static void print() {
        System.out.print(sb.toString());
    }

    private static void backTrack(int depth) {
        if (!isValid()) {
            return;
        }

        if (depth == n) {
            print();
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            sb.append(i);
            backTrack(depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean isValid() {
        int depth = sb.length() - 1;
        for (int i = 1; i <= (depth + 1) / 2; i++) {
            int s1 = depth;
            int s2 = depth - i;

            int cnt = 0;
            int k = 0;
            while (k < i) {
                cnt += sb.charAt(s1) == sb.charAt(s2) ? 1 : 0;
                s1--;
                s2--;
                k++;

                if (cnt == i) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
    }

}