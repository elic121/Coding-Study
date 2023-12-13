import java.io.*;

public class b2018 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int CNT = 0;
    for (int i = 1; i < (n / 2) + 1; i++) {
      int cnt = 0;
      int v = i;
      while (true) {
        cnt += v;
        v++;
        if (cnt > n) {
          break;
        } else if (cnt == n) {
          CNT++;
          break;
        }
      }
    }
    System.out.println(CNT+1);
  }
}
