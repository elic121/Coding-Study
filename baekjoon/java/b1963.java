import java.util.*;
import java.io.*;

public class b1963 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int n;
    static int k;
    static boolean[] visited;
    static boolean[] primes;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        getPrimeNumber();

        for (int i = 0; i < t; i++) {
            init();
            solve();
        }
    }

    private static void getPrimeNumber() {
        primes = new boolean[10000];

        for (int i = 2; i < 10000; i++) {
            if (!primes[i]) {
                for (int j = i + i; j < 10000; j += i) {
                    primes[j] = true;
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }

    private static void solve() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { n, 0 });

        visited = new boolean[10000];
        visited[n] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == k) {
                System.out.println(p[1]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= 9; j++) {
                    int next = convert(p[0], i, j);
                    if (next < 1000 || next >= 10000) {
                        continue;
                    }
                    if (primes[next]) {
                        continue;
                    }
                    if (visited[next]) {
                        continue;
                    }
                    dq.add(new int[] { next, p[1] + 1 });
                    visited[next] = true;
                }
            }
        }
        System.out.println("Impossible");
    }

    private static int convert(int num, int digit, int conv) {
        int temp = num;
        for (int i = 3; i > digit; i--) {
            int pivot = (int) Math.pow(10, i);
            temp -= pivot * (temp / pivot);
        }

        int r = (int) Math.pow(10, digit);
        num -= r * (temp / r);
        num += r * conv;

        return num;
    }
}
