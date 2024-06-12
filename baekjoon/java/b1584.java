import java.util.*;
import java.io.*;

public class b1584 {
    static class Data implements Comparable<Data> {
        int x;
        int y;
        int l;

        Data(int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.l = l;
        }

        @Override
        public int compareTo(Data o) {
            return Integer.compare(this.l, o.l);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 0, 1, 0, -1 };
    static final int[] dy = { -1, 0, 1, 0 };
    static final int size = 501;
    static int n;
    static int m;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        int[][] life = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(life[i], Integer.MAX_VALUE);
        }
        life[0][0] = 0;

        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.add(new Data(0, 0, 0));

        while (!pq.isEmpty()) {
            Data data = pq.poll();

            if (data.x == size - 1 && data.y == size - 1) {
                return data.l;
            }

            for (int d = 0; d < 4; d++) {
                int nx = data.x + dx[d];
                int ny = data.y + dy[d];

                if (!isRange(nx, ny)) {
                    continue;
                }

                if (arr[nx][ny] == 2) {
                    continue;
                }

                int nl = data.l + arr[nx][ny];
                if (life[nx][ny] > nl) {
                    pq.add(new Data(nx, ny, nl));
                    life[nx][ny] = nl;
                }
            }
        }

        return -1;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx < size && 0 <= ny && ny < size;
    }

    private static void init() throws IOException {
        arr = new int[size][size];

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            fill(1, st);
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            fill(2, st);
        }
    }

    private static void fill(int f, StringTokenizer st) {
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if (y1 > y2) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                arr[x][y] = f;
            }
        }
    }
}