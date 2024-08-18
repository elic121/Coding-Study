import java.util.*;
import java.io.*;

public class b2151 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static int n;
    static int min;
    static int x1, y1;
    static int x2, y2;
    static boolean[][] visited;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(min);
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        visited[x1][y1] = true;

        ArrayDeque<int[]> dq = new ArrayDeque<>();

        int possible = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x1 + dx[d];
            int ny = y1 + dy[d];

            if (!isRange(nx, ny)) {
                continue;
            }

            if (arr[nx][ny] == '#') {
                min = 0;
                return;
            }

            if (arr[nx][ny] == '.' || arr[nx][ny] == '!') {
                possible |= 1 << d;
            }
        }

        for (int d = 0; d < 4; d++) {
            if ((possible & 1 << d) == 0) {
                continue;
            }

            for (int s = 1; s < n; s++) {
                int nx = x1 + dx[d] * s;
                int ny = y1 + dy[d] * s;

                if (!isRange(nx, ny) || arr[nx][ny] == '*') {
                    break;
                }

                if (arr[nx][ny] == '#') {
                    min = 0;
                    return;
                }

                if (arr[nx][ny] == '!') {
                    visited[nx][ny] = true;
                    dq.add(new int[] { nx, ny, (arr[nx][ny] == '!' ? 1 : 0), d });
                }
            }
        }

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == x2 && p[1] == y2) {
                min = p[2];
                return;
            }

            for (int d = 0; d < 4; d++) {
                if (d == (p[3] + 2) % 4 || d == p[3]) {
                    continue;
                }

                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                while (isRange(nx, ny) && arr[nx][ny] != '*') {
                    if (arr[nx][ny] == '#') {
                        min = p[2];
                        return;
                    }

                    if (visited[nx][ny]) {
                        nx += dx[d];
                        ny += dy[d];
                        continue;
                    }

                    if (arr[nx][ny] == '!') {
                        visited[nx][ny] = true;
                        dq.add(new int[] { nx, ny, p[2] + (arr[nx][ny] == '#' ? 0 : 1), d });
                    }

                    nx += dx[d];
                    ny += dy[d];
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        x1 = x2 = y1 = y2 = -1;

        visited = new boolean[n][n];
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '#') {
                    if (x1 != -1 && y1 != -1) {
                        x2 = i;
                        y2 = j;
                    } else {
                        x1 = i;
                        y1 = j;
                    }
                }
            }
        }
    }
}
