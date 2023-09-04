# https://www.acmicpc.net/problem/2075
from sys import stdin
import heapq

s = stdin.readline
N = int(s())
l = []
for _ in range(N):
    arr = list(map(int, s().split()))
    for i in arr:
        if len(l) < N:
            heapq.heappush(l, i)
        else:
            if i > l[0]:
                heapq.heappop(l)
                heapq.heappush(l, i)

print(l[0])
