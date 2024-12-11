import java.util.*;
import java.io.*;

public class b14620 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int min;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int[][] prices;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(min);
    }

    private static void solve() {
        comb(0, 0, new boolean[n][n]);
    }

    private static void comb(int depth, int curr, boolean[][] visited) {
        if (depth == 3) {
            int result = checkAndCalc(visited);

            if (result != 3001) {
                min = Math.min(min, result);
            }

            return;
        }

        for (int i = curr; i < n * n; i++) {
            int x = i / n;
            int y = i % n;

            if (x == 0 || x == n - 1 || y == 0 || y == n - 1) {
                continue;
            }

            visited[x][y] = true;
            comb(depth + 1, i + 1, visited);
            visited[x][y] = false;
        }
    }

    private static int checkAndCalc(boolean[][] visited) {
        boolean[][] checked = new boolean[n][n];
        int price = 0;

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!visited[i][j]) {
                    continue;
                }

                if (checked[i][j]) {
                    return 3001;
                }

                price += prices[i][j];
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (checked[nx][ny]) {
                        return 3001;
                    }

                    if (visited[nx][ny]) {
                        return 3001;
                    }

                    price += prices[nx][ny];
                    checked[nx][ny] = true;
                }
            }
        }

        return price;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        min = 3001;

        prices = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                prices[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}