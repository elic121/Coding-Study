# https://www.acmicpc.net/problem/17070
from sys import stdin

s = stdin.readline
N = int(s())
arr = []
for _ in range(N):
    arr.append(list(map(int, s().split())))

cnt = 0
arr[0][0] = 1


def dfs(x, y, d):
    global cnt

    arr[x][y] = 1

    if x == N - 1 and y == N - 1:
        cnt += 1
        return

    if d == 0:
        if (0 <= x < N) and (0 <= y + 1 < N) and arr[x][y + 1] != 1:
            dfs(x, y + 1, 0)
            arr[x][y + 1] = 0
        if (
            (0 <= x + 1 < N)
            and (0 <= y + 1 < N)
            and arr[x + 1][y + 1] != 1
            and arr[x][y + 1] != 1
            and arr[x + 1][y] != 1
        ):
            dfs(x + 1, y + 1, 2)
            arr[x + 1][y + 1] = 0

    if d == 1:
        if (0 <= x + 1 < N) and (0 <= y < N) and arr[x + 1][y] != 1:
            dfs(x + 1, y, 1)
            arr[x + 1][y] = 0
        if (
            (0 <= x + 1 < N)
            and (0 <= y + 1 < N)
            and arr[x + 1][y + 1] != 1
            and arr[x][y + 1] != 1
            and arr[x + 1][y] != 1
        ):
            dfs(x + 1, y + 1, 2)
            arr[x + 1][y + 1] = 0

    if d == 2:
        if (
            (0 <= x + 1 < N)
            and (0 <= y + 1 < N)
            and arr[x + 1][y + 1] != 1
            and arr[x][y + 1] != 1
            and arr[x + 1][y] != 1
        ):
            dfs(x + 1, y + 1, 2)
            arr[x + 1][y + 1] = 0
        if (0 <= x < N) and (0 <= y + 1 < N) and arr[x][y + 1] != 1:
            dfs(x, y + 1, 0)
            arr[x][y + 1] = 0
        if (0 <= x + 1 < N) and (0 <= y < N) and arr[x + 1][y] != 1:
            dfs(x + 1, y, 1)
            arr[x + 1][y] = 0

    return


dfs(0, 1, 0)
print(cnt)
