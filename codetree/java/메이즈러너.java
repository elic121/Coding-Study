package codetree.java;

import java.util.*;
import java.io.*;

/**
 * 144ms
 * 9MB
 */
public class 메이즈러너 {

    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };
    static int N, M, K, move[], arr[][], guest[][];
    static int EX, EY;
    static boolean[] exit;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < K; i++) {
            simulate();
        }
        printAnswer();
    }

    private static void simulate() {
        moveGuest();
        if (isAllExit()) {
            printAnswer();
            System.exit(0);
        }
        rotate();
    }

    private static void moveGuest() {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (guest[i][j] == 0) {
                    continue;
                }
                for (int m = 1; m <= M; m++) {
                    if ((guest[i][j] & 1 << m) != 0) {
                        int[] next = whereToGo(i, j);
                        move[m] += (next[0] == i && next[1] == j) ? 0 : 1;

                        guest[i][j] ^= 1 << m;
                        if (next[0] == EX && next[1] == EY) {
                            exit[m] = true;
                        } else {
                            temp[next[0]][next[1]] |= 1 << m;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j] == 0) {
                    continue;
                }
                for (int m = 1; m <= M; m++) {
                    if ((temp[i][j] & 1 << m) != 0) {
                        guest[i][j] |= 1 << m;
                    }
                }
            }
        }
    }

    private static int[] whereToGo(int x, int y) {
        int pivot = getDist(x, y, EX, EY);
        int d = -1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isRange(nx, ny)) {
                continue;
            }
            if (arr[nx][ny] > 0) {
                continue;
            }

            int tempDist = getDist(nx, ny, EX, EY);
            if (tempDist >= pivot) {
                continue;
            }

            pivot = tempDist;
            d = i;
        }

        if (d == -1) {
            return new int[] { x, y };
        } else {
            return new int[] { x + dx[d], y + dy[d] };
        }
    }

    private static void rotate() {
        int[] param = findLeastSquare();
        int x = param[0];
        int y = param[1];
        int s = param[2];

        int[][] tempGuest = new int[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                tempGuest[i][j] = guest[x + i][y + j];
            }
        }

        int[][] tempArr = new int[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                tempArr[i][j] = arr[x + i][y + j];
            }
        }

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                guest[x + i][y + j] = tempGuest[s - j - 1][i];
            }
        }

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                arr[x + i][y + j] = tempArr[s - j - 1][i];
                arr[x + i][y + j] -= arr[x + i][y + j] > 0 ? 1 : 0;
            }
        }

        int ex = -1;
        int ey = -1;
        end: for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (arr[x + i][y + j] == -1) {
                    ex = x + i;
                    ey = y + j;
                    arr[x + i][y + j] = 0;
                    break end;
                }
            }
        }

        EX = ex;
        EY = ey;
        arr[EX][EY] = -1;
    }

    private static int[] findLeastSquare() {
        for (int s = 2; s <= N; s++) {
            for (int i = 0; i <= N - s; i++) {
                for (int j = 0; j <= N - s; j++) {
                    boolean isExistHuman = false;
                    boolean isExistExit = false;
                    for (int r = i; r < i + s; r++) {
                        for (int c = j; c < j + s; c++) {
                            for (int m = 1; m <= M; m++) {
                                if ((guest[r][c] & 1 << m) != 0) {
                                    isExistHuman = true;
                                    break;
                                }
                            }
                            if (r == EX && c == EY) {
                                isExistExit = true;
                            }
                        }
                    }
                    if (isExistHuman && isExistExit) {
                        return new int[] { i, j, s };
                    }
                }
            }
        }
        return null;
    }

    private static void printAnswer() {
        int cnt = 0;
        for (int i = 1; i <= M; i++) {
            cnt += move[i];
        }
        System.out.println(cnt);
        System.out.println((EX + 1) + " " + (EY + 1));
    }

    private static boolean isAllExit() {
        for (int i = 1; i <= M; i++) {
            if (!exit[i]) {
                return false;
            }
        }
        return true;
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        move = new int[M + 1];
        exit = new boolean[M + 1];
        arr = new int[N][N];
        guest = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            guest[x - 1][y - 1] |= 1 << i;
        }

        st = new StringTokenizer(br.readLine());
        EX = Integer.parseInt(st.nextToken()) - 1;
        EY = Integer.parseInt(st.nextToken()) - 1;

        arr[EX][EY] = -1;
    }
}
