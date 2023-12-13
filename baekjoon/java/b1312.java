import java.io.*;
import java.util.*;

public class b1312 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      A %= B;
      A *= 10;
    }
    System.out.println(A / B);
  }
}
