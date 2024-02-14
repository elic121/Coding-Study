import java.io.*;
import java.util.*;

public class b16953 {

  static int A, B, CNT;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] str = br.readLine().split(" ");
    A = Integer.parseInt(str[0]);
    B = Integer.parseInt(str[1]);
    CNT = 0;

    solve();
  }

  private static void solve() {
    bfs();
    System.out.println(CNT == 0 ? -1 : CNT + 1);
  }

  private static void bfs() {
    Deque<long[]> que = new ArrayDeque<>();
    que.add(new long[] { A, 0 });

    while (!que.isEmpty()) {
      long val[] = que.remove();
      if (val[0] == B) {
        CNT = (int) val[1];
        return;
      }
      long n1 = 2 * val[0];
      if (n1 <= B) {
        que.add(new long[] { n1, val[1] + 1 });
      }
      long n2 = 10 * val[0] + 1;
      if (n2 <= B) {
        que.add(new long[] { n2, val[1] + 1 });
      }
    }
  }
}
