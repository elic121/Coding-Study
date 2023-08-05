# https://www.acmicpc.net/problem/13549
import sys
from collections import deque
s = sys.stdin.readline
N, K = map(int, s().split())
d = deque()
d.append(N)
l = [-1 for _ in range(10**5+1)]
l[N] = 0

while d:
    p = d.popleft()

    if p == K:
        print(l[p])
        break

    for idx, next_pos in enumerate([p-1, 2*p, p+1]):

        if (next_pos < 0) or next_pos >= (10**5+1):
            continue

        if l[next_pos] != -1:
            continue

        if idx != 1:

            l[next_pos] = l[p] + 1
            d.append(next_pos)

        else:

            l[next_pos] = l[p]
            d.appendleft(next_pos)
