import java.util.*;
import java.io.*;

public class b2174 {
    static class Robot {
        int d, idx;

        Robot(int d, int idx) {
            this.d = d;
            this.idx = idx;
        }
    }

    static class Command {
        int id, iter;
        char c;

        Command(int id, char c, int iter) {
            this.id = id;
            this.c = c;
            this.iter = iter;
        }
    }

    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int A, B, N, M;
    static Robot arr[][];
    static Command com[];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() throws IOException {
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int iter = Integer.parseInt(st.nextToken());
            com[i] = new Command(id, c, iter);
        }

        for (int i = 0; i < M; i++) {
            int id = com[i].id;
            char c = com[i].c;
            int iter = com[i].iter;

            int[] result = simulate(id, c, iter);
            if (result[0] == 1) {
                crashWall(id);
                return;
            } else if (result[0] == 2) {
                crashRobot(id, result[1]);
                return;
            }
        }
        System.out.println("OK\n");
    }

    private static int[] simulate(int id, char c, int iter) {
        Robot r = null;
        int x = -1;
        int y = -1;
        end: for (int i = 0; i < B; i++) {
            for (int j = 0; j < A; j++) {
                if (arr[i][j] == null)
                    continue;
                if (arr[i][j].idx == id) {
                    r = arr[i][j];
                    x = i;
                    y = j;
                    break end;
                }
            }
        }

        if (c == 'L') {
            r.d = (r.d - iter) % 4;
            r.d += r.d < 0 ? 4 : 0;
            return new int[] { 0 };
        } else if (c == 'R') {
            r.d = (r.d + iter) % 4;
            return new int[] { 0 };
        } else {
            for (int s = 1; s <= iter; s++) {
                int nx = x + s * dx[r.d];
                int ny = y + s * dy[r.d];
                if (!isRange(nx, ny))
                    return new int[] { 1 };
                if (arr[nx][ny] != null)
                    return new int[] { 2, arr[nx][ny].idx };
            }
            arr[x + iter * dx[r.d]][y + iter * dy[r.d]] = r;
            arr[x][y] = null;
            return new int[] { 0 };
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        com = new Command[M];

        arr = new Robot[B][A];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            arr[x - 1][y - 1] = new Robot(convertCommand(c), i);
        }
    }

    private static void crashRobot(int idx, int idx2) {
        System.out.printf("Robot %d crashes into robot %d\n", idx, idx2);
    }

    private static void crashWall(int idx) {
        System.out.printf("Robot %d crashes into the wall\n", idx);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < B && 0 <= y && y < A;
    }

    private static int convertCommand(char c) {
        if (c == 'N') {
            return 0;
        } else if (c == 'W') {
            return 3;
        } else if (c == 'E') {
            return 1;
        } else {
            return 2;
        }
    }
}