import java.util.*;
import java.io.*;

public class b9328 {
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int h;
    static int w;
    static int key;
    static int max;
    static int cnt;
    static int curr;
    static char[][] arr;
    static int[][] visited;
    static boolean[][] docs;
    static ArrayDeque<int[]> dq;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            init();
            solve();
            print();
        }
    }

    private static void print() {
        System.out.println(max);
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];
                int curr = p[2];

                if (!isRange(nx, ny)) {
                    continue;
                }

                if (arr[nx][ny] == '*') {
                    continue;
                }

                if (arr[nx][ny] >= 'A' && arr[nx][ny] <= 'Z') {
                    int level = arr[nx][ny] - 'A';
                    if ((curr & 1 << level) == 0) {
                        continue;
                    }
                }

                if (arr[nx][ny] >= 'a' && arr[nx][ny] <= 'z') {
                    curr |= 1 << (arr[nx][ny] - 'a');
                }

                if (visited[nx][ny] == curr) {
                    continue;
                }

                if (arr[nx][ny] == '$' && !docs[nx][ny]) {
                    docs[nx][ny] = true;
                    max++;

                    if (cnt == max) {
                        return;
                    }
                }

                visited[nx][ny] = curr;
                dq.add(new int[] { nx, ny, curr });
            }
        }

    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx < h && 0 <= ny && ny < w;
    }

    private static void fillQue(int x, int y) {
        if (arr[x][y] == '*') {
            return;
        }

        if (arr[x][y] >= 'a' && arr[x][y] <= 'z') {
            key |= 1 << (arr[x][y] - 'a');
        }

        if (arr[x][y] >= 'A' && arr[x][y] <= 'Z') {
            int level = arr[x][y] - 'A';
            if ((key & 1 << level) == 0) {
                return;
            }
        }

        dq.add(new int[] { x, y, key });
        visited[x][y] = key;
    }

    private static void init() throws IOException {
        key = 0;
        curr = 0;
        max = 0;

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new char[h][w];
        docs = new boolean[h][w];
        visited = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(visited[i], -1);
        }

        for (int i = 0; i < h; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                cnt += arr[i][j] == '$' ? 1 : 0;
            }
        }

        char[] ch = br.readLine().toCharArray();
        if (ch[0] != '0') {
            for (int i = 0; i < ch.length; i++) {
                key |= 1 << (ch[i] - 'a');
            }
        }

        dq = new ArrayDeque<>();
        for (int i = 0; i < h; i++) {
            fillQue(i, 0);
            fillQue(i, w - 1);
        }
        for (int j = 0; j < w; j++) {
            fillQue(0, j);
            fillQue(h - 1, j);
        }
    }
}