import java.util.*;
import java.io.*;

public class b15971 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int s1;
    static int s2;
    static int dist;
    static int[][] trace;
    static boolean[] visited;
    static ArrayList<int[]>[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        // bfs();
        // analyze();
        dfs(s1, 0, 0);
    }

    private static void dfs(int curr, int dist, int max) {
        visited[curr] = true;

        if (curr == s2) {
            System.out.println(dist - max);
            System.exit(0);
        }

        if (arr[curr].isEmpty()) {
            return;
        }

        for (int[] info : arr[curr]) {
            if (!visited[info[0]]) {
                dfs(info[0], dist + info[1], Math.max(max, info[1]));
            }
        }
    }

    private static void analyze() {
        if (n == 2) {
            System.out.println(0);
            return;
        }

        int max = 0;

        int key = s2;
        while (trace[key][0] != 0) {
            max = Math.max(max, trace[key][1]);
            key = trace[key][0];
        }

        System.out.println(dist - max);
    }

    private static void bfs() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { s1, 0 });

        boolean[] visited = new boolean[n + 1];
        visited[s1] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == s2) {
                dist = p[1];
                return;
            }

            if (arr[p[0]].isEmpty()) {
                continue;
            }

            for (int[] info : arr[p[0]]) {
                int next = info[0];
                int d = info[1];

                if (visited[next]) {
                    continue;
                }

                dq.add(new int[] { next, p[1] + d });
                visited[next] = true;
                trace[next][0] = p[0];
                trace[next][1] = d;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s1 = Integer.parseInt(st.nextToken());
        s2 = Integer.parseInt(st.nextToken());

        dist = 0;
        visited = new boolean[n + 1];
        trace = new int[n + 1][2];
        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            arr[x].add(new int[] { y, d });
            arr[y].add(new int[] { x, d });
        }
    }
}