import java.util.*;
import java.io.*;

public class b5972 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static ArrayList<int[]>[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        dijkstra();
    }

    private static void dijkstra() {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        pq.add(new int[] { 1, distance[1] = 0 });

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int curr = p[0];
            int dist = p[1];

            if (curr == n) {
                System.out.println(distance[n]);
                return;
            }

            if (!arr[curr].isEmpty()) {
                for (int[] item : arr[curr]) {
                    int next = item[0];
                    int add = item[1];

                    if (distance[next] > dist + add) {
                        pq.add(new int[] { next, distance[next] = dist + add });
                    }
                }
            }
        }
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a].add(new int[] { b, c });
            arr[b].add(new int[] { a, c });
        }
    }
}