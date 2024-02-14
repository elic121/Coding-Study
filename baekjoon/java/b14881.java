import java.io.*;
import java.util.*;

public class b14881 {

  static int N, a, b, c;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      c = Integer.parseInt(st.nextToken());
      int g = gcd(a, b);
      if (c > a && c > b) {
        System.out.println("NO");
      } else if (c % g != 0) {
        System.out.println("NO");
      } else {
        System.out.println("YES");
      }
    }
  }

  private static int gcd(int a, int b) {
    int gcd = 0;
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }

    gcd = a;
    return gcd;
  }
}
