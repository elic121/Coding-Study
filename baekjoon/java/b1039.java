import java.util.*;
import java.io.*;

public class b1039 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int MAX = 1000000;
    static int n;
    static int k;
    static int digit;
    static int[] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bfs();
        printMaxValue();
    }

    private static void bfs() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { n, 0 });

        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            int num = p[0];
            int iter = p[1];

            if (iter >= k) {
                break;
            }

            for (int i = 0; i < digit; i++) {
                for (int j = i + 1; j < digit; j++) {
                    int conv = convert(num, i, j);

                    if (calcDigit(conv) != digit) {
                        continue;
                    }
                    if (visited[conv][(iter + 1) % 2]) {
                        continue;
                    }

                    dq.add(new int[] { conv, iter + 1 });
                    visited[conv][(iter + 1) % 2] = true;
                }
            }
        }
    }

    private static void printMaxValue() {
        for (int i = MAX; i >= (int) Math.pow(10, digit - 1); i--) {
            if (visited[i][k % 2]) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private static int convert(int num, int i, int j) {
        char[] c = String.valueOf(num).toCharArray();

        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < c.length; k++) {
            sb.append(c[k]);
        }

        return Integer.parseInt(sb.toString());
    }

    private static int calcDigit(int num) {
        int cnt = 0;
        while (num != 0) {
            num /= 10;
            cnt++;
        }
        return cnt;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        digit = calcDigit(n);

        arr = new int[MAX + 1];
        visited = new boolean[MAX + 1][2];
    }
}
