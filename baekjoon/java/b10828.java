import java.io.*;
import java.util.*;

public class b10828 {

  static int N;
  static Stack<Integer> stack;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    N = Integer.parseInt(br.readLine());
    stack = new Stack<>();

    for (int i = 0; i < N; i++) {
      String str = br.readLine();

      if (str.startsWith("pu")) {
        String[] split = str.split(" ");
        stack.push(Integer.parseInt(split[1]));
      } else if (str.startsWith("t")) {
        sb
          .append(stack.isEmpty() ? -1 : stack.elementAt(stack.size() - 1))
          .append("\n");
      } else if (str.startsWith("s")) {
        sb.append(stack.size()).append("\n");
      } else if (str.startsWith("e")) {
        sb.append(stack.isEmpty() ? 1 : 0).append("\n");
      } else {
        sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
      }
    }
    System.out.println(sb.toString());
  }
}
