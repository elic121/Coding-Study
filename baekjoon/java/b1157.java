import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class b1157 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String l = sc.next();
    Map<Character, Integer> dict = new HashMap<Character, Integer>();
    for (int i = 0; i < l.length(); i++) {
      char tmp = Character.toUpperCase(l.charAt(i));
      try {
        dict.put(tmp, dict.get(tmp) + 1);
      } catch (Exception e) {
        dict.put(tmp, 1);
      }
    }
    Integer MAX = 0;
    for (int i:dict.values()){
      if (MAX < i) MAX = i;
    }

    char sign = '\0';
    int cnt = 0;
    boolean find = true;

    for (char i : dict.keySet()){
      int value = dict.get(i);
      if (value == MAX) {
        cnt += 1;
        sign = i;
      }
      if (cnt >= 2){
        find = false;
        break;
      }
    }

    if (find){
      System.out.println(sign);
    }else{
      System.out.println('?');
    }

    sc.close();
  }
}
