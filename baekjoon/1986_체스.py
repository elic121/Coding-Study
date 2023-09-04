# https://www.acmicpc.net/problem/1986
from sys import stdin

s = stdin.readline
N, M = map(int, s().split())
arr = [[0 for _ in range(M + 1)] for _ in range(N + 1)]

n, *info = map(int, s().split())
for i in range(n):
    x, y = info[i * 2], info[i * 2 + 1]
    arr[x][y] = "Q"
n, *info = map(int, s().split())
for i in range(n):
    x, y = info[i * 2], info[i * 2 + 1]
    arr[x][y] = "K"
n, *info = map(int, s().split())
for i in range(n):
    x, y = info[i * 2], info[i * 2 + 1]
    arr[x][y] = "P"


def Q(i, j):
    for c in range(j - 1, 0, -1):
        if arr[i][c] == "P":
            break
        if arr[i][c] == "Q":
            break
        if arr[i][c] == "K":
            break
        if arr[i][c] == "D":
            continue
        arr[i][c] = "D"

    for c in range(j + 1, M + 1):
        if arr[i][c] == "P":
            break
        if arr[i][c] == "Q":
            break
        if arr[i][c] == "K":
            break
        if arr[i][c] == "D":
            continue
        arr[i][c] = "D"

    for r in range(i - 1, 0, -1):
        if arr[r][j] == "P":
            break
        if arr[r][j] == "Q":
            break
        if arr[r][j] == "K":
            break
        if arr[r][j] == "D":
            continue
        arr[r][j] = "D"

    for r in range(i + 1, N + 1):
        if arr[r][j] == "P":
            break
        if arr[r][j] == "Q":
            break
        if arr[r][j] == "K":
            break
        if arr[r][j] == "D":
            continue
        arr[r][j] = "D"

    for x in range(1, N + 1):
        q, w = i + x, j - x
        if q > N or w < 1:
            break
        if arr[q][w] == "P":
            break
        if arr[q][w] == "K":
            break
        if arr[q][w] == "Q":
            break
        if arr[q][w] == "D":
            continue
        arr[q][w] = "D"

    for x in range(1, N + 1):
        q, w = i - x, j + x
        if q < 1 or w > M:
            break
        if arr[q][w] == "P":
            break
        if arr[q][w] == "K":
            break
        if arr[q][w] == "Q":
            break
        if arr[q][w] == "D":
            continue
        arr[q][w] = "D"

    for x in range(1, N + 1):
        q, w = i - x, j - x
        if q < 1 or w < 1:
            break
        if arr[q][w] == "P":
            break
        if arr[q][w] == "K":
            break
        if arr[q][w] == "Q":
            break
        if arr[q][w] == "D":
            continue
        arr[q][w] = "D"

    for x in range(1, N + 1):
        q, w = i + x, j + x
        if q > N or w > M:
            break
        if arr[q][w] == "P":
            break
        if arr[q][w] == "K":
            break
        if arr[q][w] == "Q":
            break
        if arr[q][w] == "D":
            continue
        arr[q][w] = "D"

    return


def K(i, j):
    pos = [(1, 2), (-1, 2), (-1, -2), (1, -2), (2, 1), (-2, 1), (2, -1), (-2, -1)]
    for x, y in pos:
        nx, ny = i + x, j + y
        if (nx < 1 or nx > N) or (ny < 1 or ny > M):
            continue
        if arr[nx][ny] == "Q":
            continue
        if arr[nx][ny] == "D":
            continue
        if arr[nx][ny] == "P":
            continue
        if arr[nx][ny] == "K":
            continue
        arr[nx][ny] = "D"

    return


def solution():
    for i in range(1, N + 1):
        for j in range(1, M + 1):
            if arr[i][j] == 0:
                continue
            if arr[i][j] == "D":
                continue
            if arr[i][j] == "P":
                continue
            if arr[i][j] == "Q":
                Q(i, j)
            if arr[i][j] == "K":
                K(i, j)

    cnt = 0
    for i in range(1, N + 1):
        for j in range(1, M + 1):
            if arr[i][j] == 0:
                cnt += 1
    return cnt


print(solution())
