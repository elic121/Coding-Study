import java.util.*;
import java.io.*;

public class 영준이의진짜BFS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static final int DEPTH = 17;
    static int t;
    static int n;
    static int[] depth;
    static int[] parents;
    static int[][] dp;
    static ArrayList<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            init();
            solve(i);
        }
        System.out.print(sb.toString());
    }

    private static void solve(int t) {
        int cnt = 0;
        if (n != 1) {
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            dq.add(1);
            int curr = 1;
            while (!dq.isEmpty()) {
                int v = dq.pop();
                if (!nodes[v].isEmpty()) {
                    for (int i : nodes[v]) {
                        cnt += calc(curr, i);
                        dq.add(i);
                        curr = i;
                    }
                }
            }
        } else {
            cnt = 0;
        }

        sb.append("#").append(t).append(" ").append(cnt).append("\n");
    }

    private static int calc(int x, int y) {
        int iter = 0;

        int depthX = depth[x];
        int depthY = depth[y];
        while (depthX != depthY) {
            if (depthX > depthY) {
                x = parents[x];
            } else {
                y = parents[y];
            }
            depthX = depth[x];
            depthY = depth[y];
            iter++;
        }

        while (x != y) {
            int jump = 0;
            while (dp[x][jump + 1] != dp[y][jump + 1]) {
                jump++;
            }
            iter += 2 * Math.pow(2, jump);
            x = dp[x][jump];
            y = dp[y][jump];
        }
        return iter;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine().trim());
        if (n <= 1) {
            return;
        }

        depth = new int[n + 1];
        parents = new int[n + 1];
        dp = new int[n + 1][DEPTH + 1];

        nodes = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n; i++) {
            int v = Integer.parseInt(st.nextToken());
            dp[i][0] = v;
            parents[i] = v;
            depth[i] = depth[v] + 1;
            nodes[v].add(i);

            for (int d = 1; d <= DEPTH; d++) {
                dp[i][d] = dp[dp[i][d - 1]][d - 1];
            }
        }
    }
}
