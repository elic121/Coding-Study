import java.io.*;
import java.util.*;

public class b2072 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    for (int cs = 1; cs < N + 1; cs++) {
      int cnt = 0;
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 10; i++) {
        int val = Integer.parseInt(st.nextToken());
        if (val % 2 == 1) {
          cnt += val;
        }
      }
      System.out.printf("#%d %d\n", cs, cnt);
    }
  }
}
