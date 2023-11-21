# https://www.acmicpc.net/problem/15486
from sys import stdin

s = stdin.readline
N = int(s())
arr = []
for i in range(N):
    t, p = map(int, s().split())
    arr.append((t, p))
dp = [0 for _ in range(N + 1)]

M = 0
for i in range(N):
    M = max(M, dp[i])
    if i + arr[i][0] <= N:
        dp[i + arr[i][0]] = max(dp[i + arr[i][0]], M + arr[i][1])

print(max(dp))
