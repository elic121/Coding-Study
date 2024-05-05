import java.io.*;
import java.util.ArrayDeque;

public class 파핑파핑지뢰찾기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dsx = { 0, 1, 0, -1, -1, -1, 1, 1 };
    static final int[] dsy = { 1, 0, -1, 0, -1, 1, -1, 1 };
    static int t;
    static int n;
    static int[][] nums;
    static char[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            init();
            solve(i);
        }
    }

    private static void solve(int t) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '*' || visited[i][j] || nums[i][j] != 0) {
                    continue;
                }
                cnt += bfs(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && arr[i][j] != '*') {
                    cnt++;
                }
            }
        }

        System.out.println("#" + t + " " + cnt);
    }

    private static int bfs(int x, int y) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        visited[x][y] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            for (int i = 0; i < 8; i++) {
                int nx = p[0] + dsx[i];
                int ny = p[1] + dsy[i];
                if (!isRange(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == '*') {
                    continue;
                }

                if (nums[nx][ny] == 0) {
                    dq.add(new int[] { nx, ny });
                }
                visited[nx][ny] = true;
            }
        }
        return 1;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        nums = new int[n][n];
        visited = new boolean[n][n];
        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 8; d++) {
                    int nx = i + dsx[d];
                    int ny = j + dsy[d];
                    if (isRange(nx, ny) && arr[nx][ny] == '*') {
                        nums[i][j]++;
                    }
                }
            }
        }
    }
}
