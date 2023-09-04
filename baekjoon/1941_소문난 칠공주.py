# https://www.acmicpc.net/problem/1941
from sys import stdin
from collections import deque

s = stdin.readline
arr = [list(s().strip()) for _ in range(5)]

cnt = 0


def combination(n, tmp: list):
    global cnt
    if len(tmp) == 7:
        if check(tmp):
            cnt += 1
        return

    for i in range(n, 25):
        tmp.append(i)
        combination(i + 1, tmp)
        tmp.pop()

    return


def check(tmp: list):
    lst = [[0 for _ in range(5)] for _ in range(5)]
    y, s = 0, 0
    for val in tmp:
        i, j = val // 5, val % 5
        lst[i][j] = 1
        if arr[i][j] == "Y":
            y += 1
        else:
            s += 1

    if y >= 4:
        return False

    result = bfs(lst, i, j)

    return True if result == 7 else False


dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def bfs(lst, i, j):
    d = deque()
    d.append((i, j))
    c = 0
    while d:
        x, y = d.popleft()

        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if (nx < 0 or nx >= 5) or (ny < 0 or ny >= 5):
                continue
            if lst[nx][ny] == 0:
                continue
            lst[nx][ny] = 0
            d.append((nx, ny))
            c += 1

    return c


combination(0, [])
print(cnt)
