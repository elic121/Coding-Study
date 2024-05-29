import java.util.*;
import java.io.*;

/**
 * b5212
 */
public class b5212 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Integer>[] arr;
    static int n;
    static int m;
    static int k;

    public static void main(String[] args) throws IOException {
        init();
        solve();

    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { 1, 1 });

        boolean[] visited = new boolean[1 + m + n];
        visited[1] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == n) {
                System.out.println(p[1] / 2 + 1);
                return;
            }

            if (!arr[p[0]].isEmpty()) {
                for (int v : arr[p[0]]) {
                    if (visited[v]) {
                        continue;
                    }
                    visited[v] = true;
                    dq.add(new int[] { v, p[1] + 1 });
                }
            }
        }

        System.out.println(-1);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[1 + m + n];
        for (int i = 0; i < 1 + m + n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= k; j++) {
                int val = Integer.parseInt(st.nextToken());
                arr[n + i].add(val);
                arr[val].add(n + i);
            }
        }
    }
}