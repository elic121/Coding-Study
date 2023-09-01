# https://www.acmicpc.net/problem/11726
from sys import stdin
s = stdin.readline
n = int(s())
l = [0 for _ in range(max(3,n+1))]
l[1] = 1
l[2] = 2
for i in range(3,n+1):
    l[i] = l[i-1] + l[i-2]

print(l[n]%10_007)
