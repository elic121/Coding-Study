# https://www.acmicpc.net/problem/15665
from sys import stdin
s = stdin.readline

N, M = map(int,s().split())
lst = list(map(int,s().split()))
lst.sort()

S = set()

def solution1(tmp:list):
    global S

    if len(tmp) == M:

        s = tuple(tmp)
        if s in S:
            return
        
        S.add(s)
        print(*tmp)
        return
    
    for i in range(N):
        tmp.append(lst[i])
        solution1(tmp)
        tmp.pop()

    return

solution1([])