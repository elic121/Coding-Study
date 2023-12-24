import java.io.*;
import java.util.StringTokenizer;

public class b1049 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine().strip());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int MIN_SET = 100_000_000_9;
    int MIN_ONE = 100_000_000_9;
    for (int i = 0; i < M; i++) {
      StringTokenizer st1 = new StringTokenizer(br.readLine().strip());
      int a = Integer.parseInt(st1.nextToken());
      int b = Integer.parseInt(st1.nextToken());
      if (a < MIN_SET) MIN_SET = a;
      if (b < MIN_ONE) MIN_ONE = b;
    }
    int sol = 0;
    if (MIN_ONE * 6 < MIN_SET) {
      sol = MIN_ONE * N;
    } else {
      int cnt = N / 6;
      int tmp = MIN_SET * cnt + (N - cnt * 6) * MIN_ONE;
      int b = 0;
      if (N - cnt * 6 > 0) {
        b++;
      }
      if ((cnt + b) * MIN_SET < tmp) {
        sol = (cnt + b) * MIN_SET;
      } else {
        sol = tmp;
      }
    }
    System.out.println(sol);
  }
}
