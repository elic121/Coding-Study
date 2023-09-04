# https://www.acmicpc.net/problem/1926
import sys
from collections import deque

s = sys.stdin.readline
N, M = map(int, s().split())
lst = [list(map(int, s().split())) for _ in range(N)]
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def bfs(i, j):
    C = 0
    d = deque()
    d.append((i, j))
    while d:
        x, y = d.popleft()
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                continue
            if lst[nx][ny] == 0:
                continue
            d.append((nx, ny))
            lst[nx][ny] = 0
            C += 1
    return [1, C][C >= 1]


cnt = 0
MAX = 0
for i in range(N):
    for j in range(M):
        if lst[i][j] == 1:
            tmp = bfs(i, j)
            if tmp > MAX:
                MAX = tmp
            cnt += 1

print(cnt)
print([0, MAX][cnt >= 1])
