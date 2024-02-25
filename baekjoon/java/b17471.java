import java.io.*;
import java.util.*;

public class b17471 {

    static int N, MIN, POP[], parents[];
    static boolean visited[], graph[][];
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        MIN = Integer.MAX_VALUE;
        POP = new int[N];
        graph = new boolean[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            POP[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            for (int j = 0; j < T; j++) {
                graph[i][Integer.parseInt(st.nextToken()) - 1] = true;
            }
        }

        solve();
    }

    private static void solve() {
        for (int i = 1; i <= N / 2; i++) {
            visited = new boolean[N];
            comb(0, 0, i);
        }
        System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
    }

    private static void calc() {
        int CNT1 = 0;
        int CNT2 = 0;

        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (visited[i])
                arr1.add(i);
            else
                arr2.add(i);
        }

        for (int node : arr1) {
            for (int i = 0; i < N; i++) {
                if (graph[node][i] && arr1.contains(i)) {
                    union(node, i);
                }
            }
        }

        for (int node : arr2) {
            for (int i = 0; i < N; i++) {
                if (graph[node][i] && arr2.contains(i)) {
                    union(node, i);
                }
            }
        }
        
        ArrayList<Integer> arr3 = new ArrayList<>();
        for (int i = 0; i < parents.length; i++) {
            if (!arr3.contains(parents[i])) {
                arr3.add(parents[i]);
            }
        }

        if (arr3.size() != 2) {
            return;
        }

        for (int i = 0; i < arr1.size(); i++) {
            CNT1 += POP[arr1.get(i)];
        }

        for (int i = 0; i < arr2.size(); i++) {
            CNT2 += POP[arr2.get(i)];
        }

        MIN = Math.min(MIN, Math.abs(CNT1 - CNT2));
    }

    private static void comb(int depth, int index, int M) {
        if (depth == M) {
            init();
            calc();
            return;
        }

        for (int i = index; i < N; i++) {
            visited[i] = true;
            comb(depth + 1, i + 1, M);
            visited[i] = false;
        }
    }

    private static void init() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int x) {
        if (x == parents[x]) return parents[x];
        return parents[x] = find(parents[x]);
    }

    private static void union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);

        if (p1 > p2) {
            parents[p1] = p2;
        } else if (p1 < p2) {
            parents[p2] = p1;
        }
    }
}
