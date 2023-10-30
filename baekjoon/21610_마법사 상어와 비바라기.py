# https://www.acmicpc.net/problem/21610
from sys import stdin

s = stdin.readline
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]

DX = [-1, -1, 1, 1]
DY = [1, -1, 1, -1]


def movepos(x, y, D, S):
    nx, ny = S * dx[D - 1], S * dy[D - 1]
    X, Y = (x + nx) % N, (y + ny) % N

    return X, Y


def cross(x, y):
    global arr
    cnt = 0
    for n, m in zip(DX, DY):
        nx, ny = x + n, y + m
        if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
            continue
        if arr[nx][ny] > 0:
            cnt += 1
    return cnt


N, M = map(int, s().split())
arr = []
for _ in range(N):
    arr.append(list(map(int, s().split())))

cloud_pos = []
for idx in range(M):
    D, S = map(int, s().split())
    visited = [[0 for _ in range(N)] for _ in range(N)]

    if idx == 0:
        for j in range(N - 2, N):
            for k in range(2):
                x, y = movepos(j, k, D, S)
                cloud_pos.append((x, y))
                visited[x][y] = 1

        for x, y in cloud_pos:
            arr[x][y] += 1

        for x, y in cloud_pos:
            cnt = cross(x, y)
            arr[x][y] += cnt

        cloud_pos2 = []
        for i in range(N):
            for j in range(N):
                if visited[i][j] == 1:
                    continue
                if arr[i][j] >= 2:
                    arr[i][j] -= 2
                    cloud_pos2.append((i, j))
        cloud_pos = cloud_pos2

    else:
        T2 = []
        for X, Y in cloud_pos:
            x, y = movepos(X, Y, D, S)
            T2.append((x, y))
            visited[x][y] = 1

        for X, Y in T2:
            arr[X][Y] += 1

        for X, Y in T2:
            cnt = cross(X, Y)
            arr[X][Y] += cnt

        cloud_pos2 = []
        for i in range(N):
            for j in range(N):
                if visited[i][j] == 1:
                    continue
                if arr[i][j] >= 2:
                    arr[i][j] -= 2
                    cloud_pos2.append((i, j))
        cloud_pos = cloud_pos2

ANS = 0
for i in range(N):
    for j in range(N):
        ANS += arr[i][j]

print(ANS)
