import java.util.*;
import java.io.*;

public class b17141 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int wall;
    static int min;
    static int[][] arr;
    static boolean[][] visited;
    static ArrayList<Integer> order;
    static ArrayList<int[]> virus;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        comb(0, 0);
    }

    private static void print() {
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void bfs() {
        int cnt = m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            int index = order.get(i);
            int x = virus.get(index)[0];
            int y = virus.get(index)[1];

            dq.add(new int[] { x, y });
            visited[x][y] = true;
        }

        int time = 0;
        while (!dq.isEmpty()) {
            time++;
            for (int i = 0, size = dq.size(); i < size; i++) {
                int[] p = dq.pop();

                for (int d = 0; d < 4; d++) {
                    int nx = p[0] + dx[d];
                    int ny = p[1] + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    if (visited[nx][ny]) {
                        continue;
                    }

                    if (arr[nx][ny] == 1) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    dq.add(new int[] { nx, ny });
                    cnt++;
                }
            }
        }

        if (cnt == n * n - wall && min > time - 1) {
            min = time - 1;
        }
    }

    private static void comb(int depth, int curr) {
        if (depth == m) {
            bfs();
            return;
        }

        for (int i = curr; i < virus.size(); i++) {
            order.add(i);
            comb(depth + 1, i + 1);
            order.remove(order.size() - 1);
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        wall = 0;

        order = new ArrayList<>();
        virus = new ArrayList<>();
        visited = new boolean[n][n];
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 1) {
                    wall++;
                }

                if (arr[i][j] == 2) {
                    virus.add(new int[] { i, j });
                }
            }
        }
    }
}
