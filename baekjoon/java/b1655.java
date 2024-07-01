import java.util.*;
import java.io.*;

public class b1655 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static int n;
    static PriorityQueue<Integer> f;
    static PriorityQueue<Integer> b;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.print(sb.toString());
    }

    private static void solve() throws IOException {
        for (int i = 0; i < n; i++) {
            f.add(Integer.parseInt(br.readLine()));

            while (Math.abs(f.size() - b.size()) > 1) {
                boolean s = f.size() > b.size();
                (s ? b : f).add((s ? f : b).poll());
            }

            if (!f.isEmpty() && !b.isEmpty() && f.peek() > b.peek()) {
                f.add(b.poll());
                b.add(f.poll());
            }
            sb.append((f.size() >= b.size() ? f : b).peek());
            sb.append("\n");
        }
    }

    private static void init() throws IOException {
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        f = new PriorityQueue<>((x, y) -> -Integer.compare(x, y));
        b = new PriorityQueue<>();
    }
}