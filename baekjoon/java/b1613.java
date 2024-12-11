import java.util.*;
import java.io.*;

//TODO: re-try
public class b1613 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int k;
    static int s;
    static int cnt;
    static int[] level;
    static int[] order;
    static int[][] ans;
    static ArrayList<Integer>[] arr;
    static ArrayDeque<Integer> dq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.print(sb.toString());
    }

    private static void solve() {
        topologySort();
        check();
    }

    private static void check() {
        for (int i = 0; i < s; i++) {
            int[] p = ans[i];

            if (order[p[0]] == n + 2 || order[p[1]] == n + 2) {
                sb.append(0);
            }

            if (order[p[0]] == order[p[1]]) {
                sb.append(0);
            }

            if (order[p[0]] < order[p[1]]) {
                sb.append(-1);
            }

            if (order[p[0]] > order[p[1]]) {
                sb.append(1);
            }

            sb.append("\n");
        }
    }

    private static void topologySort() {
        while (!dq.isEmpty()) {
            int p = dq.pop();

            if (!arr[p].isEmpty()) {
                for (int child : arr[p]) {
                    if (--level[child] == 0) {
                        dq.add(child);
                        order[child] = cnt++;
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cnt = 0;

        order = new int[n + 1];
        Arrays.fill(order, n + 2);

        level = new int[n + 1];
        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }
        ans = new int[s][2];
        dq = new ArrayDeque<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());

            arr[first].add(last);
            level[last]++;
        }

        for (int i = 1; i <= n; i++) {
            if (level[i] == 0) {
                dq.add(i);
                order[i] = cnt;
            }
        }

        cnt++;

        s = Integer.parseInt(br.readLine());
        ans = new int[s][2];
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            ans[i][0] = Integer.parseInt(st.nextToken());
            ans[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}