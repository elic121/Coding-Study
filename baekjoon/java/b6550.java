import java.io.*;
import java.util.StringTokenizer;

public class b6550 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    while ((line = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(line);
      if (st.countTokens() == 0) break;
      String s = st.nextToken().strip();
      String t = st.nextToken().strip();

      int idx = 0;
      int find = 0;
      while (idx < t.length() && find < s.length()) {
        int tmp = find;
        for (int i = idx; i < t.length(); i++) {
          if (s.charAt(find) == t.charAt(i)) {
            find++;
            idx = i + 1;
            break;
          }
        }
        if (tmp == find) {
          break;
        }
      }
      if (find == s.length()) {
        System.out.println("Yes");
      } else {
        System.out.println("No");
      }
    }
  }
}
