import java.util.*;
import java.io.*;

public class b15653 {
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Point p) {
            return (this.x == p.x) && (this.y == p.y);
        }
    }

    static class Info {
        int cnt;

        Info(int cnt) {
            this.cnt = cnt;
        }
    }

    static int N, M;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static Point red, blue, goal;
    static char[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    private static void bfs() {
        ArrayDeque<Point[]> dq = new ArrayDeque<>();
        dq.add(new Point[] { red, blue });
        ArrayDeque<Info> dqInfo = new ArrayDeque<>();
        dqInfo.add(new Info(0));

        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[red.x][red.y][blue.x][blue.y] = true;

        while (!dq.isEmpty()) {
            Point[] pos = dq.pop();
            Info info = dqInfo.pop();

            if (info.cnt == (N - 1) * (M - 1) + 1) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                Point r = pos[0];
                Point b = pos[1];

                Point nr = nextMove(r.x, r.y, i);
                Point nb = nextMove(b.x, b.y, i);

                if (nb.equals(goal)) {
                    continue;
                }

                if (nr.equals(goal)) {
                    System.out.println(info.cnt + 1);
                    return;
                }

                if (nr.equals(nb)) {
                    int p = (r.x < b.x || r.y < b.y) ? 1 : -1;
                    if (p * (dx[i] + dy[i]) > 0) {
                        nr.x -= dx[i];
                        nr.y -= dy[i];
                    } else {
                        nb.x -= dx[i];
                        nb.y -= dy[i];
                    }
                }

                if (visited[nr.x][nr.y][nb.x][nb.y]) {
                    continue;
                }

                dq.add(new Point[] { nr, nb });
                dqInfo.add(new Info(info.cnt + 1));
                visited[nr.x][nr.y][nb.x][nb.y] = true;
            }
        }

        System.out.println(-1);
    }

    private static Point nextMove(int x, int y, int d) {
        int nx = x;
        int ny = y;
        while (true) {
            nx += dx[d];
            ny += dy[d];

            if (arr[nx][ny] == 'O') {
                return new Point(nx, ny);
            } else if (arr[nx][ny] == '#') {
                return new Point(nx - dx[d], ny - dy[d]);
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'R') {
                    red = new Point(i, j);
                } else if (arr[i][j] == 'B') {
                    blue = new Point(i, j);
                } else if (arr[i][j] == 'O') {
                    goal = new Point(i, j);
                }
            }
        }
    }
}