import java.util.*;
import java.io.*;

public class b11657 {
    static class Edge {
        int a;
        int b;
        long c;

        Edge(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static long[] dist;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        if (bf()) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= n; i++) {
                sb.append(dist[i] == Long.MAX_VALUE ? -1 : dist[i]);
                sb.append("\n");
            }
            System.out.print(sb.toString());
        }
    }

    private static boolean bf() {
        boolean isCycle = false;
        int start = 1;
        dist[start] = 0L;

        for (int i = 1; i <= n; i++) {
            for (Edge e : edges) {
                if (dist[e.a] == Long.MAX_VALUE) {
                    continue;
                }

                if (dist[e.a] + e.c < dist[e.b]) {
                    dist[e.b] = dist[e.a] + e.c;

                    if (i == n) {
                        isCycle = true;
                    }
                }
            }
        }

        return isCycle;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n + 1];
        edges = new Edge[m];
        Arrays.fill(dist, Long.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }
    }
}