# https://www.acmicpc.net/problem/15655
from sys import stdin
s = stdin.readline
N, M = map(int,s().split())
l = list(map(int,s().split()))
l.sort()

def combination(n:int,L:list):
    
    if len(L) == M:
        print(*L)
        return
    
    for i in range(n,N):
        L.append(l[i])
        combination(i+1,L)
        L.pop()
    
    return

combination(0,[])

