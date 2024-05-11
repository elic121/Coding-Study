import java.util.*;
import java.io.*;

public class b13565 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int m;
    static boolean[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < m; i++) {
            if (arr[0][i] && bfs(0, i)) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
    
    private static boolean bfs(int x, int y) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        visited[x][y] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == n - 1) {
                return true;
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (!arr[nx][ny]) {
                    continue;
                }
                dq.add(new int[] { nx, ny });
                visited[nx][ny] = true;
            }
        }
        return false;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        arr = new boolean[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = c[j] == '0';
            }
        }
    }
}