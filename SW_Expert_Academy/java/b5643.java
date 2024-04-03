import java.util.*;
import java.io.*;

public class b5643 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, M, CNT;
    static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            init();
            solve(i);
        }
    }

    private static void solve(int idx) {
        for (int i = 0; i < N; i++) {
            CNT += isComparable(i) ? 1 : 0;
        }
        System.out.printf("#%d %d\n", idx, CNT);
    }

    private static boolean isComparable(int stt) {
        return bfs(stt, true) + bfs(stt, false) == N - 1;
    }

    private static int bfs(int node, boolean f) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(node);

        boolean[] visited = new boolean[N];
        visited[node] = true;

        int cnt = 0;
        while (!dq.isEmpty()) {
            int v = dq.pop();
            for (int i = 0; i < N; i++) {
                if (!arr[f ? v : i][f ? i : v] || visited[i]) {
                    continue;
                }
                visited[i] = true;
                dq.add(i);
                cnt++;
            }
        }
        return cnt;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        CNT = 0;

        arr = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            arr[a][b] = true;
        }
    }
}