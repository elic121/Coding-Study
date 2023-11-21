# https://www.acmicpc.net/problem/11725

import sys
from collections import defaultdict

sys.setrecursionlimit(10**6)

s = sys.stdin.readline
N = int(s())
d = defaultdict(list)
visited = [0 for _ in range(N + 1)]

for _ in range(N - 1):
    a, b = map(int, s().split())
    d[a].append(b)
    d[b].append(a)

answer = defaultdict(int)


def dfs(start_node):
    for i in d[start_node]:
        if not visited[i]:
            visited[i] = 1
            answer[i] = start_node
            dfs(i)


dfs(1)

for i in range(2, N + 1):
    print(answer[i])
