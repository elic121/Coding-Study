import java.util.*;
import java.io.*;

public class b3184 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int r;
    static int c;
    static int o;
    static int v;
    static boolean[][] visited;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && arr[i][j] != '#') {
                    bfs(i, j);
                }
            }
        }
        System.out.println(o + " " + v);
    }

    private static void bfs(int x, int y) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        visited[x][y] = true;
        int oc = arr[x][y] == 'o' ? 1 : 0;
        int vc = arr[x][y] == 'v' ? 1 : 0;
        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == '#') {
                    continue;
                }
                dq.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                oc += arr[nx][ny] == 'o' ? 1 : 0;
                vc += arr[nx][ny] == 'v' ? 1 : 0;
            }
        }
        if (oc > vc) {
            o += oc;
        } else {
            v += vc;
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        o = 0;
        v = 0;

        visited = new boolean[r][c];
        arr = new char[r][c];
        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
