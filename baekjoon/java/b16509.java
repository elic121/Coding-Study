import java.util.*;
import java.io.*;

/**
 * b16509
 */
public class b16509 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static final int[] dsx = { 1, 1, -1, -1 };
    static final int[] dsy = { -1, 1, 1, -1 };
    static int kx;
    static int ky;
    static int sx;
    static int sy;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { sx, sy, 0 });

        boolean[][] visited = new boolean[10][9];
        visited[sx][sy] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == kx && p[1] == ky) {
                System.out.println(p[2]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p[0] + dx[i];
                int ny = p[1] + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                if (nx == kx && ny == ky) {
                    continue;
                }

                int tx = -1;
                int ty = -1;
                for (int j = 0; j < 2; j++) {
                    tx = nx;
                    ty = ny;

                    tx += dsx[(i + j) % 4];
                    ty += dsy[(i + j) % 4];

                    if (!isRange(tx, ty)) {
                        continue;
                    }

                    if (tx == kx && ty == ky) {
                        continue;
                    }

                    tx += dsx[(i + j) % 4];
                    ty += dsy[(i + j) % 4];

                    if (!isRange(tx, ty)) {
                        continue;
                    }

                    if (visited[tx][ty]) {
                        continue;
                    }

                    dq.add(new int[] { tx, ty, p[2] + 1 });
                    visited[tx][ty] = true;
                }

            }
        }
        System.out.println(-1);
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx < 10 && 0 <= ny && ny < 9;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        kx = Integer.parseInt(st.nextToken());
        ky = Integer.parseInt(st.nextToken());
    }
}