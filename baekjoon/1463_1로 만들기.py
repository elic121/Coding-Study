# https://www.acmicpc.net/problem/1463
from sys import stdin
s = stdin.readline

N = int(s())
l = [0 for _ in range(N+1)]

for i in range(2,N+1):
    l[i] = l[i-1] + 1
    if i%2==0:
        l[i] = min(l[i//2]+1,l[i])
    if i%3==0:
        l[i] = min(l[i//3]+1,l[i])

print(l[N])