import java.util.*;
import java.io.*;

public class b2589 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static int r;
    static int c;
    static int max;
    static char[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }
        System.out.println(max);
    }

    private static void bfs(int i, int j) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] {i, j, 0});

        visited = new boolean[r][c];
        visited[i][j] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];
                int ns = p[2] + 1;
                if (!isRange(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == 'W') {
                    continue;
                }
                visited[nx][ny] = true;
                max = Math.max(max, ns);
                dq.add(new int[] {nx, ny, ns});
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        max = 0;

        arr = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
