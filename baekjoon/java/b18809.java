import java.util.*;
import java.io.*;

public class b18809 {
    static class Field {
        int t;
        int type;

        public Field(int t, int type) {
            this.t = t;
            this.type = type;
        }
    }

    static class Data {
        int x;
        int y;
        Field field;

        public Data(int x, int y, int t, int type) {
            this.x = x;
            this.y = y;
            this.field = new Field(t, type);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int g;
    static int r;
    static int land;
    static int count;
    static int max;
    static int[][] arr;
    static Field[][] copy;
    static ArrayList<Integer> temp;
    static ArrayList<int[]> poss;
    static ArrayList<ArrayList<Integer>> state;
    static ArrayList<ArrayList<Integer>> order;
    static ArrayDeque<Data> dq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        comb(0, 0, new ArrayList<>());
        System.out.println(max);
    }

    private static void simulate(ArrayList<Integer> st, ArrayList<Integer> or) {
        reset();

        for (int i = 0; i < r + g; i++) {
            int[] pos = poss.get(st.get(i));
            int x = pos[0];
            int y = pos[1];
            int type = or.get(i) == 0 ? 4 : 5;
            copy[x][y] = new Field(1, type);
            dq.add(new Data(x, y, 1, type));

        }

        bfs();
        max = Math.max(max, count);
    }

    private static void bfs() {
        while (!dq.isEmpty()) {
            int size = dq.size();

            for (int i = 0; i < size; i++) {
                Data p = dq.pop();

                if (copy[p.x][p.y].type == -1) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];

                    // 범위를 벗어나는 경우
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    // 호수인 경우
                    if (arr[nx][ny] == 0) {
                        continue;
                    }

                    // 비어있는 공간인 경우
                    if (copy[nx][ny] == null) {
                        copy[nx][ny] = new Field(p.field.t + 1, p.field.type);
                        dq.add(new Data(nx, ny, p.field.t + 1, p.field.type));
                        continue;
                    }

                    // 꽃이 핀 경우
                    if (copy[nx][ny].type == -1) {
                        continue;
                    }

                    // 같은 색인 경우
                    if (copy[nx][ny].type == p.field.type) {
                        continue;
                    }

                    // 다른 색이면서 시간이 다른 경우
                    if (copy[nx][ny].type != p.field.type && copy[nx][ny].t != p.field.t + 1) {
                        continue;
                    }

                    copy[nx][ny] = new Field(-1, -1);
                    count++;
                }
            }
        }
    }

    private static void subset(int depth, int red, int gre, ArrayList<Integer> subset) {
        if (depth == r + g) {
            simulate(temp, subset);
            return;
        }

        if (red < r) {
            subset.add(0);
            subset(depth + 1, red + 1, gre, subset);
            subset.remove(subset.size() - 1);
        }

        if (gre < g) {
            subset.add(1);
            subset(depth + 1, red, gre + 1, subset);
            subset.remove(subset.size() - 1);
        }
    }

    private static void comb(int depth, int curr, ArrayList<Integer> arr) {
        if (depth == r + g) {
            temp = arr;
            subset(0, 0, 0, new ArrayList<>());
            return;
        }

        for (int i = curr; i < land; i++) {
            arr.add(i);
            comb(depth + 1, i + 1, arr);
            arr.remove(arr.size() - 1);
        }
    }

    private static void reset() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(copy[i], null);
        }
        count = 0;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        max = 0;
        land = 0;
        count = 0;

        copy = new Field[n][m];
        arr = new int[n][m];
        poss = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 2) {
                    poss.add(new int[] { i, j });
                    land++;
                }
            }
        }

        temp = new ArrayList<>();
        dq = new ArrayDeque<>();
    }
}