from collections import deque

N, M = 8, 8
arr = [[0 for _ in range(M)] for _ in range(N)]
arr[1][1] = "O"
arr[3][2] = "O"
arr[5][4] = "O"
arr[6][4] = "O"

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def dfs(i, j):
    d = deque()
    d.append((i, j))
    visited = [[0 for _ in range(M)] for _ in range(N)]
    visited[i][j] = 1
    while d:
        x, y = d.popleft()
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                continue
            if visited[nx][ny] == 1:
                continue
            if arr[nx][ny] == "O":
                continue
            arr[nx][ny] = arr[x][y] + 1
            visited[nx][ny] = 1
            d.append((nx, ny))


dfs(0, 0)
for i in arr:
    print(*i)
