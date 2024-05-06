import java.util.*;
import java.io.*;

public class b11967 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static boolean[][] visited;
    static boolean[][] isReachable;
    static boolean[][] turnOn;
    static ArrayList<int[]>[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { 1, 1 });

        visited[1][1] = true;
        isReachable[1][1] = true;
        turnOn[1][1] = true;
        int cnt = 1;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (!arr[p[0]][p[1]].isEmpty()) {
                for (int[] next : arr[p[0]][p[1]]) {
                    if (!turnOn[next[0]][next[1]]) {
                        if (!visited[next[0]][next[1]] && isReachable[next[0]][next[1]]) {
                            dq.add(new int[] { next[0], next[1] });
                        }
                        turnOn[next[0]][next[1]] = true;
                        cnt++;
                    }
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx <= 0 || nx > n || ny <= 0 || ny > n) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                isReachable[nx][ny] = true;

                if (!turnOn[nx][ny]) {
                    continue;
                }

                dq.add(new int[] { nx, ny });
                visited[nx][ny] = true;
            }
        }
        System.out.println(cnt);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1][n + 1];
        turnOn = new boolean[n + 1][n + 1];
        isReachable = new boolean[n + 1][n + 1];

        arr = new ArrayList[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                arr[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] go = new int[2];
            go[0] = a;
            go[1] = b;
            arr[x][y].add(go);
        }
    }
}
