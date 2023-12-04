import java.util.*;

public class b2884 {

  public static void main(String[] args) throws NullPointerException {
    Scanner sc = new Scanner(System.in);
    int iter = sc.nextInt();
    sc.nextLine();
    int maximum = 0;
    for (int i = 0; i < iter; i++) {
      int[] arr = new int[4];
      for (int j = 0; j < 4; j++) {
        arr[j] = sc.nextInt();
      }
      int tmp = sol(arr);
      if (tmp > maximum) {
        maximum = tmp;
      }
    }
    sc.close();
    System.out.println(maximum);
  }

  public static int sol(int[] arr) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < 4; i++) {
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }
    int max = 0;
    int k = 0;
    for (int key : map.keySet()) {
      int value = map.get(key);
      if (value > max) {
        max = value;
        k = key;
      }
    }
    if (max == 4) {
      return 50000 + k * 5000;
    } else if (max == 3) {
      return 10000 + k * 1000;
    } else if (max == 2) {
      if (map.size() == 2) {
        int sum = 0;
        for (int key : map.keySet()) {
          sum += key * 500;
        }
        return 2000 + sum;
      } else {
        return 1000 + k * 100;
      }
    } else {
      for (int i = 6; i > 0; i--) {
        int value = map.getOrDefault(i, 0);
        if (value >= 1) {
          return i * 100;
        }
      }
    }
    return 0;
  }
}
