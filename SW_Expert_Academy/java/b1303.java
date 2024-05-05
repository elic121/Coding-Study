import java.util.*;
import java.io.*;

public class b1303 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static int w;
    static int b;
    static boolean[][] visited;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        w = 0;
        b = 0;

        arr = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(w + " " + b);
    }

    private static void bfs(int x, int y) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        visited[x][y] = true;
        char pivot = arr[x][y];
        int cnt = 1;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || arr[nx][ny] != pivot) {
                    continue;
                }

                dq.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                cnt++;
            }
        }

        int q = pivot == 'B' ? (b += cnt * cnt) : (w += cnt * cnt);
    }
}
