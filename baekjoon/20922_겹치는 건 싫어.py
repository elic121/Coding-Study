# https://www.acmicpc.net/problem/20922
from sys import stdin
s = stdin.readline
N, K = map(int, s().split())
arr = list(map(int, s().split()))
cnt = [0 for _ in range(100_000+1)]
stt, end = 0, 0
L = 0

while end < N:
    if cnt[arr[end]] < K:
        cnt[arr[end]] += 1
        end += 1

    else:
        cnt[arr[stt]] -= 1
        stt += 1
    L = max(L, end-stt)
print(L)
