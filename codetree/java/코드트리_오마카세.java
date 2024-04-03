package codetree.java;

import java.util.*;
import java.io.*;

public class 코드트리_오마카세 {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int L, Q, num;
    static Map<Integer, String> seat;
    static Map<Integer, Map<String, Integer>> rail;

    public static void main(String[] args) throws IOException {
        solve();
    }

    private static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        num = 0;
        rail = new HashMap<>();
        seat = new HashMap<>();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            process(order, t, x, name);
            for (int v : rail.keySet()) {
                System.out.println(v + "에는");
                Map<String, Integer> temp = rail.get(v);
                for (String n : temp.keySet()) {
                    System.out.println(n + "가 " + temp.get(n) + "개 있다.");
                }
            }
            System.out.println("================");
        }
    }

    private static void process(String order, int t, int x, String name) {
        if (order.equals("100")) {
            make(t, x, name);
        } else if (order.equals("200")) {
            num++;
            enter(t, x, name);
        } else {
            take(t);
        }
    }

    private static void make(int t, int x, String name) {
        int index = get(t, x);
        if (!rail.containsKey(index)) {
            rail.put(index, new HashMap<String, Integer>());
        }

        Map<String, Integer> m = rail.get(index);
        m.put(name, m.getOrDefault(name, 0) + 1);
    }

    private static void enter(int t, int x, String name) {
        int index = get(t, x);

    }

    private static void take(int t) {

    }

    private static int get(int t, int x) {
        int idx = (x - t) % L;
        return idx + (idx < 0 ? L : 0);
    }
}
