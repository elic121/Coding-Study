import java.util.*;
import java.io.*;

public class b1504 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int e;
    static int v1;
    static int v2;
    static int[][] dist;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int comp1 = getShortestPath(1, v1) + getShortestPath(v2, n);
        int comp2 = getShortestPath(1, v2) + getShortestPath(v1, n);

        int min = getShortestPath(v1, v2) + Math.min(comp1, comp2);
        System.out.println(min >= (Integer.MAX_VALUE / 3) ? -1 : min);
    }

    private static int getShortestPath(int stt, int end) {
        if (stt == end) {
            return 0;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
            return p1[1] - p2[1];
        });
        pq.add(new int[] { stt, 0 });

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE / 3);

        distance[stt] = 0;
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int idx = p[0];
            int d = p[1];

            if (arr[idx] != null && !arr[idx].isEmpty()) {
                for (int v : arr[idx]) {
                    if (distance[v] > d + dist[idx][v]) {
                        distance[v] = d + dist[idx][v];
                        pq.add(new int[] { v, distance[v] });
                    }
                }
            }
        }

        return distance[end];
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        dist = new int[n + 1][n + 1];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);

            dist[a][b] = c;
            dist[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

}
