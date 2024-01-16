import java.io.*;
import java.util.*;

public class b1260 {

  static int N, M, V, v1[], v2[];
  static StringTokenizer st;
  static Map<Integer, List<Integer>> graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    V = Integer.parseInt(st.nextToken());
    v1 = new int[N + 1];
    v2 = new int[N + 1];

    graph = new TreeMap<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
      graph.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
    }

    dfs(V);
    System.out.println();
    bfs(V);
  }

  private static void bfs(int v) {
    Queue<Integer> q = new LinkedList<>();
    q.add(v);
    v1[v] = 1;

    while (!q.isEmpty()) {
        int val = q.poll();
        System.out.print(val+" ");
        List<Integer> tmp = graph.get(val);
        if (tmp == null) return;
        Collections.sort(tmp);
        for (int vr : tmp) {
            if (v1[vr] == 1) continue;
            q.add(vr);
            v1[vr] = 1;
        }
    }
    return;
  }

  private static void dfs(int v) {
    System.out.print(v + " ");
    v2[v] = 1;
    List<Integer> tmp = graph.get(v);
    if (tmp == null) return;
    Collections.sort(tmp);
    for (int val : tmp) {
      if (v2[val] == 1) continue;
      dfs(val);
    }
    return;
  }
}
