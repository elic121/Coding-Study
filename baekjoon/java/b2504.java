import java.io.*;
import java.util.*;

public class b2504 {

    static String[] c;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        c = br.readLine().split("");
        solve();
    }

    private static void solve() {
        Stack<String> stack = new Stack<>();
        try {
            for (int i = 0; i < c.length; i++) {
                String curr = c[i];

                if ("([".contains(curr)) {
                    stack.push(curr);
                } else {
                    int sum = 0;
                    boolean pair = curr.equals(")");

                    while (!stack.isEmpty() && !stack.peek().equals(pair ? "(" : "[")) {
                        sum += Integer.parseInt(stack.pop());
                    }
                    stack.pop();

                    if (sum == 0)
                        stack.push(pair ? "2" : "3");
                    else
                        stack.push(String.valueOf(sum * (pair ? 2 : 3)));
                }
            }

            int sum = 0;
            while (!stack.isEmpty()) {
                sum += Integer.parseInt(stack.pop());
            }
            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(0);
            return;
        }

    }
}
