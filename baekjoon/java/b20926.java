import java.util.*;
import java.io.*;

public class b20926 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int w;
    static int h;
    static int sx;
    static int sy;
    static int min;
    static int[][] dist;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        bfs();
    }

    private static void print() {
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void bfs() {
        dist[sx][sy] = 0;

        PriorityQueue<int[]> dq = new PriorityQueue<>((x, y) -> {
            return Integer.compare(x[2], y[2]);
        });

        dq.add(new int[] { sx, sy, 0 });

        while (!dq.isEmpty()) {
            int[] p = dq.poll();

            if (arr[p[0]][p[1]] == 'E') {
                min = p[2];
                return;
            }

            for (int d = 0; d < 4; d++) {
                int[] info = judge(p[0], p[1], d);
                int nx = info[0];
                int ny = info[1];
                int ns = info[2];

                if (ns == -1) {
                    continue;
                }

                if (dist[nx][ny] <= p[2] + ns) {
                    continue;
                }

                dq.add(new int[] { nx, ny, dist[nx][ny] = p[2] + ns });
            }
        }
    }

    private static int[] judge(int x, int y, int d) {
        int cnt = 0;
        int nx = x + dx[d];
        int ny = y + dy[d];

        while (isRange(nx, ny) && arr[nx][ny] != 'R' && arr[nx][ny] != 'H' && arr[nx][ny] != 'E') {
            cnt += Character.getNumericValue(arr[nx][ny]);
            nx += dx[d];
            ny += dy[d];
        }

        if (!isRange(nx, ny)) {
            return new int[] { -1, -1, -1 };
        }

        if (arr[nx][ny] == 'E') {
            return new int[] { nx, ny, cnt };
        }

        if (arr[nx][ny] == 'R') {
            return new int[] { nx - dx[d], ny - dy[d], cnt };
        }

        return new int[] { -1, -1, -1 };
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx < h && 0 <= ny && ny < w;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        sx = sy = 0;
        min = Integer.MAX_VALUE;

        arr = new char[h][w];
        for (int i = 0; i < h; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                if (arr[i][j] == 'T') {
                    sx = i;
                    sy = j;
                    arr[i][j] = 'H';
                }
            }
        }

        dist = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
    }
}
