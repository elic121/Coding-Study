# https://www.acmicpc.net/problem/20057
from sys import stdin

s = stdin.readline

N = int(s())
arr = []
for _ in range(N):
    arr.append(list(map(int, s().split())))

sx, sy = N // 2, N // 2
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]

to = [
    {
        (-1, 0): 0.01,
        (-1, -1): 0.07,
        (-2, -1): 0.02,
        (-1, -2): 0.1,
        (0, -3): 0.05,
        (1, 0): 0.01,
        (1, -1): 0.07,
        (2, -1): 0.02,
        (1, -2): 0.1,
    },
    {
        (0, -1): 0.01,
        (1, -1): 0.07,
        (1, -2): 0.02,
        (2, 1): 0.1,
        (3, 0): 0.05,
        (0, 1): 0.01,
        (1, 1): 0.07,
        (1, 2): 0.02,
        (2, -1): 0.1,
    },
    {
        (1, 0): 0.01,
        (1, 1): 0.07,
        (2, 1): 0.02,
        (1, 2): 0.1,
        (0, 3): 0.05,
        (-1, 0): 0.01,
        (-1, 1): 0.07,
        (-2, 1): 0.02,
        (-1, 2): 0.1,
    },
    {
        (0, 1): 0.01,
        (-1, 1): 0.07,
        (-1, 2): 0.02,
        (-2, -1): 0.1,
        (-3, 0): 0.05,
        (0, -1): 0.01,
        (-1, -1): 0.07,
        (-1, -2): 0.02,
        (-2, 1): 0.1,
    },
]

OUT = 0
DIR = 0
CNT = 0
MOVE = 0

while True:
    MOVE = 1 + (CNT // 2)
    DX, DY = dx[DIR], dy[DIR]

    for i in range(MOVE):
        X, Y = sx + DX * i, sy + DY * i

        MS = arr[X + DX][Y + DY]
        R = 0
        for key, val in to[DIR].items():
            x, y = key
            nx, ny = X + x, Y + y
            res = int(val * MS)

            R += res
            if (0 <= nx < N) and (0 <= ny < N):
                arr[nx][ny] += res
            else:
                OUT += res

        arr[X + DX][Y + DY] = 0

        mod = MS - R
        if (0 <= X + DX * 2 < N) and (0 <= Y + DY * 2 < N):
            arr[X + DX * 2][Y + DY * 2] += mod
        else:
            OUT += mod

        if X == 0 and Y == 1:
            break

    if X == 0 and Y == 1:
        print(OUT)
        break

    sx, sy = sx + MOVE * DX, sy + MOVE * DY
    CNT += 1
    DIR = (DIR + 1) % 4
