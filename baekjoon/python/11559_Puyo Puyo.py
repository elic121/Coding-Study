# https://www.acmicpc.net/problem/11559
from sys import stdin
from collections import deque

s = stdin.readline

visited = [[0 for _ in range(6)] for _ in range(12)]


def init_visited():
    for i in range(12):
        for j in range(6):
            visited[i][j] = 0


def condition(x, y):
    if (x < 0 or x >= 12) or (y < 0 or y >= 6):
        return False
    return True


dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]


def bfs(i, j):
    d = deque()
    d.append((i, j))
    value = arr[i][j]

    tmp = [(i, j)]
    while d:
        x, y = d.popleft()
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if not condition(nx, ny):
                continue
            if arr[nx][ny] != value:
                continue
            if visited[nx][ny]:
                continue
            visited[nx][ny] = 1
            tmp.append((nx, ny))
            d.append((nx, ny))

    return [None, tmp][len(tmp) >= 4]


def delete(exp):
    for val in exp:
        for i, j in val:
            arr[i][j] = "."


def clean():
    for c in range(6):
        stack = []
        for r in range(11, -1, -1):
            if arr[r][c] != ".":
                stack.append(arr[r][c])
                arr[r][c] = "."
        for r in range(11, 11 - len(stack), -1):
            arr[r][c] = stack[11 - r]


def explore():
    EX = []
    init_visited()
    for i in range(12):
        for j in range(6):
            if visited[i][j]:
                continue
            if arr[i][j] == ".":
                continue
            visited[i][j] = 1
            ex = bfs(i, j)
            if ex:
                EX.append(ex)

    if not EX:
        return 0

    else:
        delete(EX)
        clean()
        return 1


if __name__ == "__main__":
    arr = [list(s().strip()) for _ in range(12)]
    cnt = 0
    while explore():
        cnt += 1
    print(cnt)
