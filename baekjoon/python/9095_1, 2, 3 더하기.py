# https://www.acmicpc.net/problem/9095
from sys import stdin

s = stdin.readline
N = int(s())
for _ in range(N):
    n = int(s())
    l = [0 for _ in range(max(4, n + 1))]
    l[1] = 1
    l[2] = 2
    l[3] = 4
    for i in range(4, n + 1):
        l[i] = l[i - 3] + l[i - 2] + l[i - 1]
    print(l[n])
