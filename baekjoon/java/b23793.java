import java.util.*;
import java.io.*;

public class b23793 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int stt;
    static int sov;
    static int des;
    static ArrayList<int[]>[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        long passByStopover = 0;
        long s1 = dijkstra(stt, sov, -1);
        if (s1 == -1) {
            passByStopover = -1;
        } else {
            long s2 = dijkstra(sov, des, -1);
            passByStopover = s2 == -1 ? -1 : (s1 + s2);
        }

        StringBuilder sb = new StringBuilder();
        sb
                .append(passByStopover)
                .append(" ")
                .append(dijkstra(stt, des, sov));

        System.out.println(sb.toString());
    }

    private static long dijkstra(int x, int y, int pass) {
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[x] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.add(new long[] { 0, x });

        while (!pq.isEmpty()) {
            long[] p = pq.poll();

            if (p[1] == y) {
                return p[0];
            }

            if (!arr[(int) p[1]].isEmpty()) {
                for (int i = 0; i < arr[(int) p[1]].size(); i++) {
                    int[] info = arr[(int) p[1]].get(i);
                    int v = info[0];
                    int dist = info[1];

                    if (pass == v) {
                        continue;
                    }

                    if (distance[v] > p[0] + dist) {
                        distance[v] = p[0] + dist;
                        pq.add(new long[] { distance[v], v });
                    }
                }
            }
        }

        return -1;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[u].add(new int[] { v, w });
        }

        st = new StringTokenizer(br.readLine());
        stt = Integer.parseInt(st.nextToken());
        sov = Integer.parseInt(st.nextToken());
        des = Integer.parseInt(st.nextToken());
    }

}
