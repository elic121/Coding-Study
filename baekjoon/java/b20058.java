import java.util.*;
import java.io.*;

public class b20058 {

    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static int N, Q, L, MAX_BLOCK, size[], arr[][];
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
        checkMaxBlock();
    }

    private static void simulate(int idx) {
        rotate(0, 0, size[idx], L);
        search();
    }

    private static void rotate(int x, int y, int s, int curr) {
        if (s == curr) {
            int[][] temp = new int[curr][curr];
            for (int i = 0; i < s; i++) {
                for (int j = 0; j < s; j++) {
                    temp[i][j] = arr[s + x - 1 - j][y + i];
                }
            }

            for (int i = 0; i < s; i++) {
                for (int j = 0; j < s; j++) {
                    arr[x + i][y + j] = temp[i][j];
                }
            }

            return;
        }

        int next = curr >>> 1;
        rotate(x, y, s, next);
        rotate(x + next, y, s, next);
        rotate(x, y + next, s, next);
        rotate(x + next, y + next, s, next);
    }

    private static void search() {
        boolean[][] temp = new boolean[L][L];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!isRange(nx, ny))
                        continue;
                    if (arr[nx][ny] == 0)
                        continue;
                    cnt++;
                }
                temp[i][j] = cnt < 3;
            }
        }

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (arr[i][j] == 0)
                    continue;
                arr[i][j] -= temp[i][j] ? 1 : 0;
            }
        }
    }

    private static void checkMaxBlock() {
        boolean[][] visited = new boolean[L][L];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (visited[i][j])
                    continue;
                if (arr[i][j] == 0)
                    continue;
                int cnt = 1;
                ArrayDeque<int[]> dq = new ArrayDeque<>();
                dq.add(new int[] { i, j });
                visited[i][j] = true;

                while (!dq.isEmpty()) {
                    int[] pos = dq.pop();
                    for (int k = 0; k < 4; k++) {
                        int nx = pos[0] + dx[k];
                        int ny = pos[1] + dy[k];
                        if (!isRange(nx, ny))
                            continue;
                        if (arr[nx][ny] == 0)
                            continue;
                        if (visited[nx][ny])
                            continue;
                        cnt++;
                        dq.add(new int[] { nx, ny });
                        visited[nx][ny] = true;
                    }
                }
                MAX_BLOCK = Math.max(MAX_BLOCK, cnt);
            }
        }
        System.out.println(MAX_BLOCK);
    }

    private static void count() {
        int cnt = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                cnt += arr[i][j];
            }
        }
        System.out.println(cnt);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < L && 0 <= y && y < L;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        L = (int) Math.pow(2, N);
        MAX_BLOCK = 0;

        arr = new int[L][L];
        size = new int[Q];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            size[i] = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        }
    }
}
