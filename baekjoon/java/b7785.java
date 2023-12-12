import java.io.*;
import java.util.*;

public class b7785 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    Set<String> set = new HashSet<String>();
    List<String> list = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String name = st.nextToken();
      String order = st.nextToken();
      if (order.equals("enter")) {
        set.add(name);
      } else {
        set.remove(name);
      }
    }

    list.addAll(set);
    Collections.sort(list, Collections.reverseOrder());

    for (String name : list) {
      System.out.println(name);
    }
  }
}
