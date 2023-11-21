# https://www.acmicpc.net/problem/14497
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def bfs():
    visited = [[0 for _ in range(M)] for _ in range(N)]
    visited[x1][y1] = 1
    d = deque()
    d.append((x1, y1, 0))

    while d:
        i, j, cnt = d.popleft()
        if i == x2 and j == y2:
            print(cnt + 1)
            return
        for x, y in zip(dx, dy):
            nx, ny = i + x, j + y
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                continue
            if visited[nx][ny]:
                continue
            visited[nx][ny] = 1
            if arr[nx][ny] == "1":
                d.append((nx, ny, cnt + 1))
            else:
                d.appendleft((nx, ny, cnt))

    return


if __name__ == "__main__":
    N, M = map(int, input().split())
    x1, y1, x2, y2 = map(lambda x: int(x) - 1, input().split())

    arr = []
    for _ in range(N):
        lst = list(input().strip())
        arr.append(lst)

    bfs()
