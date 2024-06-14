import java.util.*;
import java.io.*;

public class b4991 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int h;
    static int w;
    static int n;
    static char[][] arr;
    static boolean[][][] visited;
    static ArrayDeque<int[]> dq;

    public static void main(String[] args) throws IOException {
        for (;;) {
            init();
            solve();
        }
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (arr[i][j] == '*') {
                    arr[i][j] = (char) n++;
                }
            }
        }

        visited = new boolean[h][w][1 << n];
        dq = new ArrayDeque<>();

        end: for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (arr[i][j] == 'o') {
                    visited[i][j][0] = true;
                    dq.add(new int[] { i, j, 0, 0 });
                    arr[i][j] = '.';
                    break end;
                }
            }
        }

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[2] == (1 << n) - 1) {
                System.out.println(p[3]);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    continue;
                }

                if (arr[nx][ny] == 'x') {
                    continue;
                }

                int next = (arr[nx][ny] != '.') ? p[2] | 1 << arr[nx][ny] : p[2];

                if (visited[nx][ny][next]) {
                    continue;
                }

                visited[nx][ny][next] = true;
                dq.add(new int[] { nx, ny, next, p[3] + 1 });
            }
        }
        System.out.println(-1);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        n = 0;

        if (w == 0 && h == 0) {
            System.exit(0);
        }

        arr = new char[h][w];
        for (int i = 0; i < h; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
