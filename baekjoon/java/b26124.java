import java.util.*;
import java.io.*;

public class b26124 {
    static class Point implements Comparable<Point> {
        int x, y, key;

        Point(int x, int y, int key) {
            this.x = x;
            this.y = y;
            this.key = key;
        }

        @Override
        public int compareTo(Point o) {
            if (o.key != this.key) {
                return Integer.compare(o.key, this.key);
            }
            return 0;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int h;
    static int w;
    static int cnt;
    static int fill;
    static int lights;
    static int[][] arr;
    static boolean[][] visited;
    static ArrayDeque<int[]> dq;
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(cnt == Integer.MAX_VALUE ? -1 : cnt);
    }

    private static void solve() {
        while (!isValid()) {
            simulate();
            cnt++;
        }
    }

    private static void simulate() {
        if (pq.isEmpty()) {
            System.out.println(-1);
            System.exit(0);
        }

        for (;;) {
            Point p = pq.poll();
            if (visited[p.x][p.y]) {
                continue;
            }
            bfs(p.x, p.y);
            break;
        }
    }

    private static void bfs(int x, int y) {
        visited[x][y] = true;
        dq.add(new int[] { x, y });
        fill++;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            int pivot = arr[p[0]][p[1]];

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (!isRange(nx, ny)) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                if (arr[nx][ny] == -1) {
                    continue;
                }

                if (arr[nx][ny] < pivot - 1) {
                    System.out.println(-1);
                    System.exit(0);
                }

                if (arr[nx][ny] == 0) {
                    continue;
                }

                if (arr[nx][ny] != pivot - 1) {
                    continue;
                }

                dq.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                fill++;
            }
        }
    }

    private static boolean isValid() {
        return lights == fill;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 < nx && nx <= h && 0 < ny && ny <= w;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        cnt = 0;
        fill = 0;
        lights = 0;

        dq = new ArrayDeque<>();
        pq = new PriorityQueue<>();

        visited = new boolean[h + 1][w + 1];
        arr = new int[h + 1][w + 1];

        for (int i = 1; i <= h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= w; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] >= 1) {
                    pq.add(new Point(i, j, arr[i][j]));
                    lights++;
                }

            }
        }

        if (lights == 0) {
            System.out.println(0);
            System.exit(0);
        }
    }
}