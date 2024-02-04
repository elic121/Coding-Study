import java.io.*;
import java.util.*;

public class b11005 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] str = br.readLine().split(" ");
    int N = Integer.parseInt(str[0]);
    int M = Integer.parseInt(str[1]);
    System.out.println(convert(N, M));
  }

  public static String convert(int N, int M) {
    StringBuilder sb = new StringBuilder();
    ArrayList<Integer> arr = new ArrayList<>();
    while (N >= M) {
      arr.add(N % M);
      N /= M;
    }
    arr.add(N);

    for (int i = arr.size() - 1; i >= 0; i--) {
      int val = arr.get(i);
      sb.append(val < 10 ? (char)(val + '0') : (char)('A' + (val - 10)));
    }

    return sb.toString();
  }
}
