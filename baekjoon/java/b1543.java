import java.io.*;

public class b1543 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String sen = br.readLine().strip();
    String com = br.readLine().strip();
    int L = com.length();
    int idx = 0;
    int sol = 0;
    while (L + idx <= sen.length()) {
      int cnt = 0;
      for (int i = idx; i < idx + L; i++) {
        char s1 = com.charAt(i - idx);
        char s2 = sen.charAt(i);
        if (s1 == s2) {
          cnt++;
        } else {
          break;
        }
      }
      if (cnt == L) {
        idx += L;
        sol++;
      } else {
        idx++;
      }
    }
    System.out.println(sol);
  }
}
