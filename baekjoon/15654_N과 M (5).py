# https://www.acmicpc.net/problem/15654
from sys import stdin
s = stdin.readline
N, M = map(int,s().split())
arr = list(map(int,s().split()))
arr.sort()

sel = [0 for _ in range(M)]
check = [0 for _ in range(N)]

def perm(idx):
    if idx == M:
        print(*sel)
    else:
        for i in range(N):
            if check[i] == 0:
                sel[idx] = arr[i]
                check[i] = 1
                perm(idx+1)
                check[i] = 0

perm(0)