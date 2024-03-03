import java.util.*;
import java.io.*;

public class b5656 {

    static int T, N, W, H, MIN, order[], arr[][], test[][];
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            solve(tc);
        }
        System.out.println(sb.toString());
    }

    private static void solve(int tc) {
        permWithReplacement(0);
        sb.append('#').append(tc).append(" ").append(MIN).append("\n");
    }

    private static void permWithReplacement(int depth) {
        if (depth == N) {
            test = deepcopy();
            for (int c : order) {
                simulate(c);
                drop();
            }
            countBlock();
            return;
        }

        for (int i = 0; i < W; i++) {
            order[depth] = i;
            permWithReplacement(depth + 1);
        }
    }

    private static void simulate(int c) {
        int r = 0;
        while (r < H && test[r][c] == 0) r++;

        if (r == H) return;

        boolean[][] visited = new boolean[H][W];

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] {r, c});

        while (!dq.isEmpty()) {
            int[] pos = dq.poll();
            int L = test[pos[0]][pos[1]];
            test[pos[0]][pos[1]] = 0;
            
            for (int idx = 0; idx < 4; idx++) {
                for (int s = 0; s < L; s++) {
                    int nx = pos[0] + s * dx[idx];
                    int ny = pos[1] + s * dy[idx];
                    
                    if (!isRange(nx, ny)) continue;
                    if (test[nx][ny] == 0) continue;
                    if (visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    dq.add(new int[] {nx, ny});
                }
            }
        }
    }

    private static void drop() {
        for (int j = 0; j < W; j++) {
            for (int i = H - 1; i >= 0; i--) {
                if (test[i][j] != 0) continue;
                for (int k = i - 1; k >= 0; k--) {
                    if (test[k][j] != 0) {
                        test[i][j] = test[k][j];
                        test[k][j] = 0;
                        break;
                    }
                }
            }
        }
    }

    private static void countBlock() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (test[i][j] > 0) {
                    cnt++;
                }
            }
        }

        MIN = Math.min(MIN, cnt);
    }

    private static int[][] deepcopy() {
        int[][] temp = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        MIN = Integer.MAX_VALUE;
        order = new int[N];
        
        arr = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }   
}
