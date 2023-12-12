import java.io.*;
import java.util.*;

public class b10814 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    Map<Integer, List<String>> arr = new HashMap<>();
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int age = Integer.parseInt(st.nextToken());
      String name = st.nextToken();

      arr.computeIfAbsent(age, k -> new ArrayList<>()).add(name);
    }

    List<Integer> keyList = new ArrayList<>(arr.keySet());
    Collections.sort(keyList);

    for (int key : keyList) {
      List<String> names = arr.get(key);
      for (String name : names) {
        System.out.println(key + " " + name);
      }
    }
  }
}
