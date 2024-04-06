package codetree.java;

import java.util.*;
import java.io.*;

public class 코드트리빵 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dx = { -1, 0, 0, 1 };
    static final int[] dy = { 0, -1, 1, 0 };
    static final int INF = Integer.MAX_VALUE;
    static int n;
    static int m;
    static int[][] con;
    static int[][] base;
    static int[][] pos;
    static boolean[][] find;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int s = 1; s <= n * n; s++) {
            simulate(s);
            if (isFinished()) {
                System.out.println(s);
                return;
            }
        }
    }

    private static void simulate(int s) {
        for (int i = 1; i <= Math.min(s - 1, m); i++) {
            if (pos[i][0] == con[i][0] && pos[i][1] == con[i][1]) {
                continue;
            }
            move(i);
        }

        for (int i = 1; i <= Math.min(s - 1, m); i++) {
            int x = pos[i][0];
            int y = pos[i][1];
            find[x][y] = (x == con[i][0] && y == con[i][1]);
        }

        if (s <= m) {
            selectBase(s);
        }
    }

    private static void move(int idx) {
        int x = pos[idx][0];
        int y = pos[idx][1];
        int[][] dist = getDist(con[idx][0], con[idx][1]);

        int mx = -1;
        int my = -1;
        int md = INF;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isValid(nx, ny)) {
                continue;
            }
            if (md > dist[nx][ny]) {
                md = dist[nx][ny];
                mx = nx;
                my = ny;
            }
        }
        pos[idx][0] = mx;
        pos[idx][1] = my;
    }

    private static void selectBase(int idx) {
        int cx = con[idx][0];
        int cy = con[idx][1];
        int[][] dist = getDist(cx, cy);

        int mx = -1;
        int my = -1;
        int md = INF;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (base[i][j] == 1 && md > dist[i][j]) {
                    md = dist[i][j];
                    mx = i;
                    my = j;
                }
            }
        }
        pos[idx][0] = mx;
        pos[idx][1] = my;
        base[mx][my] = 2;
    }

    private static int[][] getDist(int x, int y) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y, 0 });

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[x][y] = 0;

        while (!dq.isEmpty()) {
            int[] pos = dq.pop();
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                int nd = pos[2] + 1;
                if (!isValid(nx, ny) || dist[nx][ny] != INF) {
                    continue;
                }
                dist[nx][ny] = nd;
                dq.add(new int[] { nx, ny, nd });
            }
        }
        return dist;
    }

    private static boolean isValid(int nx, int ny) {
        return !(nx < 0 || nx >= n || ny < 0 || ny >= n || base[nx][ny] == 2 || find[nx][ny]);
    }

    private static boolean isFinished() {
        for (int i = 1; i <= m; i++) {
            if (!(pos[i][0] == con[i][0] && pos[i][1] == con[i][1])) {
                return false;
            }
        }
        return true;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        base = new int[n][n];
        find = new boolean[n][n];
        con = new int[m + 1][2];
        pos = new int[m + 1][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                base[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            con[i][0] = Integer.parseInt(st.nextToken()) - 1;
            con[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
    }
}