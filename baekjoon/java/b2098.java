import java.io.*;
import java.util.*;

public class b2098 {

  static int N, start, SIZE, INF, INIT;
  static int[][] graph, dp;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    // 초기화 값과 불가능 한 값이 같으면 안됨 
    // (방문했는지 안했는지 판별이 불가하므로)
    INF = 17_000_001;
    INIT = 2 * INF + 1;

    graph = new int[N][N];
    dp = new int[N][SIZE = (1 << N) - 1];

    for (int i = 0; i < N; i++) {
      Arrays.fill(dp[i], INIT);
    }

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        graph[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(travel(start, 0));
  }

  private static int travel(int current, int visit) {
    visit |= 1 << current;

    // 모든 노드를 방문한 상태에서 다시
    // 처음 노드로 갈 수 있으면 해당 값 return;
    // 갈 수 없으면 INF값 부여
    if (visit == (1 << N) - 1) {
      return graph[current][start] == 0 ? INF : graph[current][start];
    }

    // 초기화 상태가 아니면 이미 방문한 것이므로
    // (값이 계산되어 있으므로) 해당 값을 리턴
    if (dp[current][visit] != INIT) {
      return dp[current][visit];
    }

    for (int i = 0; i < N; i++) {
      if (graph[current][i] == 0) continue;
      if ((visit & 1 << i) != 0) continue;

      // 현재 노드에서 다음 노드까지 거리 + 다음노드에서 도착지점까지 거리
      int dist = graph[current][i] + travel(i, visit | 1 << i);
      dp[current][visit] = Math.min(dp[current][visit], dist);
    }

    return dp[current][visit];
  }
}
