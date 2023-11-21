# https://www.acmicpc.net/problem/19238
from sys import stdin
from collections import deque
from copy import deepcopy

s = stdin.readline
N, M, F = map(int, s().split())
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

arr = []
for _ in range(N):
    lst = list(map(int, s().split()))
    arr.append(lst)

posx, posy = map(int, s().split())
posx -= 1
posy -= 1
SD = {}
for _ in range(M):
    x1, y1, x2, y2 = map(int, s().split())
    SD[(x1 - 1, y1 - 1)] = (x2 - 1, y2 - 1)


def bfs_stt(i, j):
    tmp = deepcopy(arr)
    d = deque()
    d.append((i, j))
    result = {}

    for key in SD.keys():
        x1, y1 = key
        tmp[x1][y1] = -1

    if tmp[i][j] == -1:
        return 0, (i, j)

    tmp[i][j] = 0

    while d:
        X, Y = d.popleft()
        for vx, vy in zip(dx, dy):
            nx, ny = X + vx, Y + vy
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                continue
            if tmp[nx][ny] > 0:
                continue
            if tmp[nx][ny] == -1:
                r = tmp[X][Y] + 1
                if r in result:
                    result[r].append((nx, ny))
                else:
                    result[r] = [(nx, ny)]

            tmp[nx][ny] = tmp[X][Y] + 1
            d.append((nx, ny))
    try:
        M = min(result.keys())
        R = sorted(result[M], key=lambda x: (x[0], x[1]))
        return M, R[0]

    except:
        return None, None


def bfs_des(i, j, p, q):
    if i == p and j == q:
        return 0
    tmp = deepcopy(arr)
    d = deque()
    d.append((i, j))
    tmp[i][j] = 0
    while d:
        X, Y = d.popleft()
        if X == p and Y == q:
            return tmp[X][Y]
        for vx, vy in zip(dx, dy):
            nx, ny = X + vx, Y + vy
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                continue
            if tmp[nx][ny] > 0:
                continue

            tmp[nx][ny] = tmp[X][Y] + 1
            d.append((nx, ny))


SUCCESS = True
for _ in range(M):
    L, T = bfs_stt(posx, posy)
    if L == None or L > F:
        print(-1)
        SUCCESS = False
        break

    tx, ty = T
    gx, gy = SD[(tx, ty)]
    del SD[(tx, ty)]

    res = bfs_des(tx, ty, gx, gy)
    if res == None:
        print(-1)
        SUCCESS = False
        break

    FUEL = F - L - res

    if FUEL >= 0:
        F = FUEL + 2 * res
        posx, posy = gx, gy

    else:
        print(-1)
        SUCCESS = False
        break

if SUCCESS:
    print(F)
