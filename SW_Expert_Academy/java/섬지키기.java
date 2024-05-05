import java.util.ArrayDeque;

class 섬지키기
{   
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int[][][] arr;
    static boolean[][] visited;
    static int size;

	public void init(int N, int mMap[][])
	{
        size = N;
        arr = new int[6][N + 2][N + 2];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                arr[0][i][j] = mMap[i - 1][j - 1];
            } 
        }
        for (int l = 1; l < 6; l++) {
            next(l);
        }

        for (int l = 0; l < 6; l++) {
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    System.out.print(arr[l][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static void next(int level) {
        int[][] temp = new int[size + 2][size + 2];
        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                temp[i][j] = arr[level - 1][i][j]; 
            }
        }

        visited = new boolean[size + 2][size + 2];
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                floodFill(temp, i, j, level, size);
            }
        }

        arr[level] = temp;
    }

    private static void floodFill(int[][] temp, int x, int y, int level, int size) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (temp[nx][ny] == 0 && temp[x][y] == level) {
                bfs(temp, x, y, size, level);
            }
        }
    }

    private static void bfs(int[][] temp, int x, int y, int size, int level) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] {x, y});

        visited[x][y] = true;
        temp[x][y] = 0;
        while (!dq.isEmpty()) {
            int[] p = dq.pop();
            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];
                if (nx <= 0 || nx > size || ny <= 0 || ny > size) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (temp[nx][ny] > level) {
                    continue;
                }
                visited[nx][ny] = true;
                dq.add(new int[] {nx, ny});
                temp[nx][ny] = 0;
            }
        }
    }

	public int numberOfCandidate(int M, int mStructure[])
	{
		return 0;
	}

	public int maxArea(int M, int mStructure[], int mSeaLevel)
	{
		return 0;
	}
}