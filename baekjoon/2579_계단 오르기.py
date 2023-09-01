# https://www.acmicpc.net/problem/2579
from sys import stdin
s = stdin.readline
n = int(s())

arr = [0 for _ in range(max(3, n+1))]
for i in range(n):
    arr[i] = int(s())
arr = [0] + arr

ans = [0 for _ in range(max(3, n+1))]
ans[1] = arr[1]
ans[2] = ans[1] + arr[2]

for i in range(3, n+1):
    ans[i] = arr[i] + max(ans[i-3]+arr[i-1], ans[i-2])
    
print(ans[-1])
