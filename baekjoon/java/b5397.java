import java.io.*;
import java.util.*;

public class b5397 {

  static int N;
  static String str;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      str = br.readLine();
      solution();
    }
  }

  private static void solution() {
    char[] ch = str.toCharArray();
    Stack<Character> arr = new Stack<>();

    for (int i = 0; i < ch.length; i++) {
      if (ch[i] == '<') {
        arr.push(ch[i]);
      } else if (ch[i] == '-') {
        if (!arr.isEmpty()) {
          arr.pop();
        }
      } else {
        if (arr.isEmpty()) {
          arr.push(ch[i]);
        } else if (arr.peek() == '>') {} else if (arr.peek() == '<') {
          ArrayList<Character> temp = new ArrayList<>();
          int cnt = 0;
          while (!arr.isEmpty() && arr.peek() == '<') {
            cnt++;
            arr.pop();
          }

          for (int j = 0; j < cnt; j++) {
            if (arr.isEmpty()) break;
            temp.add(arr.pop());
          }

          arr.push(ch[i]);

          for (int j = temp.size() - 1; j >= 0; j--) {
            arr.push(temp.get(j));
          }
        } else {
          arr.push(ch[i]);
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.size(); i++) {
      sb.append(arr.get(i));
    }
    System.out.println(sb.toString());
  }
}
