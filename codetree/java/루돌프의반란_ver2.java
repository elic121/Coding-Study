package codetree.java;

import java.util.*;
import java.io.*;

/**
 * 130ms
 * 9MB
 */
public class 루돌프의반란_ver2 {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dx = { -1, 0, 1, 0, -1, -1, 1, 1 };
    static final int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };
    static int N, M, P, C, D;
    static int RX, RY;
    static boolean[] death;
    static int[] stop, score;
    static int[][] sp, arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < M; i++) {
            if (simulate()) {
                break;
            }
            readyToMove();
            getScore();
        }
        printScore();
    }

    private static boolean simulate() {
        rMove();
        sMove();
        for (int i = 1; i <= P; i++) {
            if (!death[i]) {
                return false;
            }
        }
        return true;
    }

    private static void rMove() {
        int nx = -1;
        int ny = -1;
        int dist = Integer.MAX_VALUE;
        int target = -1;

        for (int i = 1; i <= P; i++) {
            if (death[i]) {
                continue;
            }

            int tx = sp[i][0];
            int ty = sp[i][1];
            int tempDist = getDistance(RX, RY, tx, ty);

            if (tempDist < dist) {
                dist = tempDist;
                target = i;
                nx = tx;
                ny = ty;
            } else if (tempDist == dist) {
                if (nx < tx || (nx == tx && ny < ty)) {
                    target = i;
                    nx = tx;
                    ny = ty;
                }
            }
        }

        int pivot = getDistance(RX, RY, sp[target][0], sp[target][1]);
        int dir = -1;
        for (int i = 0; i < 8; i++) {
            int rx = RX + dx[i];
            int ry = RY + dy[i];

            if (!isRange(rx, ry)) {
                continue;
            }

            int tempDist = getDistance(rx, ry, sp[target][0], sp[target][1]);
            if (tempDist >= pivot) {
                continue;
            }

            dir = i;
            pivot = tempDist;
        }

        RX += dx[dir];
        RY += dy[dir];

        if (arr[RX][RY] != 0) {
            interact(arr[RX][RY], dx[dir], dy[dir], C, true);
        }
    }

    private static void sMove() {
        for (int i = 1; i <= P; i++) {
            if (death[i] || stop[i] > 0) {
                continue;
            }
            ;
            int dir = -1;
            int pivot = getDistance(sp[i][0], sp[i][1], RX, RY);

            for (int d = 0; d < 4; d++) {
                int nx = sp[i][0] + dx[d];
                int ny = sp[i][1] + dy[d];
                if (!isRange(nx, ny) || arr[nx][ny] != 0) {
                    continue;
                }

                int tempDist = getDistance(nx, ny, RX, RY);
                if (tempDist >= pivot) {
                    continue;
                }

                pivot = tempDist;
                dir = d;
            }

            if (dir == -1) {
                continue;
            }

            arr[sp[i][0]][sp[i][1]] = 0;
            sp[i][0] += dx[dir];
            sp[i][1] += dy[dir];
            arr[sp[i][0]][sp[i][1]] = i;

            if (sp[i][0] == RX && sp[i][1] == RY) {
                interact(i, dx[dir], dy[dir], D, false);
            }
        }
    }

    private static void interact(int idx, int mx, int my, int s, boolean rudolf) {
        arr[RX][RY] = 0;
        score[idx] += s;
        stop[idx] = 2;

        int gx = mx * (rudolf ? 1 : -1);
        int gy = my * (rudolf ? 1 : -1);

        int x = RX + s * gx;
        int y = RY + s * gy;
        if (!isRange(x, y)) {
            death[idx] = true;
            return;
        }

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(idx);
        while (isRange(x, y) && arr[x][y] != 0) {
            dq.add(arr[x][y]);
            x += gx;
            y += gy;
        }

        int nx = RX + s * gx;
        int ny = RY + s * gy;
        while (isRange(nx, ny) && !dq.isEmpty()) {
            int value = dq.pop();
            arr[nx][ny] = value;
            sp[value][0] = nx;
            sp[value][1] = ny;
            nx += gx;
            ny += gy;
        }

        if (!dq.isEmpty()) {
            int d = dq.pop();
            death[d] = true;
        }
    }

    private static void readyToMove() {
        for (int i = 1; i <= P; i++) {
            if (stop[i] > 0) {
                stop[i]--;
            }
        }
    }

    private static void getScore() {
        for (int i = 1; i <= P; i++) {
            if (!death[i]) {
                score[i]++;
            }
        }
    }

    private static void printScore() {
        for (int i = 1; i <= P; i++) {
            System.out.print(score[i] + " ");
        }
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    private static boolean isRange(int x, int y) {
        return 0 < x && x <= N && 0 < y && y <= N;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        stop = new int[P + 1];
        score = new int[P + 1];
        sp = new int[P + 1][2];
        death = new boolean[P + 1];

        st = new StringTokenizer(br.readLine());
        RX = Integer.parseInt(st.nextToken());
        RY = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= P; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x][y] = idx;
            sp[idx] = new int[] { x, y };
        }
    }
}
