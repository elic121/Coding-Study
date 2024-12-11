import java.util.*;
import java.io.*;

/**
 * b7507
 */
public class b7507 {
    static class Period implements Comparable<Period> {
        int d;
        int s;
        int e;

        Period(int d, int s, int e) {
            this.d = d;
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Period o) {
            if (this.d != o.d) {
                return Integer.compare(this.d, o.d);
            }
            if (this.e != o.e) {
                return Integer.compare(this.e, o.e);
            }

            return Integer.compare(this.s, o.s);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static PriorityQueue<Period> pq;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            init();
            solve(i);
        }
    }

    private static void solve(int index) {
        int d = -1;
        int e = -1;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Period p = pq.poll();

            if (d < p.d) {
                d = p.d;
                e = p.e;
                cnt++;
                continue;
            }

            if (p.s < e) {
                continue;
            }

            d = p.d;
            e = p.e;
            cnt++;
        }

        System.out.println("Scenario #" + index + ":");
        System.out.println(cnt);
        System.out.println();
    }

    private static void init() throws IOException {
        m = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            pq.add(new Period(d, s, e));
        }
    }
}