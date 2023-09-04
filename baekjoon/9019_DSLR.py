# https://www.acmicpc.net/problem/9019
import sys
from collections import deque, defaultdict


def command(n, l):
    if l == "D":
        n = (2 * n) % 10000
        return n
    if l == "S":
        n -= 1
        if n == -1:
            n = 9999
        return n
    if l == "L":
        return (10 * n + (n // 1000)) % 10000
    if l == "R":
        return (n % 10) * 1000 + (n // 10)


def bfs(s, e):
    # history = ['' for _ in range(10**4)]
    history = defaultdict(list)
    d = deque()
    d.append(s)

    while d:
        x = d.popleft()

        for com in list("DLRS"):
            res = command(int(x), com)
            if res == s:
                continue

            if history[res]:
                continue

            history[res].extend(history[x])
            history[res].append(com)

            if res == e:
                return history[res]

            d.append(res)


N = int(sys.stdin.readline())
for _ in range(N):
    S, E = map(int, sys.stdin.readline().split())
    print("".join(bfs(S, E)))
