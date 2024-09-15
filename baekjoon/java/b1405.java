import java.util.*;
import java.io.*;

public class b1405 {
    static final int[] dx = { 0, 0, 1, -1 };
    static final int[] dy = { 1, -1, 0, 0 };
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int cnt;
    static double[] prob;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        boolean[][] visited = new boolean[2 * n + 3][2 * n + 3];
        visited[0][0] = true;

        double ans = dfs(0, 0, 0, 1.0, visited);
        System.out.println(ans);
    }

    private static double dfs(int depth, int x, int y, double intg, boolean[][] pos) {
        if (depth == n + 1) {
            return intg;
        }

        double curr = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (Double.compare(prob[i], 0.0) == 0) {
                continue;
            }

            if (pos[nx + n + 1][ny + n + 1]) {
                continue;
            }

            pos[nx + n + 1][ny + n + 1] = true;
            curr += dfs(depth + 1, nx, ny, intg * prob[i], pos);
            pos[nx + n + 1][ny + n + 1] = false;
        }

        return curr;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        cnt = 0;

        prob = new double[4];
        for (int i = 0; i < 4; i++) {
            prob[i] = Double.parseDouble(st.nextToken()) / 100;
        }
    }
}