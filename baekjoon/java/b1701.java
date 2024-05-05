import java.util.*;
import java.io.*;

public class b1701 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int v;
    static int e;
    static boolean[] judge;
    static boolean[] visited;
    static ArrayList<Integer>[] rel;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            init();
            solve();
        }
    }

    private static void solve() {
        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                if (!bfs(i)) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    private static boolean bfs(int vertex) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(vertex);

        visited[vertex] = true;

        while (!dq.isEmpty()) {
            int p = dq.pop();
            for (int v : rel[p]) {
                if (!visited[v]) {
                    judge[v] = !judge[p];
                    visited[v] = true;
                    dq.add(v);
                }

                if (judge[p] == judge[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        judge = new boolean[v + 1];
        visited = new boolean[v + 1];
        rel = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            rel[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            rel[s1].add(s2);
            rel[s2].add(s1);
        }
    }
}
