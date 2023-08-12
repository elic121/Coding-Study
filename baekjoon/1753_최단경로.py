# https://www.acmicpc.net/problem/1753
from sys import stdin
from collections import defaultdict
import heapq

s = stdin.readline
V, E = map(int,s().split())
d = defaultdict(list)
start = int(s())
for _ in range(E):
    u,v,w = map(int,s().split())
    d[u].append((w,v))

L = [float('inf') for _ in range(V+1)]

def D(start):
    Q = []
    heapq.heappush(Q,(0,start))
    L[start] = 0
    while Q:
        dist, node = heapq.heappop(Q)
        if L[node] < dist:
            continue
        for val in d[node]:
            cost = L[node] + val[0]

            if cost < L[val[1]]:
                L[val[1]] = cost
                heapq.heappush(Q,(cost,val[1]))

D(start)
for i in L[1:]:
    print(['INF',i][i!=float('inf')])