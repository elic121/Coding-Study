# https://www.acmicpc.net/problem/15666
from sys import stdin
s = stdin.readline

N, M = map(int,s().split())
lst = list(map(int,s().split()))
lst.sort()

S = set()

def solution1(n:int,tmp:list):
    global S

    if len(tmp) == M:

        s = tuple(tmp)
        if s in S:
            return
        
        S.add(s)
        print(*tmp)
        return
    
    for i in range(n,N):
        tmp.append(lst[i])
        solution1(i,tmp)
        tmp.pop()

    return

solution1(0,[])