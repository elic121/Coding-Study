# https://www.acmicpc.net/problem/14502

# 0은 빈칸
# 1은 벽
# 2는 바이러스

import sys
from copy import deepcopy
from collections import deque

s = sys.stdin.readline

N, M = map(int, s().split())
lst = [list(map(int, s().split())) for _ in range(N)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def cnt(L):
    c = 0
    for i in range(N):
        for j in range(M):
            if L[i][j] == 0:
                c += 1
    return c


def bfs(L):
    for i in range(N):
        for j in range(M):
            if L[i][j] == 2:
                d = deque()
                d.append((i, j))

                while d:
                    x, y = d.popleft()
                    for X, Y in zip(dx, dy):
                        nx, ny = x + X, y + Y
                        if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                            continue
                        if L[nx][ny] != 0:
                            continue
                        L[nx][ny] = 2
                        d.append((nx, ny))

    return cnt(L)


MAX = -1

for i in range(N * M):
    ax, ay = divmod(i, M)
    if lst[ax][ay] != 0:
        continue
    for j in range(i + 1, N * M):
        bx, by = divmod(j, M)
        if lst[bx][by] != 0:
            continue
        for k in range(j + 1, N * M):
            cx, cy = divmod(k, M)
            if lst[cx][cy] != 0:
                continue

            l = deepcopy(lst)
            l[ax][ay] = 1
            l[bx][by] = 1
            l[cx][cy] = 1

            C = bfs(l)
            if MAX < C:
                MAX = C

print(MAX)
