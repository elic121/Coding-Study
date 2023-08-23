# https://www.acmicpc.net/problem/15664
from sys import stdin
s = stdin.readline

N, M = map(int,s().split())
lst = list(map(int,s().split()))
lst.sort()

S = set()
visited = [0 for _ in range(N)]

def solution1(n,tmp:list):
    global S

    if len(tmp) == M:

        s = tuple(tmp)
        if s in S:
            return
        
        S.add(s)
        print(*tmp)
        return
    
    for i in range(n,N):
        if visited[i] == 1:
            continue
        visited[i] = 1
        tmp.append(lst[i])
        solution1(i+1,tmp)
        visited[i] = 0
        tmp.pop()

    return

solution1(0,[])