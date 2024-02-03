import java.io.*;
import java.util.*;

class Piece {

  int x, y, dir, idx;

  Piece(int x, int y, int dir, int idx) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.idx = idx;
  }
}

public class b17780 {

  static int N, K, arr[][];
  static int[] dx = { 0, 0, -1, 1 };
  static int[] dy = { 1, -1, 0, 0 };
  static StringTokenizer st;
  static ArrayList<Piece>[][] chess;
  static Piece[] summary;

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    arr = new int[N][N];
    summary = new Piece[K];
    chess = new ArrayList[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
        chess[i][j] = new ArrayList<Piece>();
      }
    }

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int dir = Integer.parseInt(st.nextToken());
      Piece tmp = new Piece(x - 1, y - 1, dir - 1, i);
      chess[x - 1][y - 1].add(tmp);
      summary[i] = tmp;
    }

    int cnt = 0;
    b:while (true) {
      cnt++;
      for (int i = 0; i < K; i++) {
        move(i);
        if (isFour()) break b;
      }
      if (cnt > 1000) break;
    }

    System.out.println(cnt <= 1000 ? cnt : -1);
  }

  private static void move(int idx) {
    Piece pie = summary[idx];
    int x = pie.x;
    int y = pie.y;
    int index = findPiece(idx, x, y);
    if (index != 0) return;
    int nx = pie.x + dx[pie.dir];
    int ny = pie.y + dy[pie.dir];

    if (!isPossible(nx, ny)) {
      blue(idx);
      return;
    }

    if (arr[nx][ny] == 0) {
      white(idx);
    } else if (arr[nx][ny] == 1) {
      red(idx);
    } else if (arr[nx][ny] == 2) {
      blue(idx);
    }

    return;
  }

  private static void white(int idx) {
    Piece pie = summary[idx];
    int x = pie.x;
    int y = pie.y;
    int nx = x + dx[pie.dir];
    int ny = y + dy[pie.dir];

    if (!isPossible(nx, ny)) blue(idx);

    int size = chess[x][y].size();
    int index = findPiece(idx, x, y);

    for (int i = index; i < size; i++) {
      chess[nx][ny].add(chess[x][y].get(i));
      chess[nx][ny].get(chess[nx][ny].size() - 1).x = nx;
      chess[nx][ny].get(chess[nx][ny].size() - 1).y = ny;
    }

    for (int i = 0; i < size - index; i++) {
      chess[x][y].remove(chess[x][y].size() - 1);
    }
  }

  private static void red(int idx) {
    Piece pie = summary[idx];
    int x = pie.x;
    int y = pie.y;
    int nx = x + dx[pie.dir];
    int ny = y + dy[pie.dir];

    if (!isPossible(nx, ny)) blue(idx);

    int size = chess[x][y].size();
    int index = findPiece(idx, x, y);
    for (int i = size - 1; i >= index; i--) {
      chess[nx][ny].add(chess[x][y].get(i));
      chess[nx][ny].get(chess[nx][ny].size() - 1).x = nx;
      chess[nx][ny].get(chess[nx][ny].size() - 1).y = ny;
    }

    for (int i = 0; i < size - index; i++) {
      chess[x][y].remove(chess[x][y].size() - 1);
    }
  }

  private static void blue(int idx) {
    Piece pie = summary[idx];
    pie.dir = directionSwap(pie.dir);

    int x = pie.x;
    int y = pie.y;
    int nx = x + dx[pie.dir];
    int ny = y + dy[pie.dir];

    if (!isPossible(nx, ny) || arr[nx][ny] == 2) return;

    if (arr[nx][ny] == 0) {
      white(idx);
    } else if (arr[nx][ny] == 1) {
      red(idx);
    }
  }

  private static int findPiece(int idx, int x, int y) {
    if (chess[x][y].isEmpty()) return -1;
    for (int i = 0, size = chess[x][y].size(); i < size; i++) {
      if (chess[x][y].get(i).idx == idx) {
        return i;
      }
    }
    return -1;
  }

  private static boolean isPossible(int x, int y) {
    return 0 <= x && x < N && 0 <= y && y < N;
  }

  private static boolean isFour() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (chess[i][j].size() >= 4) {
          return true;
        }
      }
    }
    return false;
  }

  private static int directionSwap(int i) {
    return i == 0 ? 1 : (i == 1 ? 0 : (i == 2 ? 3 : 2));
  }
}
