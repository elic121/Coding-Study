import java.io.*;
import java.util.*;

public class b6198 {

  static long N, CNT, arr[];
  static Stack<Long> stack;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Long.parseLong(br.readLine());
    arr = new long[(int) N];
    stack = new Stack<>();
    for (int i = 0; i < N; i++) {
      arr[i] = Long.parseLong(br.readLine());
    }

    solution();
    System.out.println(CNT);
  }

  private static void solution() {
    for (int i = 0; i < N; i++) {
      long val = arr[i];
      while (!stack.isEmpty() && val >= stack.lastElement()) {
        stack.pop();
      }
      CNT += stack.size();
      stack.push(val);
    }
  }
}
