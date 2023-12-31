import java.io.*;
import java.util.*;

public class b1249 {

  static int[][] arr;
  static int size;
  static int[][] p = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

  public static class Node {

    int x, y, dist;

    Node(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }
  }

  private static int bfs() {
    int[][] tmp = new int[size][size];
    for (int i = 0; i < size; i++) {
      Arrays.fill(tmp[i], Integer.MAX_VALUE);
    }

    Deque<Node> d = new ArrayDeque<>();
    d.add(new Node(0, 0, 0));
    int dist = Integer.MAX_VALUE;

    while (!d.isEmpty()) {
      Node node = d.removeFirst();
      for (int[] q : p) {
        int dx = node.x + q[0];
        int dy = node.y + q[1];
        if (dx == size - 1 && dy == size - 1) {
          dist = Math.min(dist, node.dist);
          continue;
        }
        if (dx < 0 || dx >= size) continue;
        if (dy < 0 || dy >= size) continue;
        int new_dist = node.dist + arr[dx][dy];
        if (tmp[dx][dy] > new_dist) {
          tmp[dx][dy] = new_dist;
          d.add(new Node(dx, dy, new_dist));
        }
      }
    }
    return dist;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    for (int tc = 0 + 1; tc < N + 1; tc++) {
      size = Integer.parseInt(br.readLine());
      arr = new int[size][size];

      for (int i = 0; i < size; i++) {
        String line = br.readLine();
        for (int q = 0; q < line.length(); q++) {
          arr[i][q] = Character.getNumericValue(line.charAt(q));
        }
      }
      int sol = bfs();
      System.out.printf("#%d %d\n", tc, sol);
    }
  }
}
