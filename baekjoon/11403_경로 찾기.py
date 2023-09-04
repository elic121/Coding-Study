# https://www.acmicpc.net/problem/11403
from sys import stdin

s = stdin.readline
N = int(s())
arr = [list(map(int, s().split())) for i in range(N)]


def fw():
    for k in range(N):
        for i in range(N):
            for j in range(N):
                if arr[i][k] and arr[k][j]:
                    arr[i][j] = 1


fw()
for i in arr:
    print(*i)
