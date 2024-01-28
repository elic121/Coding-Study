import java.io.*;
import java.util.*;

public class b2529 {

  static long MAX, MIN;
  static int N;
  static String MAX_STRING, MIN_STRING;
  static char[] arr;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new char[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = st.nextToken().charAt(0);
    }

    boolean[] visited = new boolean[10];
    MAX = Long.MIN_VALUE;
    MIN = Long.MAX_VALUE;
    MAX_STRING = "";
    MIN_STRING = "";
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 10; i++) {
      visited[i] = true;
      backTracking(1, i, sb.append(i), visited);
      sb.setLength(0);
      visited[i] = false;
    }
    System.out.println(MAX_STRING);
    System.out.println(MIN_STRING);
  }

  private static void backTracking(
    int idx,
    int prev,
    StringBuilder sol,
    boolean[] visited
  ) {
    if (idx == N + 1) {
      long num = Long.parseLong(sol.toString());
      if (num > MAX) {
        MAX = num;
        MAX_STRING = sol.toString();
      }
      if (num < MIN) {
        MIN = num;
        MIN_STRING = sol.toString();
      }
      return;
    }

    boolean c = arr[idx - 1] == '<';
    int start = c ? prev + 1 : prev - 1;
    int end = c ? 10 : -1;
    int sign = c ? 1 : -1;

    for (int i = start; i != end; i += sign) {
      if (visited[i]) continue;
      visited[i] = true;
      backTracking(idx + 1, i, sol.append(i), visited);
      sol.setLength(sol.length() - 1);
      visited[i] = false;
    }
  }
}
