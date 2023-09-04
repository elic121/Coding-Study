# https://www.acmicpc.net/problem/6593
import sys
from collections import deque

s = sys.stdin.readline
dx = [1, -1, 0, 0, 0, 0]
dy = [0, 0, 1, -1, 0, 0]
dz = [0, 0, 0, 0, 1, -1]


def bfs(i, j, k):
    if i == ePos[0] and j == ePos[1] and k == ePos[2]:
        return 0

    d = deque()
    d.append((i, j, k))
    lst[i][j][k] = 0

    while d:
        x, y, z = d.popleft()
        for X, Y, Z in zip(dx, dy, dz):
            nx, ny, nz = x + X, y + Y, z + Z

            if (nx < 0 or nx >= L) or (ny < 0 or ny >= R) or (nz < 0 or nz >= C):
                continue

            if not (lst[nx][ny][nz] == "." or lst[nx][ny][nz] == "E"):
                continue

            if nx == ePos[0] and ny == ePos[1] and nz == ePos[2]:
                ans = lst[x][y][z] + 1
                return ans

            lst[nx][ny][nz] = lst[x][y][z] + 1
            d.append((nx, ny, nz))

    return -1


while True:
    L, R, C = map(int, s().split())
    if L == 0 and R == 0 and C == 0:
        break
    lst: list = []
    sPos: tuple
    ePos: tuple

    ans = 0

    for i in range(L):
        tmp = []
        for j in range(R):
            l = list(s().strip())
            tmp2 = []
            for idx, k in enumerate(l):
                if k == "S":
                    sPos = (i, j, idx)
                if k == "E":
                    ePos = (i, j, idx)
                tmp2.append(k)
            tmp.append(tmp2)
        s()
        lst.append(tmp)

    ans = bfs(sPos[0], sPos[1], sPos[2])
    print([f"Escaped in {ans} minute(s).", "Trapped!"][ans == -1])
