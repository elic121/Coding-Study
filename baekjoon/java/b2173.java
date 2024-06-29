import java.util.*;
import java.io.*;

public class b2173 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;
    static int n;
    static int m;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        for (int i = 0; i < m; i++) {
            simulate();
        }
    }

    private static void print() {
        System.out.print(sb.toString());
    }

    private static void simulate() {
        int max = Integer.MIN_VALUE;
        int ax = -1;
        int ay = -1;
        int ar = -1;
        int ac = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }

                for (int r = 3; r <= n - i; r++) {
                    for (int c = 3; c <= n - j; c++) {
                        int score = getScore(i, j, r, c);

                        if (max < score) {
                            max = score;
                            ax = i;
                            ay = j;
                            ar = r;
                            ac = c;
                        }
                    }
                }
            }
        }

        if (ax == -1) {
            System.out.println(0);
            System.exit(0);
        }

        check(ax, ay, ar, ac);
        sb
                .append(max).append(" ")
                .append(ax + 1).append(" ")
                .append(ay + 1).append(" ")
                .append(ax + ar).append(" ")
                .append(ay + ac).append("\n");
    }

    private static int getScore(int x, int y, int r, int c) {
        int sum = 0;
        for (int col = y; col < y + c; col++) {
            if (visited[x][col] || visited[x + r - 1][col]) {
                return Integer.MIN_VALUE;
            }
            sum += arr[x][col];
            sum += arr[x + r - 1][col];
        }

        for (int row = x + 1; row < x + r - 1; row++) {
            if (visited[row][y] || visited[row][y + c - 1]) {
                return Integer.MIN_VALUE;
            }
            sum += arr[row][y];
            sum += arr[row][y + c - 1];
        }

        return sum;
    }

    private static void check(int x, int y, int r, int c) {
        for (int col = y; col < y + c; col++) {
            visited[x][col] = true;
            visited[x + r - 1][col] = true;
        }

        for (int row = x + 1; row < x + r - 1; row++) {
            visited[row][y] = true;
            visited[row][y + c - 1] = true;
        }
    }

    private static void init() throws IOException {
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][n];
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
