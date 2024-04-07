package codetree.java;

import java.util.*;
import java.io.*;

public class 싸움땅 {
    static class User {
        int x, y, d, o, idx;

        User(int x, int y, int d, int o, int idx) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.o = o;
            this.idx = idx;
        }
    }

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dx = { -1, 0, 1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int k;
    static int[] point;
    static int[] guns;
    static int[][] field;
    static User[] users;
    static PriorityQueue<Integer>[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < k; i++) {
            simulate();
        }
        printResult();
    }

    private static void simulate() {
        for (int i = 1; i <= m; i++) {
            User user = users[i];
            move(user);

            if (field[user.x][user.y] != 0) {
                fight(user);
            } else {
                compareAndGet(user);
            }
        }
    }

    private static void fight(User user) {
        User enemy = users[field[user.x][user.y]];

        int o1 = user.o;
        int o2 = enemy.o;

        int idx1 = user.idx;
        int idx2 = enemy.idx;

        int total1 = o1 + guns[idx1];
        int total2 = o2 + guns[idx2];

        User winner, loser;

        if (total1 > total2) {
            winner = user;
            loser = enemy;
        } else if (total1 < total2) {
            winner = enemy;
            loser = user;
        } else {
            winner = o1 > o2 ? user : enemy;
            loser = o1 > o2 ? enemy : user;
        }

        point[winner.idx] += total1 - total2;

        arr[loser.x][loser.y].add(guns[loser.idx]);
        guns[loser.idx] = 0;

        int d = loser.d;
        while (!(isRange(loser.x + dx[d], loser.y + dy[d]) && field[loser.x + dx[d]][loser.y + dy[d]] == 0)) {
            d = (d + 1) % 4;
        }

        int nx = loser.x + dx[d];
        int ny = loser.y + dy[d];
        users[loser.idx].x = nx;
        users[loser.idx].y = ny;
        users[loser.idx].d = d % 4;

        field[nx][ny] = loser.idx;
        compareAndGet(loser);

        field[winner.x][winner.y] = winner.idx;
        compareAndGet(winner);
    }

    private static void compareAndGet(User user) {
        int x = user.x;
        int y = user.y;
        int idx = user.idx;

        if (arr[x][y].size() > 0) {
            int max = arr[x][y].peek();
            if (guns[idx] != 0) {
                if (guns[idx] < max) {
                    arr[x][y].add(guns[idx]);
                    guns[idx] = arr[x][y].poll();
                }
            } else {
                guns[idx] = arr[x][y].poll();
            }
        }
        field[x][y] = idx;
    }

    private static void move(User user) {
        int x = user.x;
        int y = user.y;
        int d = user.d;
        int idx = user.idx;

        d = isRange(x + dx[d], y + dy[d]) ? d : convert(d);
        int nx = x + dx[d];
        int ny = y + dy[d];

        users[idx].x = nx;
        users[idx].y = ny;
        users[idx].d = d;

        field[x][y] = 0;
    }

    private static int convert(int d) {
        return d == 0 ? 2 : (d == 1 ? 3 : (d == 2 ? 0 : 1));
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static void printResult() {
        for (int i = 1; i <= m; i++) {
            System.out.print(point[i] + " ");
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new PriorityQueue[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = new PriorityQueue<>((o1, o2) -> o2 - o1);
                int val = Integer.parseInt(st.nextToken());
                if (val > 0) {
                    arr[i][j].add(val);
                }
            }
        }

        point = new int[m + 1];
        guns = new int[m + 1];
        users = new User[m + 1];
        field = new int[n][n];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            field[x][y] = i;
            users[i] = new User(x, y, d, s, i);
        }
    }
}
