import sys
from collections import defaultdict

N = 3
d = defaultdict(list)
for _ in range(N):
    a, b = map(int, sys.stdin.readline().split())
    d[a].append(b)
visited = [0 for _ in range(20)]
a = 0


def dfs(depth, start_node):
    print(start_node)
    if visited[start_node] == 1:
        return
    global a
    a = max(depth, a)
    visited[start_node] = 1
    for i in d[start_node]:
        dfs(depth + 1, i)


dfs(0, 1)
print(a)
