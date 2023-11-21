# https://www.acmicpc.net/problem/15683
from sys import stdin

s = stdin.readline
cctv = {}

MIN = 100_000_000_9
N, M = map(int, s().split())
arr = []
cctv = {}
wall = [[0 for _ in range(M)] for _ in range(N)]

# x,y = y, -x
d = {
    1: [(0, 1)],
    2: [(0, 1), (0, -1)],
    3: [(-1, 0), (0, 1)],
    4: [(0, -1), (-1, 0), (0, 1)],
    5: [(0, -1), (-1, 0), (0, 1), (1, 0)],
}

C = [[0 for _ in range(M)] for _ in range(N)]
cctv_cnt = 0
for i in range(N):
    lst = list(map(int, s().split()))
    for idx, j in enumerate(lst):
        if j != 0:
            if j == 6:
                wall[i][idx] = 1
            else:
                cctv[(i, idx)] = j
                C[i][idx] = 1
                cctv_cnt += 1
    arr.append(lst)


def check(lst):
    global wall
    cnt = 0
    for i in range(N):
        for j in range(M):
            if C[i][j] == 1:
                continue
            if wall[i][j] == 1:
                continue
            if lst[i][j] == "#":
                continue
            if lst[i][j] == 0:
                cnt += 1

    return cnt


def CCTV(lst):
    global cctv, N, M, wall, MIN
    L = [[0 for _ in range(M)] for _ in range(N)]
    idx = 0
    for key, val in cctv.items():
        D = lst[idx]
        idx += 1
        x, y = key
        L[x][y] = val
        for X, Y in d[val]:
            tx, ty = X, Y
            for _ in range(D):
                if val == 5:
                    break
                tx, ty = ty, -tx
            for i in range(1, max(N, M)):
                nx, ny = x + tx * i, y + ty * i
                if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                    break
                if wall[nx][ny] == 1:
                    break
                if L[nx][ny] != "#" and L[nx][ny] >= 1:
                    continue
                L[nx][ny] = "#"

    res = check(L)
    if res < MIN:
        MIN = res
        ARR = L

    return


def backtracking(tmp):
    global cctv_cnt
    if len(tmp) == cctv_cnt:
        tmp2 = [i for i in tmp]
        CCTV(tmp2)
        return

    for i in range(4):
        tmp.append(i)
        backtracking(tmp)
        tmp.pop()


backtracking([])
print(MIN)
