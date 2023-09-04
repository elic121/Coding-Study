# https://www.acmicpc.net/problem/16928
from sys import stdin
from collections import deque

s = stdin.readline
N, M = map(int, s().split())
dice = [i for i in range(1, 7)]
move = [0 for _ in range(101)]
visited = [False for _ in range(101)]

n = {}
for _ in range(N):
    u, v = map(int, s().split())
    n[u] = v

m = {}
for _ in range(M):
    u, v = map(int, s().split())
    m[u] = v


def bfs():
    q = deque()
    q.append(1)

    while q:
        x = q.popleft()

        if x == 100:
            print(move[x])
            break

        for i in dice:
            nx = x + i
            # 범위를 넘어가는 경우
            if nx >= 101:
                continue
            # 이미 값을 가지고 있는 경우
            if visited[nx] != 0:
                continue
            if nx in n.keys():
                nx = n[nx]
            if nx in m.keys():
                nx = m[nx]
            if visited[nx] != 0:
                continue

            visited[nx] = True
            move[nx] = move[x] + 1
            q.append(nx)


bfs()
