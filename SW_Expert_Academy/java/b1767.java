import java.util.*;
import java.io.*;

public class b1767 {
    static int T, N, MAX_CELL, MIN_LEN, CELL, D;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    static char[][] arr;
    static boolean[][] visited;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new char[N][N];
            visited = new boolean[N][N];

            MAX_CELL = 0;
            MIN_LEN = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = st.nextToken().charAt(0);
                    if (arr[i][j] == '1') {
                        CELL++;
                        if (isCorner(i, j)) {
                            visited[i][j] = true;
                            D++;
                        }
                    }
                }
            }

            solve(tc);
        }
    }

    private static void solve(int tc) {
        simulate(D, 0);
        System.out.printf("#%d %d\n", tc, MIN_LEN);
    }

    private static void simulate(int depth, int length) {
        if (depth > MAX_CELL) {
            MAX_CELL = depth;
            MIN_LEN = length;
        } else if (depth == MAX_CELL) {
            MIN_LEN = Math.min(MIN_LEN, length);
        }

        if (depth == CELL) return;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (arr[i][j] != '1' || visited[i][j]) continue;
                for (int k = 0; k < 4; k++) {
                    if (isPossible(i, j, k)) {
                        simulate(depth + 1, length + wire(i, j, k));
                        restore(i, j, k);
                    }
                }
            }
        }
    }

    private static int wire(int x, int y, int d) {
        int cnt = -1;
        while (isRange(x, y)) {
            visited[x][y] = true;
            x += dx[d];
            y += dy[d];
            cnt++;
        }

        return cnt;
    }

    private static void restore(int x, int y, int d) {
        while (isRange(x, y)) {
            visited[x][y] = false;
            x += dx[d];
            y += dy[d];
        }
    }

    private static boolean isPossible(int x, int y, int d) {
        do {
            x += dx[d];
            y += dy[d];
        } while (isRange(x, y) && arr[x][y] == '0' && !visited[x][y]);

        return !isRange(x, y);
    }

    private static boolean isCorner(int x, int y) {
        return x == 0 || x == N - 1 || y == 0 || y == N - 1;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
