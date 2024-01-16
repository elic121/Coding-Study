import java.io.*;
import java.util.*;

public class b17413 {

  static boolean tag_check;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringBuilder sb = new StringBuilder();

    ArrayList<Character> charArray = new ArrayList<>();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (ch == '<') {
        if (!charArray.isEmpty()) {
          Collections.reverse(charArray);
          StringBuilder tmp = new StringBuilder();
          for (Character c : charArray) {
            tmp.append(c);
          }
          sb.append(tmp.toString());
          charArray.clear();
        }
        tag_check = true;
        sb.append('<');
      } else {
        if (ch == ' ') {
          Collections.reverse(charArray);
          StringBuilder tmp = new StringBuilder();
          for (Character c : charArray) {
            tmp.append(c);
          }
          sb.append(tmp.toString() + " ");
          charArray.clear();
          continue;
        }
        if (tag_check) {
          if (ch == '>') {
            tag_check = false;
          }
          sb.append(ch);
        } else {
          charArray.add(ch);
        }
      }
    }
    if (!charArray.isEmpty()) {
      Collections.reverse(charArray);
      StringBuilder tmp = new StringBuilder();
      for (Character c : charArray) {
        tmp.append(c);
      }
      sb.append(tmp.toString());
      charArray.clear();
    }
    System.out.println(sb.toString());
  }
}
