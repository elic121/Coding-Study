import java.util.*;
import java.io.*;

public class b1600 {

    static int K, N, M, arr[][];
    static final int[] dx = {1, 0, -1, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] dy = {0, -1, 0, 1, -2, -1, 1, 2, -2, -1, 1, 2};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static int bfs() {
        arr[0][0] |= 1 << 1;
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] {0, 0, 0, 0});

        while (!dq.isEmpty()) {
            int[] pos = dq.pop();

            if (pos[0] == N - 1 && pos[1] == M - 1) return pos[3];
            
            for (int i = 0; i < 12; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                
                if (pos[2] >= K && i >= 4) continue;
                int cnt = pos[2] + (i >= 4 ? 1 : 0);

                if (!isRange(nx, ny)) continue;
                if ((arr[nx][ny] & 1 << 0) != 0) continue;
                if ((arr[nx][ny] & 1 << cnt) != 0) continue;

                arr[nx][ny] |= 1 << cnt;
                dq.add(new int[] {nx, ny, cnt, pos[3] + 1});
            }
        }
        return -1;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < M;
    }
    
    private static void init() throws IOException {
        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
