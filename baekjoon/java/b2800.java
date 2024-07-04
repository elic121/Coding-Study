import java.util.*;
import java.io.*;

public class b2800 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;
    static char[] ch;
    static Stack<Integer> stack;
    static ArrayList<int[]> arr;
    static ArrayList<String> ans;
    static boolean[] visited;
    static HashSet<String> fin;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        for (int i = 1; i < ans.size(); i++) {
            if (fin.contains(ans.get(i))) {
                continue;
            }
            sb.append(ans.get(i)).append("\n");
            fin.add(ans.get(i));
        }

        System.out.print(sb.toString());
    }

    private static void solve() {
        check();
        comb(0);
        sort();
    }

    private static void sort() {
        Collections.sort(ans);
    }

    private static void check() {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                stack.add(i);
            }

            if (ch[i] == ')') {
                int p1 = stack.pop();
                int p2 = i;
                arr.add(new int[] { p1, p2 });
            }
        }
    }

    private static void comb(int depth) {
        if (depth == arr.size()) {
            addString();
            return;
        }

        comb(depth + 1);

        int[] p = arr.get(depth);
        visited[p[0]] = true;
        visited[p[1]] = true;
        comb(depth + 1);
        visited[p[0]] = false;
        visited[p[1]] = false;
    }

    private static void addString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            if (!visited[i]) {
                str.append(ch[i]);
            }
        }

        ans.add(str.toString());
    }

    private static void init() throws IOException {
        sb = new StringBuilder();
        stack = new Stack<>();
        arr = new ArrayList<>();
        ans = new ArrayList<>();
        fin = new HashSet<>();

        ch = br.readLine().toCharArray();
        visited = new boolean[ch.length];
    }
}