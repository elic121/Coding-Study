import java.io.*;
import java.util.*;

public class b1005 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int n;
    static int k;
    static int w;
    static int max;
    static int[] time;
    static int[] maxTime;
    static int[] indegree;
    static ArrayList<Integer>[] arr;
    static ArrayDeque<int[]> dq;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            init();
            solve();
        }
    }

    private static void solve() {
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                dq.add(new int[] { i, maxTime[i] });
            }
        }

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (!arr[p[0]].isEmpty()) {
                for (int v : arr[p[0]]) {
                    maxTime[v] = Math.max(maxTime[v], time[v] + p[1]);

                    if (--indegree[v] == 0) {
                        dq.add(new int[] { v, maxTime[v] });
                    }
                }
            }
        }
        System.out.println(maxTime[w]);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        max = 0;

        dq = new ArrayDeque<>();

        time = new int[n + 1];
        maxTime = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            maxTime[i] = time[i];
        }

        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        indegree = new int[n + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[b]++;
            arr[a].add(b);
        }

        w = Integer.parseInt(br.readLine());
    }
}
