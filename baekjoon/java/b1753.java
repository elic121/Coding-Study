import java.util.*;
import java.io.*;

/**
 * 	128432kb
 * 	816ms
 */
public class b1753 {
    static class Vertex implements Comparable<Vertex> {
        int index, dist;
        Vertex(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.dist - o.dist;
        }

    }
    static int V, E, start;
    static final int INF = Integer.MAX_VALUE;
    static int[] distance;
    static ArrayList<ArrayList<Vertex>> graph;
    static boolean[] visited;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        visited = new boolean[V + 1];
        
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
    
        start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Vertex(v, w));
        }

        solve();
    }

    private static void solve() {
        distance = new int[V + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex v = pq.poll();
            int index = v.index;

            if (visited[index]) continue;
            visited[index] = true;

            for (Vertex ver : graph.get(index)) {
                distance[ver.index] = Math.min(distance[ver.index], distance[index] + ver.dist);
                pq.add(new Vertex(ver.index, distance[ver.index]));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(distance[i] != INF ? distance[i] : "INF");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}
