# https://www.acmicpc.net/problem/6064
from sys import stdin

s = stdin.readline
n = int(s())


def ans(M, N, x, y):
    k = x
    while k <= N * M:
        if (k - x) % M == 0 and (k - y) % N == 0:
            print(k)
            return
        k += M
    print(-1)


for _ in range(n):
    M, N, x, y = map(int, s().split())
    ans(M, N, x, y)
