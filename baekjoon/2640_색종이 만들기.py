# https://www.acmicpc.net/problem/2630
from sys import stdin

s = stdin.readline
N = int(s())
arr = [list(map(int, s().split())) for _ in range(N)]
B, W = 0, 0


def cnt(i, j, N):
    global B, W
    tmp = 0
    for r in range(i, i + N):
        for c in range(j, j + N):
            if arr[r][c]:
                tmp += 1
    if tmp == N**2:
        B += 1
    elif tmp == 0:
        W += 1
    else:
        new = N // 2
        cnt(i, j, new)
        cnt(i + new, j, new)
        cnt(i, j + new, new)
        cnt(i + new, j + new, new)


cnt(0, 0, N)
print(W)
print(B)
