# https://www.acmicpc.net/problem/2644
import sys
from collections import defaultdict

s = sys.stdin.readline
N = int(s())
X, Y = map(int, s().split())
d = defaultdict(list)
for _ in range(int(s())):
    a, b = map(int, s().split())
    d[a].append(b)
    d[b].append(a)

visited = [0 for _ in range(N + 1)]

dep = sys.maxsize


def dfs(sNode, depth, dNode):
    global dep
    if sNode == dNode:
        if dep > depth:
            dep = depth

    if visited[sNode] == 1:
        return

    visited[sNode] = 1

    for idx in d[sNode]:
        dfs(idx, depth + 1, dNode)


dfs(X, 0, Y)
if dep == sys.maxsize:
    print(-1)
else:
    print(dep)
