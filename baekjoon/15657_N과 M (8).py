# https://www.acmicpc.net/problem/15657
from sys import stdin

s = stdin.readline
N, M = map(int, s().split())
arr = sorted(list(map(int, s().split())))
tmp = []


def p(idx):
    if len(tmp) == M:
        print(*tmp)
        return

    for i in range(idx, N):
        tmp.append(arr[i])
        p(i)
        tmp.pop()


p(0)
