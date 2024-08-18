import java.util.*;
import java.io.*;

public class b10282 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int n;
    static int d;
    static int c;
    static int[] time;
    static ArrayList<int[]>[] arr;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            init();
            solve();
            print();
        }
    }

    private static void print() {
        int num = 0;
        int max = -1;

        for (int i = 1; i <= n; i++) {
            if (time[i] != Integer.MAX_VALUE) {
                num++;
                max = Math.max(max, time[i]);
            }
        }

        System.out.println(num + " " + max);
    }

    private static void solve() {
        dijkstra(c);
    }

    private static void dijkstra(int stt) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]) {
            {
                add(new int[] { stt, 0 });
            }
        };

        time[stt] = 0;

        while (!pq.isEmpty()) {
            int[] p = pq.poll();

            if (p[1] > time[p[0]]) {
                continue;
            }

            for (int[] v : arr[p[0]]) {
                int node = v[0];
                int dist = v[1];

                if (time[node] > time[p[0]] + dist) {
                    time[node] = time[p[0]] + dist;
                    pq.add(new int[] { node, time[node] });
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        time = new int[n + 1];
        Arrays.fill(time, Integer.MAX_VALUE);

        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            arr[b].add(new int[] { a, s });
        }
    }
}