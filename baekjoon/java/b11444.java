import java.io.*;

public class b11444 {

  static long N;
  static int R = 1000000007;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Long.parseLong(br.readLine());
    System.out.println(divide_and_conquer(N - 1)[0][0]);
  }

  private static long[][] mul_matrix(long[][] a, long[][] b) {
    long[][] tmp = new long[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
          tmp[i][j] += (a[i][k] * b[k][j]) % R;
        }
        tmp[i][j] %= R;
      }
    }
    return tmp;
  }

  private static long[][] divide_and_conquer(long n) {
    if (n <= 1) {
      return new long[][] { { 1, 1 }, { 1, 0 } };
    }

    long[][] val = divide_and_conquer(n / 2);
    long[][] sol = mul_matrix(val, val);
    if (n % 2 == 0) {
      return sol;
    } else {
      return mul_matrix(new long[][] { { 1, 1 }, { 1, 0 } }, sol);
    }
  }
}
