import java.util.*;
import java.io.*;

public class b4195 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;
    static int t;
    static int f;
    static int[] size;
    static int[] parents;
    static String[][] relationship;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            init();
            solve();
            print();
        }
    }

    private static void print() {
        System.out.print(sb.toString());
    }

    private static void solve() {
        for (int i = 0; i < f; i++) {
            int x = check(relationship[i][0]);
            int y = check(relationship[i][1]);

            union(x, y);
            add(x);
        }
    }

    private static void add(int a) {
        sb.append(size[find(a)]).append("\n");
    }

    private static int check(String str) {
        if (map.containsKey(str)) {
            return map.get(str);
        } else {
            map.put(str, map.size() + 1);
            return map.size();
        }
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x != y) {
            if (x > y) {
                parents[x] = y;
                size[y] += size[x];
            } else {
                parents[y] = x;
                size[x] += size[y];
            }
        }
    }

    private static int find(int x) {
        return parents[x] = (x == parents[x] ? x : find(parents[x]));
    }

    private static void init() throws IOException {
        f = Integer.parseInt(br.readLine());

        size = new int[f * 2 + 1];
        parents = new int[f * 2 + 1];
        for (int i = 1; i <= f * 2; i++) {
            parents[i] = i;
            size[i] = 1;
        }

        relationship = new String[f + 1][2];
        for (int i = 0; i < f; i++) {
            st = new StringTokenizer(br.readLine());

            relationship[i][0] = st.nextToken();
            relationship[i][1] = st.nextToken();
        }

        map = new HashMap<>();
        sb = new StringBuilder();
    }

}
