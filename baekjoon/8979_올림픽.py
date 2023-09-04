# https://www.acmicpc.net/problem/8979
import sys

s = sys.stdin.readline
N, K = map(int, s().split())
lst = []
for _ in range(N):
    a, b, c, d = map(int, s().split())
    lst.append((a, b, c, d))
lst.sort(key=lambda x: (-x[1], -x[2], -x[3]))
idx = [lst[i][0] for i in range(N)].index(K)
for i in range(N):
    if lst[idx][1:] == lst[i][1:]:
        print(i + 1)
        break
