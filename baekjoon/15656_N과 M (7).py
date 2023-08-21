# https://www.acmicpc.net/problem/15656
from sys import stdin
s = stdin.readline
N, M = map(int,s().split())
l = list(map(int,s().split()))
l.sort()

def permutation(L:list):
    
    if len(L) == M:
        print(*L)
        return
    
    for i in range(N):
        L.append(l[i])
        permutation(L)
        L.pop()
    
    return

permutation([])