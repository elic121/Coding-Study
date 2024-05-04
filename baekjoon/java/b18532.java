import java.util.*;
import java.io.*;

public class b18532 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int k;
    static int x;
    static boolean[] dist;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bfs(x);
        printAnswer();
    }

    private static void printAnswer() {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i]) {
                cnt++;
                System.out.println(i);
            }
        }
        if (cnt == 0) {
            System.out.println(-1);
        }
    }

    private static void bfs(int stt) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { stt, 0 });

        boolean[] visited = new boolean[n + 1];
        visited[stt] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            if (p[1] == k) {
                dist[p[0]] = true;
                continue;
            }
            if (arr[p[0]] != null && !arr[p[0]].isEmpty()) {
                for (int v : arr[p[0]]) {
                    if (visited[v]) {
                        continue;
                    }
                    dq.add(new int[] { v, p[1] + 1 });
                    visited[v] = true;
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        dist = new boolean[n + 1];
        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
        }
    }
}
