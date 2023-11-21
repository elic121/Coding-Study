# https://www.acmicpc.net/problem/12100
from sys import stdin
from collections import deque

s = stdin.readline
N = int(s())
arr = []
for _ in range(N):
    arr.append(list(map(int, s().split())))
M = 0


def move(tmpLST, move):
    if move != 3:
        for _ in range(move + 1):
            tmpLST = rotate(tmpLST)
    tmp = []
    for i in range(N):
        d = deque()
        tmp2 = deque()
        lst = tmpLST[i]
        while lst:
            x = lst.pop()
            if x == 0:
                continue
            d.appendleft(x)
            if len(d) >= 2:
                if d[0] == d[1]:
                    T1 = d.popleft()
                    T2 = d.popleft()
                    val = T1 + T2
                    while d:
                        tmp2.appendleft(d.pop())
                    tmp2.appendleft(val)

        while d:
            tmp2.appendleft(d.pop())

        for _ in range(N - len(tmp2)):
            tmp2.appendleft(0)

        tmp.append(list(tmp2))

    for _ in range(N - 1 - move):
        tmp = rotate(tmp)

    return tmp


def rotate(L):
    tmp = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            tmp[i][j] = L[N - 1 - j][i]
    return tmp


def backtracking(LIST, n):
    global M
    res = calmax(LIST)
    if M < res:
        M = res
    if n == 5:
        return

    for i in range(4):
        tmp = move(LIST, i)
        backtracking(tmp, n + 1)


def calmax(arr):
    MAX = 0
    for i in range(N):
        for j in range(N):
            MAX = max(MAX, arr[i][j])
    return MAX


backtracking(arr, 0)
print(M)
