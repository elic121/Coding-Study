import java.util.*;
import java.io.*;

/**
 * b17952
 */
public class b17952 {

    static class HW {
        int score, period;

        HW(int score, int period) {
            this.score = score;
            this.period = period;
        }
    }

    static int N, SCORE;
    static Stack<HW> stack;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int s = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                stack.add(new HW(s, m));

            }

            if (!stack.isEmpty()) {
                HW hw = stack.pop();
                if (hw.period == 1) {
                    SCORE += hw.score;
                } else {
                    stack.add(new HW(hw.score, hw.period - 1));
                }
            }

        }

        System.out.println(SCORE);
    }
}