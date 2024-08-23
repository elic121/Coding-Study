import java.util.*;
import java.io.*;

public class b2623 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] indegree;
    static ArrayList<Integer>[] arr;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        topologySort();
    }

    private static void topologySort() {
        int cnt = 0;

        while (!dq.isEmpty()) {
            int p = dq.pop();
            cnt++;
            sb.append(p).append("\n");

            for (int ch : arr[p]) {
                if (--indegree[ch] == 0) {
                    dq.add(ch);
                }
            }
        }

        if (cnt != n) {
            System.out.println(0);
        } else {
            System.out.print(sb.toString());
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        arr = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int size = Integer.parseInt(st.nextToken());

            int prev = Integer.parseInt(st.nextToken());
            for (int j = 1; j < size; j++) {
                int node = Integer.parseInt(st.nextToken());

                indegree[node]++;
                arr[prev].add(node);
                prev = node;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                dq.add(i);
            }
        }
    }
}
