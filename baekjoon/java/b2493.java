import java.io.*;
import java.util.*;

public class b2493 {

  static class Top {

    int height, idx;

    Top(int height, int idx) {
      this.height = height;
      this.idx = idx;
    }
  }

  static Top arr[];
  static int N, cnt[];
  static Stack<Top> stack;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new Top[N];
    cnt = new int[N];
    stack = new Stack<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int val = Integer.parseInt(st.nextToken());
      arr[i] = new Top(val, i + 1);
    }

    for (int i = 0; i < N; i++) {
      while (!stack.isEmpty() && stack.lastElement().height < arr[i].height) {
        stack.pop();
      }
      System.out.print((stack.isEmpty() ? 0 : stack.lastElement().idx) + " ");
      stack.push(arr[i]);
    }
  }
}
