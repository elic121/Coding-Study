# https://www.acmicpc.net/problem/1520
from sys import stdin
from collections import deque

s = stdin.readline

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]


def condition(x, y):
    if (x < 0 or x >= N) or (y < 0 or y >= M):
        return False
    return True


def dp(i, j):
    if i == N - 1 and j == M - 1:
        return 1
    if visited[i][j] != -1:
        return visited[i][j]

    visited[i][j] = 0
    for X, Y in zip(dx, dy):
        nx, ny = i + X, j + Y
        if not condition(nx, ny):
            continue
        if arr[nx][ny] < arr[i][j]:
            visited[i][j] += dp(nx, ny)

    return visited[i][j]


if __name__ == "__main__":
    N, M = map(int, s().split())
    arr = []
    for _ in range(N):
        lst = list(map(int, s().split()))
        arr.append(lst)
    visited = [[-1 for _ in range(M)] for _ in range(N)]
    print(dp(0, 0))
