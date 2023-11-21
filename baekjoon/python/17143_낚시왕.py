# https://www.acmicpc.net/problem/17143
from sys import stdin

s = stdin.readline
kx = -1
dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

R, C, M = map(int, s().split())
if M == 0:
    print(0)
    exit()

arr = [[0 for _ in range(C)] for _ in range(R)]
scale = 0

for _ in range(M):
    r, c, S, D, Z = map(int, s().split())
    arr[r - 1][c - 1] = (S, D, Z)


def catchShark():
    global kx, scale, arr
    stopRow = 0
    for i in range(R):
        if arr[i][kx]:
            stopRow = i
            break

    if stopRow == 0 and arr[0][kx] == 0:
        pass
    else:
        if arr[stopRow][kx]:
            shark = arr[stopRow][kx]
            scale += shark[-1]
            arr[stopRow][kx] = 0


GET = 0


def moveShark():
    global arr, kx, R, C, GET
    GET = 0
    tmp = [[0 for _ in range(C)] for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if not arr[i][j]:
                continue
            GET += 1
            S, d, z = arr[i][j]

            if d >= 3:
                stmp = S % (2 * (C - 1))
                dtmp = d - 1
                sy = j
                for _ in range(stmp):
                    ny = sy + dy[dtmp]
                    if ny < 0:
                        ny = 1
                        dtmp = 2
                    if ny > C - 1:
                        ny = C - 2
                        dtmp = 3
                    sy = ny

                if tmp[i][sy] and tmp[i][sy][-1] > z:
                    continue
                tmp[i][sy] = (S, dtmp + 1, z)

            else:
                stmp = S % (2 * (R - 1))
                dtmp = d - 1
                sx = i
                for _ in range(stmp):
                    nx = sx + dx[dtmp]
                    if nx < 0:
                        nx = 1
                        dtmp = 1
                    if nx > R - 1:
                        nx = R - 2
                        dtmp = 0
                    sx = nx

                if tmp[sx][j] and tmp[sx][j][-1] > z:
                    continue
                tmp[sx][j] = (S, dtmp + 1, z)

    arr = tmp


for _ in range(C):
    kx += 1
    catchShark()
    moveShark()
    if GET == 0:
        break

print(scale)
