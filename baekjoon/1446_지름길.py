# https://www.acmicpc.net/problem/1446
from sys import stdin
s = stdin.readline
N, D = map(int, s().split())
info = [list(map(int,s().split())) for _ in range(N)]
l = [i for i  in range(D+1)]
for i in range(D+1):
    l[i] = min(l[i],l[i-1]+1)
    for s,e,c in info:
        if e>D:
            continue
        if i==s:
            l[e] = min(l[e],l[i]+c)
print(l[D])