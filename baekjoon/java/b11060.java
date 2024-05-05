import java.util.*;
import java.io.*;

public class b11060 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { 0, 0 });
        visited[0] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            if (p[0] == n - 1) {
                System.out.println(p[1]);
                System.exit(0);
            }
            for (int i = 1; i <= arr[p[0]]; i++) {
                if (p[0] + i < n && !visited[p[0] + i]) {
                    dq.add(new int[] { p[0] + i, p[1] + 1 });
                    visited[p[0] + i] = true;
                }
            }
        }
        System.out.println(-1);
    }
}
