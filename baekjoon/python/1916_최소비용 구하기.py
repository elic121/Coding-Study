# https://www.acmicpc.net/problem/1916
from sys import stdin
from collections import defaultdict
import heapq

s = stdin.readline
N = int(s())
M = int(s())
d = defaultdict(list)
for _ in range(M):
    i, j, c = map(int, s().split())
    d[i].append((c, j))

start, end = map(int, s().split())

H = []
L = [float("inf") for _ in range(N + 1)]
L[start] = 0
heapq.heappush(H, (0, start))
while H:
    dist, node = heapq.heappop(H)
    if dist > L[node]:
        continue
    for cost, n in d[node]:
        if L[node] + cost < L[n]:
            L[n] = L[node] + cost
            heapq.heappush(H, (L[node] + cost, n))

print(L[end])
