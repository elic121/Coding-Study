# https://www.acmicpc.net/problem/17142
from sys import stdin

s = stdin.readline

N, M = map(int, s().split())
arr = []
virus = []
C = N * N
for i in range(N):
    lst = list(map(int, s().split()))
    for j, val in enumerate(lst):
        if val == 2:
            C -= 1
            virus.append((i, j))
            lst[j] = -2
        if val == 1:
            C -= 1
            lst[j] = -1
    arr.append(lst)

L = len(virus)

T = 100_000_000_9


def backtracking(n, tmp):
    global M
    if len(tmp) == M:
        tmp2 = [i for i in tmp]
        bfs(tmp2)

    for i in range(n, L):
        tmp.append(i)
        backtracking(i + 1, tmp)
        tmp.pop()


def bfs(lst):
    global T, C, arr, virus

    tmp = [[0 for _ in range(N)] for _ in range(N)]
    LST = []

    for idx in lst:
        x, y = virus[idx]
        tmp[x][y] = 1
        LST.append((x, y))

    while True:
        next_lst = []
        for x, y in LST:
            for _x, _y in zip([1, 0, -1, 0], [0, 1, 0, -1]):
                nx, ny = x + _x, y + _y
                if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                    continue
                if arr[nx][ny] == -1:
                    continue
                if tmp[nx][ny] >= 1:
                    continue
                tmp[nx][ny] = tmp[x][y] + 1
                next_lst.append((nx, ny))

        if next_lst:
            LST = [i for i in next_lst]
        else:
            break

    m = 0
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 0:
                if tmp[i][j] <= 0:
                    return
                else:
                    m = max(m, tmp[i][j] - 1)

    T = min(T, m)


backtracking(0, [])
print([-1, T][T != 100_000_000_9])
