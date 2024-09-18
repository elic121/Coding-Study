import java.util.*;
import java.io.*;

public class b4241 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int d;
    static boolean find;
    static int[] numbers;
    static boolean[] primes;

    public static void main(String[] args) throws IOException {
        makePrimes();
        for (;;) {
            init();
            solve();
        }
    }

    private static void solve() {
        backTrack(0, new boolean[1001], new int[m - n + 1]);
        if (!find) {
            System.out.println("No anti-prime sequence exists.");
        }
    }

    private static void backTrack(int depth, boolean[] visited, int[] list) {
        if (depth == m - n + 1) {
            find = true;
            print(list);
            return;
        }

        if (find) {
            return;
        }

        for (int i = n; i <= m; i++) {
            if (visited[i]) {
                continue;
            }

            if (depth >= 1 && !isValid(depth, i, list)) {
                continue;
            }

            visited[i] = true;
            list[depth] = i;
            backTrack(depth + 1, visited, list);
            if (find) {
                return;
            }
            visited[i] = false;
        }
    }

    private static void print(int[] list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            sb.append(list[i]);
            if (i != list.length - 1) {
                sb.append(',');
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean isValid(int end, int curr, int[] list) {
        int sum = curr;
        int e = 1;
        while (e < d && end - e >= 0) {
            sum += list[end - e];
            if (isPrime(sum)) {
                return false;
            }
            e++;
        }
        return true;
    }

    private static boolean isPrime(int number) {
        return primes[number];
    }

    private static void makePrimes() {
        primes = new boolean[10001];
        Arrays.fill(primes, true);

        for (int i = 2; i <= 100; i++) {
            if (!primes[i]) {
                continue;
            }

            for (int j = 2 * i; j < 10001; j += i) {
                primes[j] = false;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        if (n == 0) {
            System.exit(0);
        }

        find = false;
        numbers = new int[m - n + 1];
    }
}
