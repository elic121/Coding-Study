import java.util.*;
import java.io.*;

public class b28279 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Deque<Integer> dq;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(sb.toString());
    }

    private static void solve() throws IOException {
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());

            if (o == 1 || o == 2) {
                int number = Integer.parseInt(st.nextToken());

                if (o == 1) {
                    dq.addFirst(number);
                } else {
                    dq.add(number);
                }
            } else if (o == 3) {
                int key = !dq.isEmpty() ? dq.pollFirst() : -1;
                sb.append(key);
                sb.append("\n");
            } else if (o == 4) {
                int key = !dq.isEmpty() ? dq.pollLast() : -1;
                sb.append(key);
                sb.append("\n");
            } else if (o == 5) {
                sb.append(dq.size());
                sb.append("\n");
            } else if (o == 6) {
                sb.append(dq.isEmpty() ? 1 : 0);
                sb.append("\n");
            } else if (o == 7) {
                int key = !dq.isEmpty() ? dq.peekFirst() : -1;
                sb.append(key);
                sb.append("\n");
            } else {
                int key = !dq.isEmpty() ? dq.peekLast() : -1;
                sb.append(key);
                sb.append("\n");
            }
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        dq = new ArrayDeque<>();
        sb = new StringBuilder();
    }
}