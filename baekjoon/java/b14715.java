import java.io.*;

public class b14715 {

  static int N, CNT;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    CNT = 1;

    for (int i = 2; i <= (int) Math.sqrt(N); i++) {
      while (N % i == 0) {
        CNT++;
        N /= i;
      }
      if (N == 1) {
        CNT--;
      }
    }

    System.out.println((int) Math.ceil(Math.log10(CNT) / Math.log10(2)));
  }
}
