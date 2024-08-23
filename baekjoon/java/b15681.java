import java.util.*;
import java.io.*;

public class b15681 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int r;
    static int q;
    static int[] query;
    static int[] children;
    static List<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        for (int i = 0; i < q; i++) {
            sb.append(children[query[i]]).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void solve() {
        search(r, -1);
    }

    private static int search(int root, int parent) {
        children[root] = 1;

        for (int ch : arr[root]) {
            if (ch != parent) {
                search(ch, root);
                children[root] += children[ch];
            }
        }

        return children[root];
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        children = new int[n + 1];

        arr = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[u].add(v);
            arr[v].add(u);
        }

        query = new int[q];
        for (int i = 0; i < q; i++) {
            query[i] = Integer.parseInt(br.readLine());
        }
    }
}
