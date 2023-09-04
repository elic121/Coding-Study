# https://www.acmicpc.net/problem/1931
import sys

s = sys.stdin.readline
N = int(s())
arr = [tuple(map(int, s().split())) for _ in range(N)]
arr.sort(key=lambda x: (x[1], x[0]))

stt = -1
cnt = 0
for s, e in arr:
    if stt <= s:
        stt = e
        cnt += 1
print(cnt)
