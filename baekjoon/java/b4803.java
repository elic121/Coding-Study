import java.util.*;
import java.io.*;

public class b4803 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int cnt;
    static int iter = 0;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        for (;;) {
            init();
            solve();
            print();
        }
    }

    private static void print() {
        sb
                .append("Case ")
                .append(iter)
                .append(": ");

        if (cnt > 1) {
            sb
                    .append("A forest of ")
                    .append(cnt)
                    .append(" trees.");
        } else if (cnt == 1) {
            sb
                    .append("There is one tree.");
        } else {
            sb
                    .append("No trees.");
        }
        sb.append("\n");
    }

    private static void solve() throws IOException {
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (parents[i] == i) {
                cnt++;
            }
        }
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x > y) {
            parents[x] = y;
        } else if (x < y) {
            parents[y] = x;
        } else {
            parents[x] = 0;
        }
    }

    private static int find(int x) {
        return parents[x] = (x == parents[x] ? x : find(parents[x]));
    }

    private static void init() throws IOException {
        iter++;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cnt = 0;

        if (n == 0) {
            System.out.print(sb.toString());
            System.exit(0);
        }

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }
}
