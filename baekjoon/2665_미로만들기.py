# https://www.acmicpc.net/problem/2665
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def bfs():
    d = deque()
    d.append((0, 0, 0))
    visited = [[0 for _ in range(N)] for _ in range(N)]
    visited[0][0] = 1
    while d:
        x, y, cnt = d.popleft()
        if x == N - 1 and y == N - 1:
            print(cnt)
            return
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                continue
            if visited[nx][ny]:
                continue
            visited[nx][ny] = 1
            if arr[nx][ny] == 0:
                d.append((nx, ny, cnt + 1))
            else:
                d.appendleft((nx, ny, cnt))
    return


if __name__ == "__main__":
    N = int(input())
    arr = []
    for _ in range(N):
        lst = list(map(int, list(input())))
        arr.append(lst)
    bfs()
