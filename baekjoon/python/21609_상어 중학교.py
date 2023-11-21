# https://www.acmicpc.net/problem/21609
from sys import stdin
from collections import deque

s = stdin.readline
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
N, M = map(int, s().split())

arr = []
for _ in range(N):
    l = list(map(int, s().split()))
    arr.append(l)


def findBlock():
    global arr
    MAX = {}
    tmp = [[0 for _ in range(N)] for _ in range(N)]

    for i in range(N):
        for j in range(N):
            if arr[i][j] == -1:
                continue
            if arr[i][j] == None:
                continue
            if arr[i][j] == 0:
                continue

            d = deque()
            d.append((i, j))
            key = arr[i][j]
            tmp2 = [(i, j)]
            visited = [[0 for _ in range(N)] for _ in range(N)]
            visited[i][j] = 1

            while d:
                x, y = d.popleft()
                for X, Y in zip(dx, dy):
                    nx, ny = x + X, y + Y
                    if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                        continue
                    if tmp[nx][ny] == 1:
                        continue
                    if visited[nx][ny] == 1:
                        continue
                    if arr[nx][ny] == None:
                        continue
                    if arr[nx][ny] == -1:
                        continue
                    if not (arr[nx][ny] == 0 or arr[nx][ny] == key):
                        continue
                    d.append((nx, ny))
                    tmp2.append((nx, ny))
                    visited[nx][ny] = 1

            if len(tmp2) < 2:
                continue

            for X, Y in tmp2:
                if arr[X][Y] != 0:
                    tmp[X][Y] = 1

            if not len(tmp2) in MAX:
                MAX[len(tmp2)] = [tmp2]
            else:
                MAX[len(tmp2)].append(tmp2)
    try:
        R = MAX[max(MAX.keys())]
        return R
    except:
        return None


def autoplay(lst: list):
    rainbow = 0
    MAXIDX = {}
    for idx, L in enumerate(lst):
        tmpcnt = 0
        for i, j in L:
            if arr[i][j] == 0:
                tmpcnt += 1
        if rainbow <= tmpcnt:
            rainbow = tmpcnt

            if tmpcnt not in MAXIDX:
                MAXIDX[tmpcnt] = [idx]
            else:
                MAXIDX[tmpcnt].append(idx)
    MK = max(MAXIDX.keys())
    # rainbow 개수가 가장 많은 블럭들의 리스트 인덱스
    res2 = MAXIDX[MK]
    if len(res2) == 1:
        return lst[res2[0]]

    pivot = (-1, -1)
    sIDX = N
    for I in res2:
        for a, b in sorted(lst[I], key=lambda x: (x[0], x[1])):
            if arr[a][b] != 0:
                X, Y = a, b
                break
        if pivot[0] < X:
            pivot = (X, Y)
            sIDX = I
        elif pivot[0] == X:
            if pivot[1] < Y:
                pivot = (X, Y)
                sIDX = I

    return lst[sIDX]


def gravity():
    global arr

    for i in range(N - 2, -1, -1):
        for j in range(N):
            if arr[i][j] == -1:
                continue
            if arr[i][j] == None:
                continue
            idx = i
            for k in range(i + 1, N):
                if arr[k][j] == -1 or arr[k][j] != None:
                    break
                idx = k
            if idx == i:
                continue
            arr[idx][j] = arr[i][j]
            arr[i][j] = None


def rotateCounterclockwise():
    global arr
    tmp = [[0] * N for _ in range(N)]
    for j in range(N - 1, -1, -1):
        for i in range(N):
            key = arr[i][j]
            tmp[N - 1 - j][i] = key
    arr = tmp


SCORE = 0
while True:
    R = findBlock()
    if R == None:
        break

    if len(R) == 1:
        delete = R[0]
    else:
        delete = autoplay(R)

    cnt = 0
    for x, y in delete:
        cnt += 1
        arr[x][y] = None
    tmpScore = cnt * cnt
    SCORE += tmpScore

    gravity()
    rotateCounterclockwise()
    gravity()

print(SCORE)
