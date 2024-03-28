package codetree.java;

import java.util.*;
import java.io.*;

/**
 * 150ms
 * 10MB
 * 변수 관리를 조금 더 타이트하게 해볼 것. (클래스 => 배열)
 * 함수가 의존성을 가짐. 독립적으로 개선해 볼 것.
 * 중복 코드가 많음.
 */
public class 왕실의기사대결 {
    static class Knight {
        int r, c, h, w, k, damage;

        Knight(int r, int c, int h, int w, int k) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.damage = 0;
        }
    }

    static class Order {
        int idx, dir;

        Order(int idx, int dir) {
            this.idx = idx;
            this.dir = dir;
        }
    }

    static final int[] dx = { -1, 0, 1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int L, N, Q, chess[][], field[][];
    static Knight[] knights;
    static Order[] orders;
    static boolean[] death;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < Q; i++) {
            simulate(i);
        }
        count();
    }

    private static void simulate(int orderIdx) {
        Order ord = orders[orderIdx];
        if (death[ord.idx])
            return;

        Knight knight = knights[ord.idx];
        int[][] temp = check(knight.r, knight.c, ord.dir);
        if (temp == null) {
            return;
        }

        boolean[] moved = move(temp, ord.dir);

        for (int i = 1; i <= N; i++) {
            if (moved[i]) {
                Knight k = knights[i];
                k.r += dx[ord.dir];
                k.c += dy[ord.dir];

                if (i == ord.idx)
                    continue;

                int dmg = getTrap(k);
                k.damage += dmg;
                k.k -= dmg;

                if (k.k <= 0) {
                    clean(i);
                }
            }
        }
    }

    private static boolean[] move(int[][] arr, int dir) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (arr[i][j] != 0) {
                    field[i][j] = 0;
                }
            }
        }

        boolean[] isMoved = new boolean[N + 1];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (arr[i][j] == 0)
                    continue;
                int nx = i + dx[dir];
                int ny = j + dy[dir];

                isMoved[arr[i][j]] = true;
                field[nx][ny] = arr[i][j];
            }
        }
        return isMoved;
    }

    private static int[][] check(int x, int y, int dir) {
        int[][] temp = new int[L][L];

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        boolean[][] visited = new boolean[L][L];
        visited[x][y] = true;

        while (!dq.isEmpty()) {
            int[] pos = dq.pop();
            int r = pos[0];
            int c = pos[1];

            if (!isRange(r + dx[dir], c + dy[dir]))
                return null;
            if (chess[r + dx[dir]][c + dy[dir]] == 2)
                return null;

            temp[r][c] = field[r][c];
            for (int i = 0; i < 4; i++) {
                int nx = r + dx[i];
                int ny = c + dy[i];
                if (!isRange(nx, ny))
                    continue;
                if (field[nx][ny] == 0)
                    continue;
                if (field[r][c] != field[nx][ny] && i != dir)
                    continue;
                if (visited[nx][ny])
                    continue;
                visited[nx][ny] = true;
                dq.add(new int[] { nx, ny });
            }
        }
        return temp;
    }

    private static int getTrap(Knight k) {
        int trap = 0;
        for (int i = k.r; i < k.r + k.h; i++) {
            for (int j = k.c; j < k.c + k.w; j++) {
                if (!isRange(i, j))
                    continue;
                if (chess[i][j] == 1) {
                    trap++;
                }
            }
        }
        return trap;
    }

    private static void clean(int idx) {
        death[idx] = true;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (field[i][j] == idx) {
                    field[i][j] = 0;
                }
            }
        }
    }

    private static void count() {
        int dmg = 0;
        for (int i = 1; i <= N; i++) {
            if (!death[i])
                dmg += knights[i].damage;
        }
        System.out.println(dmg);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < L && 0 <= y && y < L;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        chess = new int[L][L];
        field = new int[L][L];
        orders = new Order[Q];
        knights = new Knight[N + 1];
        death = new boolean[N + 1];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int n = r; n < r + h; n++) {
                for (int m = c; m < c + w; m++) {
                    field[n][m] = i;
                }
            }

            knights[i] = new Knight(r, c, h, w, k);
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            orders[i] = new Order(idx, d);
        }
    }
}