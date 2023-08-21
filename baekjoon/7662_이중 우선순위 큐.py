# https://www.acmicpc.net/problem/7662
from sys import stdin
from heapq import heappush,heappop,nlargest
s = stdin.readline
N = int(s())

# 시간초과
def solution1():
    n = int(s())
    L = []
    for i in range(n):
        a, b = s().strip().split()
        if a == 'I':
            heappush(L,int(b))
        else:
            if not L:
                continue
            if int(b) == -1:
                heappop(L)
            if int(b) == 1:
                L.pop(L.index(nlargest(1,L)[0]))
    if L:
        print(nlargest(1,L)[0],L[0])
    else:
        print("EMPTY")

# id, 방문을 사용할 것!!
# 연산 마친 후 해당 id가 방문했는지를 체크해서 버릴지 말지 결정
def solution2():
    n = int(s())
    visited = [0 for _ in range(n)]
    L1 = []
    L2 = []

    for i in range(n):
        a, b = s().strip().split()
        if a == 'I':
            heappush(L1,(int(b),i))
            heappush(L2,(-int(b),i))
        else:
            if int(b) == 1:
                while L2 and visited[L2[0][1]] == 1:
                    heappop(L2)
                if not L2:
                    continue
                _, id = heappop(L2)
                visited[id] = 1
            else:
                while L1 and visited[L1[0][1]] == 1:
                    heappop(L1)
                if not L1:
                    continue
                _, id = heappop(L1)
                visited[id] = 1

    while L1 and visited[L1[0][1]] == 1:
        heappop(L1)    
    while L2 and visited[L2[0][1]] == 1:
        heappop(L2)    

    if (not L1) or (not L2):
        print("EMPTY")
    else:
        print(-L2[0][0],L1[0][0])

for _ in range(N):
    solution2()    