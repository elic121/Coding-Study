import java.io.*;
import java.util.*;

public class b1918 {
    static String str;
    static ArrayDeque<String> stack;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        str = br.readLine().trim();
        solve();
    }

    private static void solve() {
        stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == ')') {
                ArrayDeque<String> dq = new ArrayDeque();
                while (true) {
                    String temp = stack.removeLast();
                    if (temp.equals("(")) {
                        break;
                    }
                    dq.addFirst(temp);
                }
                stack.add(convert(dq));
            } else {
                stack.add(String.valueOf(c));
            }
        }
        System.out.println(convert(stack));
    }

    private static String convert(ArrayDeque<String> stack) {
        Deque<String> temp = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            String s = stack.pop();
            if (s.equals("/") || s.equals("*")) {
                StringBuilder sb = new StringBuilder();
                sb.append(temp.removeLast());
                sb.append(stack.pop());
                sb.append(s);
                temp.add(sb.toString());
            } else {
                temp.add(s);
            }
        }

        Deque<String> temp2 = new ArrayDeque<>();
        while (!temp.isEmpty()) {
            String s = temp.pop();
            if (s.equals("+") || s.equals("-")) {
                StringBuilder sb = new StringBuilder();
                sb.append(temp2.removeLast());
                sb.append(temp.pop());
                sb.append(s);
                temp2.add(sb.toString());
            } else {
                temp2.add(s);
            }
        }
        return temp2.pop().trim();
    }
}
