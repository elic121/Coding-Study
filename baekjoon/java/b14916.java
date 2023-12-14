import java.io.*;

public class b14916 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int five = N / 5;
    boolean check = false;
    while (five >= 0) {
      if ((N - five * 5) % 2 == 1) {
        five--;
        continue;
      } else {
        System.out.println(five + (N - five * 5) / 2);
        check = true;
        break;
      }
    }
    if (!check) {
      System.out.println(-1);
    }
  }
}
