import java.util.*;
import java.io.*;

public class b21322 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;
    static int n;
    static int m;
    static int[][] arr;
    static int[][] order;
    static int[][][] pos;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        simulate();
        print();
    }

    private static void simulate() {
        for (int i = 0; i < m; i++) {
            int[] o = order[i];

            if (o[0] == 1) {
                one(o[1], o[2]);
            } else if (o[0] == 2) {
                two(o[1], o[2]);
            } else {
                three(o[1], o[2]);
            }
        }
    }

    private static void one(int a, int b) {
        int count = 4 * (n - 2 * a + 1);
        int rot = b % count;

    }

    private static int[] next(int a, int b, int x, int y) {
        for (int i = 0; i < b; i++) {
            while (!isRange(a, x, y)) {
                
            }
        }

        return new int[] { x, y };
    }

    private static boolean isRange(int a, int nx, int ny) {
        return a - 1 <= nx && nx <= n - a && a - 1 <= ny && ny <= n - a;
    }

    private static void two(int x, int y) {
        int temp = arr[x][y];
        arr[x][y] = arr[x + 1][y];
        arr[x + 1][y] = arr[x + 1][y + 1];
        arr[x + 1][y + 1] = arr[x][y + 1];
        arr[x][y + 1] = temp;
    }

    private static void three(int x, int y) {
        sb.append(arr[x][y]).append("\n");
    }

    private static void print() {
        System.out.println(sb.toString());
    }

    private static void init() throws IOException {
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            order[i][0] = Integer.parseInt(st.nextToken());
            order[i][1] = Integer.parseInt(st.nextToken());
            order[i][2] = Integer.parseInt(st.nextToken());
        }
    }
}
