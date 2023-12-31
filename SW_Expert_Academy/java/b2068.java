import java.io.*;
import java.util.*;

public class b2068 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int M = -1;
      for (int j = 0; j < 10; j++) {
        int val = Integer.parseInt(st.nextToken());
        if (M < val) M = val;
      }
      System.out.printf("#%d %d\n",i,M);
    }
  }
}
