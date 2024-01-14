import java.io.*;
import java.util.*;

public class b10974 {

  private static String permutation(int N, ArrayList<Integer> arr, int[] visited) {
    if (arr.size() == N) {
      String tmp = "";
      for (int i : arr) {
        tmp += Integer.toString(i);
        tmp += " ";
      }
      System.out.println(tmp);
    }

    for (int i = 1; i < N + 1; i++) {
        if (visited[i-1] == 1) {
            continue;
        }
        arr.add(i);
        visited[i-1] = 1;
        permutation(N, arr, visited);
        visited[i-1] = 0;
        arr.remove(arr.size() - 1);
    }
    return "";
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    ArrayList<Integer> arr = new ArrayList<>();
    int[] visited = new int[N];
    permutation(N, arr, visited);
  }
}
