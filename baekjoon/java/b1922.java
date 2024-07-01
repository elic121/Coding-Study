import java.util.*;
import java.io.*;

public class b1922 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int dist;
    static int[] parents;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(dist);
    }

    private static void solve() throws IOException {
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int x = find(p[0]);
            int y = find(p[1]);

            parents[Math.max(x, y)] = Math.min(x, y);
            dist += x != y ? p[2] : 0;
        }
    }

    private static int find(int x) {
        return parents[x] = (x == parents[x] ? x : find(parents[x]));
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = 0;
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        pq = new PriorityQueue<>((x, y) -> {
            return Integer.compare(x[2], y[2]);
        });

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new int[] { a, b, c });
        }
    }
}
