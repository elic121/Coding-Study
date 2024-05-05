import java.util.*;
import java.io.*;

public class b17086 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0, -1, -1, 1, 1 };
    static final int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };
    static int r;
    static int c;
    static boolean[][] visited;
    static int[][] dist;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] != 1) {
                    max = Math.max(max, dist[i][j]);
                }
            }
        }
        System.out.println(max);
    }

    private static void bfs(int x, int y) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y, 0 });

        visited = new boolean[r][c];
        visited[x][y] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            for (int d = 0; d < 8; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == 1) {
                    continue;
                }
                if (p[2] + 1 >= dist[nx][ny]) {
                    continue;
                }
                dq.add(new int[] { nx, ny, p[2] + 1 });
                visited[nx][ny] = true;
                dist[nx][ny] = p[2] + 1;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visited = new boolean[r][c];
        arr = new int[r][c];
        dist = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                dist[i][j] = 987654321;
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
