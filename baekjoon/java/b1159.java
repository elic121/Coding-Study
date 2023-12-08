import java.util.*;

public class b1159 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    sc.nextLine();
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for (int i = 0; i < N; i++) {
      String tmp = sc.nextLine();
      map.put(tmp.charAt(0), map.getOrDefault(tmp.charAt(0), 0) + 1);
    }
    ArrayList<Character> sol = new ArrayList<>();
    for (char key : map.keySet()) {
      if (map.get(key) >= 5) {
        sol.add(key);
      }
    }
    Character[] arr = sol.toArray(Character[]::new);
    Arrays.sort(arr);
    if (arr.length > 0) {
      for (Character i : arr) {
        System.out.print(i);
      }
    } else {
        System.out.println("PREDAJA");
    }
    sc.close();
  }
}
