import java.util.*;
import java.io.*;

public class b18405 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int k;
    static int gx;
    static int gy;
    static int gt;
    static int time;
    static int[][] arr;
    static boolean[][] visited;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        while (!pq.isEmpty() && time < gt) {
            time++;

            PriorityQueue<int[]> temp = new PriorityQueue<>((x, y) -> {
                return Integer.compare(x[2], y[2]);
            });
            for (int i = 0, size = pq.size(); i < size; i++) {
                int[] p = pq.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = p[0] + dx[d];
                    int ny = p[1] + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    if (visited[nx][ny]) {
                        continue;
                    }

                    if (arr[nx][ny] != 0) {
                        continue;
                    }

                    arr[nx][ny] = arr[p[0]][p[1]];
                    visited[nx][ny] = true;
                    temp.add(new int[] { nx, ny, arr[nx][ny] });
                }
            }

            while (!temp.isEmpty()) {
                pq.add(temp.poll());
            }
        }

        if (arr[gx][gy] == 0) {
            System.out.println(0);
        } else {
            System.out.println(arr[gx][gy]);
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>((x, y) -> {
            return Integer.compare(x[2], y[2]);
        });

        gt = 0;
        gx = 0;
        gy = 0;
        time = 0;

        visited = new boolean[n][n];
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int t = Integer.parseInt(st.nextToken());

                if (t > 0) {
                    arr[i][j] = t;
                    visited[i][j] = true;
                    pq.add(new int[] { i, j, t });
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        gt = Integer.parseInt(st.nextToken());
        gx = Integer.parseInt(st.nextToken()) - 1;
        gy = Integer.parseInt(st.nextToken()) - 1;
    }
}