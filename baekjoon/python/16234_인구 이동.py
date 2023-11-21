# https://www.acmicpc.net/problem/16234
from sys import stdin
from collections import deque

s = stdin.readline

N, L, R = map(int, s().split())
arr = []
for _ in range(N):
    lst = list(map(int, s().split()))
    arr.append(lst)

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def bfs(i, j):
    d = deque()
    d.append((i, j))

    T[i][j] = 1
    VAL = [arr[i][j]]
    tmp = [(i, j)]

    while d:
        x, y = d.popleft()
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                continue
            if T[nx][ny] == 1:
                continue

            val = abs(arr[nx][ny] - arr[x][y])
            if val < L or R < val:
                continue

            T[nx][ny] = 1
            VAL.append(arr[nx][ny])
            d.append((nx, ny))
            tmp.append((nx, ny))

    V = sum(VAL) // len(VAL)
    for x, y in tmp:
        arr[x][y] = V


COUNT = 0
while True:
    T = [[0 for _ in range(N)] for _ in range(N)]
    cnt = 0
    for i in range(N):
        for j in range(N):
            if T[i][j]:
                continue
            cnt += 1
            bfs(i, j)

    if cnt == N * N:
        break
    COUNT += 1

print(COUNT)
