# https://www.acmicpc.net/problem/2583
import sys
from collections import deque

s = sys.stdin.readline
M, N, K = map(int, s().split())
lst = [[0 for _ in range(M)] for _ in range(N)]

for _ in range(K):
    ax, ay, bx, by = map(int, s().split())
    for i in range(ax, bx):
        for j in range(ay, by):
            lst[i][j] = 1

cnt = 0
cntLst = []
for i in range(N):
    for j in range(M):
        if lst[i][j] != 0:
            continue

        cnt += 1
        d = deque()
        d.append((i, j))

        Bcnt = 0
        while d:
            x, y = d.popleft()

            for X, Y in zip([1, -1, 0, 0], [0, 0, 1, -1]):
                nx, ny = x + X, y + Y
                if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                    continue
                if lst[nx][ny] != 0:
                    continue

                Bcnt += 1
                lst[nx][ny] = 1
                d.append((nx, ny))
        cntLst.append([1, Bcnt][Bcnt > 0])

print(cnt)
print(*sorted(cntLst))
