import java.io.*;
import java.util.*;

public class b31912 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int t;
    static int[] bottle;
    static int[][] time;
    static HashSet<Integer> set;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        simulate();
        System.out.print(sb.toString());
    }

    private static void simulate() {
        for (int i = 0; i < t; i++) {
            create(i);
            move();
        }
    }

    private static void create(int t) {
        int y = time[t][0];
        int k = time[t][1];

        bottle[y] = Math.min(m, k + bottle[y]);
        set.add(y);
    }

    private static void move() {
        int[] temp = new int[n + 1];
        HashSet<Integer> next = new HashSet<>();

        for (int i : set) {
            int pivot = bottle[i] / 5;

            if (pivot == 0) {
                continue;
            }

            temp[i] += pivot;
            next.add(i);

            if (i > 1) {
                temp[i - 1] += pivot;
                next.add(i - 1);
            }

            if (i < n) {
                temp[i + 1] += pivot;
                next.add(i + 1);
            }
        }

        int sum = 0;
        Arrays.fill(bottle, 0);
        for (int i : next) {
            bottle[i] = Math.min(m, temp[i]);
            sum += bottle[i];
        }

        sb.append(sum).append("\n");
        set = next;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        bottle = new int[n + 2];
        time = new int[t][2];
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        set = new HashSet<>();
        sb = new StringBuilder();
    }
}