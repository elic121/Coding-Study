# https://www.acmicpc.net/problem/6593
import sys
sys.setrecursionlimit(10**7)
s = sys.stdin.readline
dx = [1, -1, 0, 0, 0, 0]
dy = [0, 0, 1, -1, 0, 0]
dz = [0, 0, 0, 0, 1, -1]


def bfs(i, j, k, time):
    global ans
    if i == ePos[0] and j == ePos[1] and k == ePos[2]:
        if ans > time:
            ans = time
        return

    lst[i][j][k] = time

    for X, Y, Z in zip(dx, dy, dz):
        nx, ny, nz = i+X, j+Y, k+Z
        if (nx < 0 or nx >= L) or (ny < 0 or ny >= R) or (nz < 0 or nz >= C):
            continue
        if lst[nx][ny][nz] != '.' and lst[nx][ny][nz] != 'E':
            continue
        bfs(nx, ny, nz, time+1)


while True:
    L, R, C = map(int, s().split())
    if L == 0 and R == 0 and C == 0:
        break
    lst = []
    sPos: tuple
    ePos: tuple

    ans = sys.maxsize

    for i in range(L):
        tmp = []
        for j in range(R):
            l = list(s().strip())
            tmp2 = []
            for idx, k in enumerate(l):
                if k == 'S':
                    sPos = (i, j, idx)
                if k == 'E':
                    ePos = (i, j, idx)
                tmp2.append(k)
            tmp.append(tmp2)
        s()
        lst.append(tmp)

    bfs(sPos[0], sPos[1], sPos[2], 0)
    print([f"Escaped in {ans} minute(s).", "Trapped!"][ans == sys.maxsize])
