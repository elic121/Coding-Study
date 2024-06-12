import java.util.*;
import java.io.*;

public class b1202 {
    static class Data {
        int m;
        int v;

        Data(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int[] bag;
    static Data[] datas;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        long profit = 0;

        for (int i = 0, j = 0; i < k; i++) {
            while (j < n && datas[j].m <= bag[i]) {
                pq.add(datas[j++].v);
            }

            if (!pq.isEmpty()) {
                profit += pq.poll();
            }
        }

        System.out.println(profit);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bag = new int[k];
        pq = new PriorityQueue<>(Collections.reverseOrder());

        datas = new Data[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            datas[i] = new Data(m, v);
        }
        Arrays.sort(datas, new Comparator<Data>() {
            @Override
            public int compare(Data p1, Data p2) {
                if (p1.m != p2.m) {
                    return Integer.compare(p1.m, p2.m);
                } else {
                    return Integer.compare(p2.v, p1.v);
                }
            }
        });

        for (int i = 0; i < k; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);
    }
}