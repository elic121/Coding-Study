# https://www.acmicpc.net/problem/11722
from sys import stdin

s = stdin.readline

N = int(s())
arr = list(map(int, s().split()))
L = [1 for _ in range(N)]
for i in range(N):
    for j in range(i):
        if arr[j] > arr[i]:
            L[i] = max(L[i], L[j] + 1)

print(max(L))
