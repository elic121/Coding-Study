import java.util.*;
import java.io.*;

/**
 * b14510
 */
public class b14510 {

    static int T, N, MAX, trees[];
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());
            MAX = 0;
            trees = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                MAX = Math.max(MAX, val);
                trees[j] = val;
            }

            solve(i);
        }
    }

    private static void solve(int idx) {
        int sol = calculate();
        System.out.printf("#%d %d\n", idx, sol);
    }

    private static int calculate() {

        int days = 0;
        int odd = 0;
        int even = 0;

        for (int i = 0; i < N; i++) {
            int diff = MAX - trees[i];

            odd += diff % 2;
            even += diff / 2;
        }

        int diff = even >= odd ? (even - odd) << 1 : 0;
        days = ((odd + (diff / 3)) << 1) + (diff % 3) - (diff == 0 && odd != even ? 1 : 0); 

        return days;
    }
}