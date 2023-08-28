# https://www.acmicpc.net/problem/15591
from sys import stdin
from collections import deque
from collections import defaultdict

s = stdin.readline
N, Q = map(int, s().split())
d = defaultdict(list)
for _ in range(N-1):
    p, q, r = map(int, s().split())
    d[p].append((q, r))
    d[q].append((p, r))


def solution(start_node, kvalue):

    visited = [0 for _ in range(N+1)]
    visited[start_node] = 1

    D = deque()
    D.append((start_node, 10**9+1))

    cnt = 0

    while D:
        key, dist = D.popleft()

        for next_node, q in d[key]:
            if visited[next_node] != 0:
                continue
            if q < kvalue:
                continue
            cnt += 1
            visited[next_node] = 1
            D.append((next_node, min(dist, q)))

    return cnt


for _ in range(Q):
    k, v = map(int, s().split())
    print(solution(v, k))
