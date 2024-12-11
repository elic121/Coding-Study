import java.util.*;
import java.io.*;

public class b5568 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int k;
    static int[] arr;
    static HashSet<String> set;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        perm(0, 0, "");
    }

    private static void print() {
        System.out.println(set.size());
    }

    private static void perm(int depth, int visited, String str) {
        if (depth == k) {
            set.add(str);
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((visited & 1 << i) != 0) {
                continue;
            }

            perm(depth + 1, visited | 1 << i, str + String.valueOf(arr[i]));
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        set = new HashSet<>();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}