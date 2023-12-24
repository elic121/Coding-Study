import java.io.*;

public class b9625 {

  public static String fibo(int n) {
    int a = 0;
    int b = 1;
    for (int i = 0; i < n - 1; i++) {
      int tmp = a;
      a = b;
      b = a + tmp;
    }
    return Integer.toString(a) + " " + Integer.toString(b);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String sol = fibo(N);
    System.out.println(sol);
  }
}
