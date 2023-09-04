# https://www.acmicpc.net/problem/10451
import sys

s = sys.stdin.readline
N = int(s())


def dfs(n):
    visited[n] = 1
    O = d[n]
    if visited[O] == 0:
        dfs(O)


for _ in range(N):
    n = int(s())
    d = {}
    l = list(map(int, s().split()))
    visited = [0 for _ in range(n + 1)]
    cnt = 0
    for i in range(1, n + 1):
        d[i] = l[i - 1]

    for i in range(1, n + 1):
        if visited[i] == 0:
            dfs(i)
            cnt += 1
    print(cnt)
