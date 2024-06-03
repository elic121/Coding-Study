import java.util.*;
import java.io.*;

public class b1766 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] indegree;
    static ArrayList<Integer>[] arr;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        topology();
    }

    private static void topology() {
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int in = pq.poll();
            System.out.print(in + " ");

            if (!arr[in].isEmpty()) {
                for (int v : arr[in]) {
                    if (--indegree[v] == 0) {
                        pq.add(v);
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n + 1];

        pq = new PriorityQueue<>();

        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            indegree[b]++;
        }
    }
}