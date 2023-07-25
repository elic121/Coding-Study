# https://www.acmicpc.net/problem/5014
import sys
from collections import deque
s = sys.stdin.readline
F, S, G, U, D = map(int,s().split())
d= deque()
d.append(S)
visited = [None for _ in range(F+1)]
visited[S] = 0

def bfs():
    while d:
        s = d.popleft()
        for i in [U,-D]:
            nx = s+i
            if nx<1 or nx>F:
                continue
            if visited[nx] != None:
                continue

            visited[nx] = visited[s] + 1
            d.append(nx)

if S==G:
    print(0)
else:
    bfs()
    ans = visited[G]
    print(["use the stairs",ans][ans!=None])

