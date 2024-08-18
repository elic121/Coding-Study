import java.util.*;
import java.io.*;

public class b28278 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] order;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.print(sb.toString());
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            order(i);
        }
    }

    private static void order(int index) {
        switch (order[index][0]) {
            case 1:
                orderOne(order[index][1]);
                break;
            case 2:
                orderTwo();
                break;
            case 3:
                orderThree();
                break;
            case 4:
                orderFour();
                break;
            case 5:
                orderFive();
                break;
            default:
                break;
        }
    }

    private static void orderOne(int value) {
        dq.add(value);
    }

    private static void orderTwo() {
        append(dq.isEmpty() ? -1 : dq.pollLast());
    }

    private static void orderThree() {
        append(dq.size());
    }

    private static void orderFour() {
        append(dq.isEmpty() ? 1 : 0);
    }

    private static void orderFive() {
        append(dq.isEmpty() ? -1 : dq.peekLast());
    }

    private static void append(Object o) {
        sb.append(o).append("\n");
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());

        order = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            order[i][0] = Integer.parseInt(st.nextToken());

            if (st.hasMoreTokens()) {
                order[i][1] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
