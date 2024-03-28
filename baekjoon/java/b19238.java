import java.util.*;
import java.io.*;

public class b19238 {
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static int N, M, FUEL;
    static Point taxi;
    static Point[][] pass;
    static boolean arr[][];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < M; i++) {
            simulate();
        }
        System.out.println(FUEL);
    }

    private static void simulate() {
        int[] goal = judge();
        
        FUEL -= goal[1];

        int next = getDistance(pass[goal[0]][0], pass[goal[0]][1]);

        if (FUEL < next) {
            System.out.println(-1);
            System.exit(0);
        }

        FUEL += next;

        taxi = pass[goal[0]][1];
        pass[goal[0]] = null;
    }

    private static int[] judge() {
        int[] candi = null;

        for (int i = 0; i < M; i++) {
            if (pass[i] == null)
                continue;

            int d = getDistance(taxi, pass[i][0]);
            if (d > FUEL || d == -1)
                continue;

            if (candi == null) {
                candi = new int[] { i, d, pass[i][0].x, pass[i][0].y };
                continue;
            }

            if (candi[1] > d) {
                candi = new int[] { i, d, pass[i][0].x, pass[i][0].y };
            } else if (candi[1] == d) {
                if (candi[2] > pass[i][0].x) {
                    candi = new int[] { i, d, pass[i][0].x, pass[i][0].y };
                } else if (candi[2] == pass[i][0].x) {
                    if (candi[3] > pass[i][0].y) {
                        candi = new int[] { i, d, pass[i][0].x, pass[i][0].y };
                    }
                }
            }
        }
        return candi;
    }

    private static int getDistance(Point s, Point e) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { s.x, s.y, 0 });

        boolean[][] visited = new boolean[N][N];
        visited[s.x][s.y] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == e.x && p[1] == e.y) {
                return p[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = p[0] + dx[i];
                int ny = p[1] + dy[i];
                int ns = p[2] + 1;

                if (!isRange(nx, ny))
                    continue;
                if (arr[nx][ny])
                    continue;
                if (visited[nx][ny])
                    continue;

                dq.add(new int[] { nx, ny, ns });
                visited[nx][ny] = true;

            }
        }
        System.out.println(-1);
        System.exit(0);
        return -1;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        FUEL = Integer.parseInt(st.nextToken());

        pass = new Point[M][2];
        taxi = null;

        arr = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = st.nextToken().charAt(0) == '1';
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Point(x, y);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            pass[i][0] = new Point(sx, sy);
            pass[i][1] = new Point(ex, ey);
        }
    }
}
