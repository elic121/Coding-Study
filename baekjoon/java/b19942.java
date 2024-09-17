import java.util.*;
import java.io.*;

public class b19942 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int min;
    static int comb;
    static int[] minium;
    static int[][] nuts;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        backTrack(0, 0, 0, 0);
    }

    private static void print() {
        if (comb == -1) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if ((comb & 1 << i) != 0) {
                sb.append(i + 1).append(" ");
            }
        }
        System.out.println(min);
        System.out.print(sb.toString());
    }

    private static void backTrack(int depth, int curr, int include, int cost) {
        if (cost >= min) {
            return;
        }

        if (isValid(include)) {
            min = cost;
            comb = include;
            return;
        }

        if (depth == n) {
            return;
        }

        for (int i = curr; i < n; i++) {
            backTrack(depth + 1, i + 1, include | 1 << i, cost + nuts[i][4]);
        }
    }

    private static boolean isValid(int include) {
        end: for (int i = 0; i < 4; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((include & 1 << j) != 0) {
                    sum += nuts[j][i];
                }

                if (sum >= minium[i]) {
                    continue end;
                }
            }
            return false;
        }
        return true;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        comb = -1;

        st = new StringTokenizer(br.readLine());
        minium = new int[4];
        for (int i = 0; i < 4; i++) {
            minium[i] = Integer.parseInt(st.nextToken());
        }

        nuts = new int[n][5];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                nuts[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}