import java.io.*;
import java.util.*;

public class b1032 {

  static String[] str;
  static boolean[] check;
  static int N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    str = new String[N];
    
    for (int i = 0; i < N; i++) {
        str[i] = br.readLine();
    }
    check = new boolean[str[0].length()];

    for (int i = 0; i < str[0].length(); i++) {
      boolean isDifferent = false;
      for (int j = 1; j < N; j++) {
        if (str[j].charAt(i) != str[0].charAt(i)) {
          isDifferent = true;
          break;
        }
      }
      if (isDifferent) {
        check[i] = true;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str[0].length(); i++) {
        sb.append(check[i] ? '?' : str[0].charAt(i));
    }

    System.out.println(sb.toString());
  }
}
