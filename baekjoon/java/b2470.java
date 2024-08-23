import java.util.*;
import java.io.*;

public class b2470 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int s;
    static int e;
    static int diffMax;
    static TreeSet<Integer> set;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        s = 0;
        e = 0;

        Integer left = set.first();
        Integer right = set.last();

        diffMax = 2_000_000_001;

        while (left != null && right != null && left < right) {
            int sum = left + right;
            int val = Math.abs(sum);

            if (val < diffMax) {
                diffMax = val;
                s = left;
                e = right;
            }

            if (sum < 0) {
                left = set.higher(left);
            } else {
                right = set.lower(right);
            }
        }

        System.out.println(s + " " + e);
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        set = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
    }
}
