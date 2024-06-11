import java.util.*;
import java.io.*;

public class b1717 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;
    static int n;
    static int m;
    static int[] parents;
    static int[][] order;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < m; i++) {
            calc(i);
        }
        System.out.println(sb.toString());
    }

    private static void calc(int idx) {
        int ord = order[idx][0];
        int a = order[idx][1];
        int b = order[idx][2];

        if (ord == 0) {
            union(a, b);
        } else if (ord == 1) {
            sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
        }
    }

    private static void union(int a, int b) {
        if (a == b) {
            return;
        }

        int x = find(a);
        int y = find(b);

        if (x >= y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }

    private static int find(int x) {
        return parents[x] = (x == parents[x] ? x : find(parents[x]));
    }

    private static void init() throws IOException {
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        order = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            order[i][0] = Integer.parseInt(st.nextToken());
            order[i][1] = Integer.parseInt(st.nextToken());
            order[i][2] = Integer.parseInt(st.nextToken());
        }
    }
}