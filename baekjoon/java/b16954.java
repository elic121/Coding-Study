import java.util.*;
import java.io.*;

public class b16954 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
    static final int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { 7, 0 });

        int cnt = 0;
        while (!dq.isEmpty()) {
            if (cnt >= 8) {
                System.out.println(1);
                return;
            }

            for (int i = 0, size = dq.size(); i < size; i++) {
                int[] p = dq.pop();
                int x = p[0];
                int y = p[1];

                if (arr[x][y]) {
                    continue;
                }

                for (int d = 0; d < dx.length; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) {
                        continue;
                    }
                    if (arr[nx][ny]) {
                        continue;
                    }

                    dq.add(new int[] { nx, ny });
                }
            }

            arr = next();
            cnt++;
        }
        System.out.println(0);
    }

    private static void init() throws IOException {
        arr = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = c[j] == '#';
            }
        }
    }

    private static boolean[][] next() {
        boolean[][] temp = new boolean[8][8];

        for (int i = 1; i < 8; i++) {
            temp[i] = Arrays.copyOf(arr[i - 1], 8);
        }

        return temp;
    }
}