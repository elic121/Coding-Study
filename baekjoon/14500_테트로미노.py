# https://www.acmicpc.net/problem/14500
from sys import stdin

s = stdin.readline
N, M = map(int, s().split())
arr = [list(map(int, s().split())) for _ in range(N)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def tuple_sum(t):
    tmp = 0
    for i, j in t:
        tmp += arr[i][j]
    return tmp


MAX = 0


def dfs(x, y, L=list[tuple]):
    global MAX
    if len(L) == 4:
        MAX = max(MAX, tuple_sum(L))
        return

    for i, j in zip(dx, dy):
        nx, ny = x + i, y + j
        if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
            continue
        if (nx, ny) in L:
            continue
        L.append((nx, ny))
        dfs(nx, ny, L)
        L.pop()


def fuckyou():
    global MAX

    pos = [1, 2]
    Rsub = [(-1, 1), (1, 1)]
    Csub = [(1, -1), (1, 1)]

    # row
    for i in range(N):
        for j in range(M - 2):
            tmp = [arr[i][j]]
            for q in range(2):
                tmp.append(arr[i][j + pos[q]])
            for k in range(2):
                nx, ny = i + Rsub[k][0], j + Rsub[k][1]
                if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                    continue
                tmp.append(arr[nx][ny])
                if len(tmp) == 4:
                    MAX = max(MAX, sum(tmp))
                    tmp.pop()

    # column
    for i in range(N - 2):
        for j in range(M):
            tmp = [arr[i][j]]
            for q in range(2):
                tmp.append(arr[i + pos[q]][j])
            for k in range(2):
                nx, ny = i + Csub[k][0], j + Csub[k][1]
                if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                    continue
                tmp.append(arr[nx][ny])
                if len(tmp) == 4:
                    MAX = max(MAX, sum(tmp))
                    tmp.pop()


fuckyou()
for i in range(N):
    for j in range(M):
        dfs(i, j, [(i, j)])

print(MAX)
