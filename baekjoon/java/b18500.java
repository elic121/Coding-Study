import java.util.*;
import java.io.*;

public class b18500 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int r;
    static int c;
    static int n;
    static int[] height;
    static boolean[][] visited;
    static boolean[][] connected;
    static boolean[][] temp;
    static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        simulate();
        printAnswer();
    }

    private static void simulate() {
        for (int i = 0; i < n; i++) {
            if (throwStick(i)) {
                check();
                drop();
            }
        }
    }

    private static boolean throwStick(int order) {
        boolean even = order % 2 == 0;
        int x = r - height[order];

        for (int y = even ? 0 : (c - 1); y != (even ? c : -1); y += even ? 1 : -1) {
            if (arr[x][y]) {
                arr[x][y] = false;
                return true;
            }
        }
        return false;
    }

    private static void check() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                visited[i][j] = false;
            }
        }

        for (int col = 0; col < c; col++) {
            if (!visited[r - 1][col] && arr[r - 1][col]) {
                ArrayDeque<int[]> dq = new ArrayDeque<>();
                dq.add(new int[] { r - 1, col });

                visited[r - 1][col] = true;

                while (!dq.isEmpty()) {
                    int[] p = dq.pop();

                    for (int d = 0; d < 4; d++) {
                        int nx = p[0] + dx[d];
                        int ny = p[1] + dy[d];

                        if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                            continue;
                        }

                        if (!visited[nx][ny] && arr[nx][ny]) {
                            visited[nx][ny] = true;
                            dq.add(new int[] { nx, ny });
                        }
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            connected[i] = Arrays.copyOf(visited[i], c);
        }
    }

    private static void drop() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                visited[i][j] = false;
                temp[i][j] = false;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] && !connected[i][j] && !visited[i][j]) {
                    dropSub(i, j);
                    return;
                }
            }
        }
    }

    private static void dropSub(int i, int j) {
        visited[i][j] = true;

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { i, j });

        ArrayDeque<int[]> save = new ArrayDeque<>();
        save.add(new int[] { i, j });

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    continue;
                }

                if (!visited[nx][ny] && arr[nx][ny] && !connected[nx][ny]) {
                    visited[nx][ny] = true;
                    dq.add(new int[] { nx, ny });
                    save.add(new int[] { nx, ny });
                }
            }
        }

        int move = 0;
        int e = save.size();
        end: for (int m = 1; m < r; m++) {
            for (int s = 0; s < e; s++) {
                int[] p = save.pop();
                int nx = p[0] + m;
                int ny = p[1];

                if (nx == r) {
                    move = m - 1;
                    save.add(p);
                    break end;
                }

                if (arr[nx][ny] && !connected[nx][ny]) {
                    save.add(p);
                    continue;
                }

                if (arr[nx][ny] && connected[nx][ny]) {
                    move = m - 1;
                    save.add(p);
                    break end;
                }

                save.add(p);
            }
        }

        while (!save.isEmpty()) {
            int[] p = save.pop();
            int nx = p[0] + move;
            int ny = p[1];

            arr[p[0]][p[1]] = false;
            temp[nx][ny] = true;
        }

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (temp[x][y]) {
                    arr[x][y] = true;
                }
            }
        }
    }

    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(arr[i][j] ? 'x' : '.');
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new boolean[r][c];
        temp = new boolean[r][c];
        visited = new boolean[r][c];
        connected = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                arr[i][j] = ch[j] == 'x';
            }
        }

        n = Integer.parseInt(br.readLine());
        height = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
    }
}
