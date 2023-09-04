# https://www.acmicpc.net/problem/5427
from sys import stdin

s = stdin.readline
N = int(s())


def bfs(jPos, fPos):
    i, j = jPos[0]
    arr[i][j] = 1

    while jPos:
        # Jihun
        tmpJ = []
        for jx, jy in jPos:
            if arr[jx][jy] == "*":
                continue

            for X, Y in zip([1, -1, 0, 0], [0, 0, 1, -1]):
                nx, ny = jx + X, jy + Y

                if (nx < 0 or nx >= R) or (ny < 0 or ny >= C):
                    return arr[jx][jy]

                if arr[nx][ny] != ".":
                    continue

                arr[nx][ny] = arr[jx][jy] + 1
                tmpJ.append((nx, ny))
        jPos = tmpJ

        # Fire
        tmp = []
        for fx, fy in fPos:
            for X, Y in zip([1, -1, 0, 0], [0, 0, 1, -1]):
                nx, ny = fx + X, fy + Y
                if (nx < 0 or nx >= R) or (ny < 0 or ny >= C):
                    continue
                if arr[nx][ny] == "#":
                    continue
                if arr[nx][ny] == "*":
                    continue
                arr[nx][ny] = "*"
                tmp.append((nx, ny))
        fPos = tmp

    return "IMPOSSIBLE"


for _ in range(N):
    C, R = map(int, s().strip().split())
    arr = []
    jPos = []
    fPos = []

    for i in range(R):
        l = list(s().strip())
        for j, k in enumerate(l):
            if k == "@":
                jPos.append((i, j))
            if k == "*":
                fPos.append((i, j))
        arr.append(l)

    print(bfs(jPos, fPos))
