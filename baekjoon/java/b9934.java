import java.util.*;
import java.io.*;

public class b9934 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int cnt;
    static int[] values;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        search(1, 1);
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();

        end: for (int i = 1; i <= n; i++) {
            for (int j = (int) Math.pow(2, i - 1); j < (int) Math.pow(2, i); j++) {
                if (j >= (int) Math.pow(2, n)) {
                    break end;
                }
                sb.append(tree[j]);
                sb.append(" ");

            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void search(int depth, int index) {
        if (depth == n) {
            tree[index] = values[cnt++];
            return;
        }

        search(depth + 1, index << 1);
        tree[index] = values[cnt++];
        search(depth + 1, 1 + (index << 1));
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        cnt = 0;

        values = new int[(int) Math.pow(2, n) - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < (int) Math.pow(2, n) - 1; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        tree = new int[(int) Math.pow(2, n)];
    }
}