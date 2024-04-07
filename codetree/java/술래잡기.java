package codetree.java;

import java.util.*;
import java.io.*;

public class 술래잡기 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dx = { -1, 0, 1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int h;
    static int k;
    static int cx;
    static int cy;
    static int cd;
    static int cs;
    static int cm;
    static int score;
    static int[][] runner;
    static int[][] trees;
    static boolean oppsite;
    static boolean[] out;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 1; i <= k; i++) {
            simulate(i);
        }
        System.out.println(score);
    }

    private static void simulate(int turn) {
        moveRunner();
        moveChaser(turn);
    }

    private static void moveChaser(int turn) {
        cx += dx[cd];
        cy += dy[cd];
        cs--;

        set();

        if (oppsite) {
            nextReverse();
        } else {
            nextForward();
        }

        arrest(turn);
    }

    private static void set() {
        if (cx == 0 && cy == 0) {
            oppsite = true;
            cd = 3;
            cs = 0;
            cm = 2 * n - 1;
        }
        if (cx == n / 2 && cy == n / 2) {
            oppsite = false;
            cd = 0;
            cs = 1;
            cm = 0;
        }
    }

    private static void moveRunner() {
        for (int i = 1; i <= m; i++) {
            if (out[i]) {
                continue;
            }
            int x = runner[i][0];
            int y = runner[i][1];
            int d = runner[i][2];

            if (getDist(x, y, cx, cy) > 3) {
                continue;
            }

            d = isRange(x + dx[d], y + dy[d]) ? d : convert(d);
            runner[i][2] = d;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!(cx == nx && cy == ny)) {
                runner[i][0] = nx;
                runner[i][1] = ny;
            }
        }
    }

    private static void arrest(int turn) {
        int cnt = 0;
        int nx = cx;
        int ny = cy;
        for (int i = 0; i < 3; i++) {
            if (!isRange(nx, ny)) {
                break;
            }
            if (trees[nx][ny] > 0) {
                nx += dx[cd];
                ny += dy[cd];
                continue;
            }
            for (int r = 1; r <= m; r++) {
                if (out[r]) {
                    continue;
                }
                if (runner[r][0] == nx && runner[r][1] == ny) {
                    out[r] = true;
                    cnt++;
                }
            }
            nx += dx[cd];
            ny += dy[cd];
        }
        score += turn * cnt;
    }

    private static void nextReverse() {
        if (cs > 0 && !(cx == n - 1 && cy == 0)) {
            return;
        }
        cm--;
        cs = 1 + (cm / 2);
        cd = (cd + 3) % 4;
    }

    private static void nextForward() {
        if (cs > 0) {
            return;
        }
        cm++;
        cs = 1 + (cm / 2);
        cd = (cd + 1) % 4;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static int convert(int d) {
        return (d + 6) % 4;
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        score = 0;
        cx = cy = n / 2;
        cm = cd = 0;
        cs = 1 + cm / 2;
        oppsite = false;

        out = new boolean[m + 1];
        runner = new int[m + 1][3];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            runner[i][0] = x;
            runner[i][1] = y;
            runner[i][2] = d;
        }

        trees = new int[n][n];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            trees[x][y] = 1;
        }
    }
}

/**
 * 실수 목록
 * 1. arrest : 위치 갱신하지 않고 continue함
 * 2. moveRunner : 도망자의 방향을 갱신하지 않음
 */