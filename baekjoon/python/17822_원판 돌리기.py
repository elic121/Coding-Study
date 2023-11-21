# https://www.acmicpc.net/problem/17822
from sys import stdin
from collections import deque

s = stdin.readline
arr = []
N, M, T = map(int, s().split())
for _ in range(N):
    lst = list(map(int, s().split()))
    arr.append(deque(lst))


def rotate(x, d, k):
    global arr
    for idx in range(N):
        if (idx + 1) % x == 0:
            if d == 0:
                arr[idx].rotate(k)
            else:
                arr[idx].rotate(-k)


def checkSameLevel(i, j):
    global arr
    tmp = []
    for c in (1, -1):
        idx = (j + c) % M
        if arr[i][idx] == arr[i][j]:
            tmp.append((i, idx))
    return tmp


def checkPrevNextLevel(i, j):
    global arr
    tmp = []
    for k in range(i - 1, 0, -1):
        if arr[k][j] == arr[i][j]:
            tmp.append((k, j))
        else:
            break
    for k in range(i + 1, N):
        if arr[k][j] == arr[i][j]:
            tmp.append((k, j))
        else:
            break

    return tmp


def check(i, j):
    global arr
    c1, c2 = [], []
    c1 = checkSameLevel(i, j)
    c2 = checkPrevNextLevel(i, j)
    return c1 + c2


def average():
    global arr
    C = 0
    cnt = 0
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                continue
            C += arr[i][j]
            cnt += 1
    try:
        C /= cnt

        for i in range(N):
            for j in range(M):
                if arr[i][j] == 0:
                    continue
                if arr[i][j] > C:
                    arr[i][j] -= 1
                elif arr[i][j] < C:
                    arr[i][j] += 1
                else:
                    pass
    except:
        pass


for _ in range(T):
    tmp = [[0 for _ in range(M)] for _ in range(N)]

    x, d, k = map(int, s().split())
    rotate(x, d, k)

    D = False
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                continue
            ch = check(i, j)
            if ch:
                D = True
                tmp[i][j] = 1
                for x, y in ch:
                    tmp[x][y] = 1

    if D:
        for i in range(N):
            for j in range(M):
                if tmp[i][j] == 1:
                    arr[i][j] = 0

    else:
        average()

C = 0
for i in range(N):
    for j in range(M):
        C += arr[i][j]
print(C)
