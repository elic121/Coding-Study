import java.util.*;
import java.io.*;

public class b9944 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int min;
    static int count;
    static int iter = 0;
    static char[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        for (;;) {
            try {
                init();
                solve();
                print();
            } catch (Exception e) {
                System.exit(0);
            }
        }
    }

    private static void solve() {
        if (count == 1) {
            min = 0;
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '.') {
                    simulate(i, j);
                }
            }
        }
    }

    private static void simulate(int x, int y) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }

        for (int d = 0; d < 4; d++) {
            if (isValid(x, y, d)) {
                visited[x][y] = true;
                dfs(x, y, d, 1, 1);
                visited[x][y] = false;
            }
        }
    }

    private static void dfs(int x, int y, int dir, int c, int change) {
        if (c == count) {
            min = Math.min(min, change);
            return;
        }

        if (isValid(x, y, dir)) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            visited[nx][ny] = true;
            dfs(nx, ny, dir, c + 1, change);
            visited[nx][ny] = false;
        } else {
            for (int d = 0; d < 4; d++) {
                if (d != dir && isValid(x, y, d)) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    visited[nx][ny] = true;
                    dfs(nx, ny, d, c + 1, change + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }

    private static boolean isValid(int x, int y, int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return false;
        }

        if (arr[nx][ny] == '*') {
            return false;
        }

        if (visited[nx][ny]) {
            return false;
        }

        return true;
    }

    private static void print() {
        System.out.println("Case " + iter + ":" + " " + (min == Integer.MAX_VALUE ? -1 : min));
    }

    private static void init() throws IOException {
        iter++;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        count = 0;

        visited = new boolean[n][m];
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '.') {
                    count++;
                }
            }
        }
    }
}