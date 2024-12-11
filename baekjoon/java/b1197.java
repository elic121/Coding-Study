import java.util.*;
import java.io.*;

/**
 * b1197
 */
public class b1197 {
    static class Edge {
        int stt, end, dist;

        Edge(int stt, int end, int dist) {
            this.stt = stt;
            this.end = end;
            this.dist = dist;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int v;
    static int e;
    static int weights;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(weights);
    }

    private static void solve() {
        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (find(e.stt) != find(e.end)) {
                weights += e.dist;
                union(e.stt, e.end);
            }
        }
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);

        if (p1 > p2) {
            parents[p1] = p2;
        } else {
            parents[p2] = p1;
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        weights = 0;

        parents = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parents[i] = i;
        }

        pq = new PriorityQueue<Edge>((e1, e2) -> e1.dist - e2.dist);
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int stt = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(stt, end, dist);
            pq.add(edge);
        }
    }
}