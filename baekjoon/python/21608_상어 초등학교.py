# https://www.acmicpc.net/problem/21608
from sys import stdin

s = stdin.readline
N = int(s())
d = {}
for _ in range(N * N):
    a, *b = map(int, s().split())
    d[a] = list(b)

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
arr = [[0 for _ in range(N + 1)] for _ in range(N + 1)]


def first(n):
    global arr
    history = {}
    D = d[n]
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            if arr[i][j] != 0:
                continue
            cnt = 0
            for x, y in zip(dx, dy):
                nx, ny = i + x, j + y
                if (nx < 1 or nx >= N + 1) or (ny < 1 or ny >= N + 1):
                    continue
                if arr[nx][ny] in D:
                    cnt += 1
            if cnt in history:
                history[cnt].append((i, j))
            else:
                history[cnt] = [(i, j)]

    return history


def secondAndThird(lst):
    global arr
    MAX = -1
    for i, j in lst:
        cnt = 0
        for x, y in zip(dx, dy):
            nx, ny = i + x, j + y
            if (nx < 1 or nx >= N + 1) or (ny < 1 or ny >= N + 1):
                continue
            if arr[nx][ny] != 0:
                continue
            cnt += 1
        if cnt > MAX:
            MAX = cnt
            POS = (i, j)

    return POS


def sat():
    global arr
    score = 0
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            key = d[arr[i][j]]
            cnt = 0
            for x, y in zip(dx, dy):
                nx, ny = i + x, j + y
                if (nx < 1 or nx >= N + 1) or (ny < 1 or ny >= N + 1):
                    continue
                if arr[nx][ny] in key:
                    cnt += 1
            if cnt == 0:
                score += 0
            if cnt == 1:
                score += 1
            if cnt == 2:
                score += 10
            if cnt == 3:
                score += 100
            if cnt == 4:
                score += 1000
    return score


for key in d.keys():
    res1 = first(key)
    valcnt = 0
    for k in res1.keys():
        valcnt += len(res1[k])
    if valcnt > 1:
        res2 = secondAndThird(res1[max(res1.keys())])
        arr[res2[0]][res2[1]] = key
    else:
        i, j = res1[list(res1.keys())[0]][0]
        arr[i][j] = key

print(sat())
