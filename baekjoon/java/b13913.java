import java.util.*;
import java.io.*;

public class b13913 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int arr[];
    static int back[];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[100001];
        Arrays.fill(arr, 98765432);

        back = new int[100001];

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] {n, 0});

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == k) {
                System.out.println(p[1]);
                break;
            }

            for (int i = 0; i < 3; i++) {
                int next = next(i, p[0]);

                if (next < 0 || next > 100000) {
                    continue;
                }

                if (p[1] + 1 >= arr[next]) {
                    continue;
                }

                arr[next] = p[1] + 1;
                back[next] = p[0];
                dq.add(new int[] {next, p[1] + 1});
            }
        }

        printAnswer();
    }

    private static void printAnswer() {
        Stack<Integer> stack = new Stack<>();
        stack.add(k);

        int curr = k;
        while (curr != n) {
            curr = back[curr];
            stack.add(curr);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static int next(int idx, int num) {
        if (idx == 0) {
            return num << 1;
        } else if (idx == 1) {
            return ++num;
        } else {
            return --num;
        }
    }
}
