import java.util.*;
import java.io.*;

/**
 * b12851
 */
public class b12851 {

    static int N, K, CNT, MIN, arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);
        MIN = Integer.MAX_VALUE;
        arr = new int[100_001];

        bfs();
        System.out.println(MIN);
        System.out.println(CNT);
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(N);

        while (!que.isEmpty()) {
            int val = que.remove();
            if (MIN < arr[val]) return;

            if (val == K) {
                MIN = arr[val];
                CNT++;
                continue;
            }

            for (int i = 0; i < 3; i++) {
                int nx = whereToGo(val, i);

                if (nx < 0 || nx > 100_000) continue;
                if (arr[nx] == 0 || arr[nx] == arr[val] + 1) {
                    arr[nx] = arr[val] + 1;
                    que.add(nx);
                }
            }
        }
    }

    private static int whereToGo(int val, int index) {
        if (index == 0) {
            return val - 1;
        } else if (index == 1) {
            return val + 1;
        } else {
            return 2 * val;
        }
    }
}