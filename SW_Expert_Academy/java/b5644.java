
/**
 *  실행시간:   124 ms
 *  메모리  :   22,648 kb
 */

import java.io.*;
import java.util.*;

public class b5644 {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Charger {
        Point pos;
        int c, p;

        Charger(int x, int y, int c, int p) {
            this.pos = new Point(x, y);
            this.c = c;
            this.p = p;
        }
    }

    static int T, M, A, score;
    static int[] userA, userB;
    static int arr[][];
    static Point PA, PB;
    static Charger[] chargers;
    static final int[] dy = { 0, 0, 1, 0, -1 };
    static final int[] dx = { 0, -1, 0, 1, 0 };
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int i = 1; i <= T; i++) {

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            arr = new int[11][11];
            chargers = new Charger[A + 1];

            PA = new Point(1, 1);
            PB = new Point(10, 10);
            score = 0;

            for (int j = 0; j < 11; j++) {
                Arrays.fill(arr[j], 1);
            }

            userA = new int[M + 1];
            userB = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                userA[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                userB[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 1; j <= A; j++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                chargers[j] = new Charger(Y, X, C, P);
                init(Y, X, C, P, j);
            }

            simulate(i);
        }

        System.out.println(sb.toString());
    }

    private static void simulate(int index) {
        for (int i = 0; i <= M; i++) {
            move(i);
        }
        sb.append("#").append(index).append(" ").append(score).append("\n");
    }

    private static void move(int index) {
        PA.x += dx[userA[index]];
        PA.y += dy[userA[index]];

        PB.x += dx[userB[index]];
        PB.y += dy[userB[index]];

        int currentA = arr[PA.x][PA.y];
        int currentB = arr[PB.x][PB.y];

        int MAX = 0;

        for (int i = 1; i <= A; i++) {
            for (int j = 1; j <= A; j++) {
                int ap = (currentA & 1 << i) != 0 ? 1 : 0;
                int bp = (currentB & 1 << j) != 0 ? 1 : 0;
                if (ap == 0 && bp == 0)
                    continue;
                if (i == j) {
                    MAX = Math.max(MAX, chargers[i].p * ap
                            + chargers[j].p * bp
                            - chargers[i].p * (ap * bp));
                } else {
                    MAX = Math.max(MAX, chargers[i].p * ap + chargers[j].p * bp);
                }
            }
        }
        score += MAX;
    }

    private static void init(int x, int y, int c, int p, int index) {
        for (int i = x - c; i <= x + c; i++) {
            for (int j = y - c; j <= y + c; j++) {
                if (!isRange(i, j) || distance(i, j, x, y) > c)
                    continue;
                arr[i][j] |= 1 << index;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 1 <= x && x < 11 && 1 <= y && y < 11;
    }

    private static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}