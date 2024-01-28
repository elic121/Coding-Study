import java.io.*;
import java.util.*;

public class b16985 {

  static class Point {

    int x, y, z, dist;

    Point(int x, int y, int z, int dist) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.dist = dist;
    }
  }

  static final int N = 5;
  static int MIN;
  static int[][][] arr;
  static int[][][] copy;
  static StringTokenizer st;
  static int[] dx = { 0, 0, 1, 0, 0, -1 };
  static int[] dy = { 0, 1, 0, 0, -1, 0 };
  static int[] dz = { 1, 0, 0, -1, 0, 0 };
  static int[][] stt = {
    { 0, 0, 0 },
    { 0, 0, N - 1 },
    { 0, N - 1, 0 },
    { N - 1, 0, 0 },
  };
  static ArrayList<Integer[]> fiveFactorial;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    MIN = Integer.MAX_VALUE;
    arr = new int[N][N][N];
    for (int f = 0; f < N; f++) {
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          arr[f][i][j] = Integer.parseInt(st.nextToken());
        }
      }
    }

    fiveFactorial = new ArrayList<Integer[]>();
    factorial(0, new boolean[N], new StringBuilder());

    backTracking(0, new ArrayList<Integer>());
    System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
  }

  private static void backTracking(int iter, ArrayList<Integer> rotates) {
    if (iter == N) {
      test(rotates);
      return;
    }

    for (int i = 0; i < 4; i++) {
      rotates.add(i);
      backTracking(iter + 1, rotates);
      rotates.remove(rotates.size() - 1);
    }
  }

  private static void factorial(int iter, boolean[] visited, StringBuilder sb) {
    if (iter == N) {
      Integer[] tmp = new Integer[5];
      String s = sb.toString();
      for (int i = 0; i < N; i++) {
        tmp[i] = Character.getNumericValue(s.charAt(i));
      }
      fiveFactorial.add(tmp);
      return;
    }

    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;
      visited[i] = true;
      sb.append(String.valueOf(i));
      factorial(iter + 1, visited, sb);
      sb.deleteCharAt(sb.length() - 1);
      visited[i] = false;
    }
  }

  private static void test(ArrayList<Integer> rotates) {
    for (int it = 0; it < fiveFactorial.size(); it++) {
      copy = new int[N][N][N];
      for (int r = 0; r < N; r++) {
        copy[fiveFactorial.get(it)[r]] = rotate(r, rotates.get(r));
      }
      for (int[] s : stt) {
        bfs(s[0], s[1], s[2]);
      }
    }
  }

  private static int[][] rotate(int floor, int iter) {
    int rotArr[][] = new int[N][N];
    rotArr = copyCube(floor);
    for (int it = 0; it < iter; it++) {
      int tmp[][] = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          tmp[i][j] = rotArr[N - 1 - j][i];
        }
      }
      rotArr = tmp;
    }
    return rotArr;
  }

  private static int[][] copyCube(int floor) {
    int tmp[][] = new int[N][N];
    for (int i = 0; i < N; i++) {
      tmp[i] = Arrays.copyOf(arr[floor][i], N);
    }
    return tmp;
  }

  private static void bfs(int x, int y, int z) {
    if (copy[x][y][z] == 0) return;

    int destX = x == 0 ? N - 1 : 0;
    int destY = y == 0 ? N - 1 : 0;
    int destZ = z == 0 ? N - 1 : 0;

    if (copy[destX][destY][destZ] == 0) return;

    Deque<Point> d = new ArrayDeque<>();
    d.add(new Point(x, y, z, 0));

    boolean[][][] visited = new boolean[N][N][N];
    visited[x][y][z] = true;

    while (!d.isEmpty()) {
      Point p = d.remove();
      if (p.x == destX && p.y == destY && p.z == destZ) {
        MIN = Math.min(MIN, p.dist);
        return;
      }
      for (int idx = 0; idx < dx.length; idx++) {
        int nx = p.x + dx[idx];
        int ny = p.y + dy[idx];
        int nz = p.z + dz[idx];
        if (!isPossible(nx, ny, nz)) continue;
        if (visited[nx][ny][nz]) continue;
        if (copy[nx][ny][nz] == 0) continue;
        visited[nx][ny][nz] = true;
        d.add(new Point(nx, ny, nz, p.dist + 1));
      }
    }
  }

  private static boolean isPossible(int x, int y, int z) {
    return 0 <= x && x < N && 0 <= y && y < N && 0 <= z && z < N;
  }
}
