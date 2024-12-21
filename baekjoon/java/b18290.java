import java.util.*;
import java.io.*;

public class b18290 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int k;
    static int max;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(max);
    }

    private static void solve() {
        comb(0, 0, 0, new boolean[n][m]);
    }

    private static void comb(int depth, int curr, int value, boolean[][] visited) {
        if (depth == k) {
            max = Math.max(max, value);
            return;
        }

        end: for (int i = curr; i < n * m; i++) {
            int row = i / m;
            int col = i % m;

            for (int d = 0; d < 4; d++) {
                int x = row + dx[d];
                int y = col + dy[d];

                if (!isRange(x, y)) {
                    continue;
                }

                if (visited[x][y]) {
                    continue end;
                }
            }

            visited[row][col] = true;
            comb(depth + 1, i + 1, value + arr[row][col], visited);
            visited[row][col] = false;
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}