import java.io.*;
import java.util.*;

public class b1247 {

  static Point[] arr;
  static int N;
  static int distance = Integer.MAX_VALUE;
  static int[] lst;
  static int[] visited;

  public static class Point {

    int x, y;

    Point(String x, String y) {
      this.x = Integer.parseInt(x);
      this.y = Integer.parseInt(y);
    }

    int dist(Point p) {
      int X = x - p.x;
      int Y = y - p.y;
      return Math.abs(X) + Math.abs(Y);
    }
  }

  public static void backTracking(int n, int[] visited, int[] lst) {
    if (n == N + 1) {
      int score = 0;
      for (int i = 1; i < N + 2; i++) {
        score += arr[lst[i]].dist(arr[lst[i - 1]]);
        if (score >= distance) return;
      }
      distance = Math.min(distance, score);
      return;
    }

    for (int i = 2; i < N + 2; i++) {
      if (visited[i - 2] == 1) continue;
      lst[n] = i;
      visited[i - 2] = 1;
      backTracking(n + 1, visited, lst);
      visited[i - 2] = 0;
      lst[n] = 0;
    }
    return;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int T = 1; T < tc + 1; T++) {
      N = Integer.parseInt(br.readLine());
      arr = new Point[N + 2];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N + 2; i++) {
        String a = st.nextToken();
        String b = st.nextToken();
        arr[i] = new Point(a, b);
      }
      lst = new int[N + 2];
      lst[0] = 0;
      lst[N + 1] = 1;
      visited = new int[N];
      distance = Integer.MAX_VALUE;

      backTracking(1, visited, lst);
      System.out.printf("#%d %d\n", T, distance);
    }
  }
}
