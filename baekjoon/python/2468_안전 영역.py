# https://www.acmicpc.net/problem/2468
import sys
from copy import deepcopy

sys.setrecursionlimit(10**6)
s = sys.stdin.readline
N = int(s())
l = []
tmpMax = []

for _ in range(N):
    tmpList = list(map(int, s().strip().split()))
    tmpMax.append(max(tmpList))
    l.append(tmpList)

MAX = max(tmpList)
tmp = deepcopy(l)

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]


def dfs(x, y, c):
    xconst = x < 0 or x >= N
    yconst = y < 0 or y >= N
    if xconst or yconst:
        return False
    if l[x][y] > c:
        l[x][y] = c
        for X, Y in zip(dx, dy):
            nx = x + X
            ny = y + Y
            dfs(nx, ny, c)
        return True
    return False


cnt = []
t = 0
for k in range(MAX + 1):
    for i in range(N):
        for j in range(N):
            t += dfs(i, j, k)
    cnt.append(t)
    t = 0
    l = deepcopy(tmp)

print(max(cnt))
