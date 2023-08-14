# https://www.acmicpc.net/problem/15686
from sys import stdin
s = stdin.readline
N, M = map(int,s().split())
chicken :list[tuple]= []
home :list[tuple]= []

def distance(a:tuple,b:tuple)->int:
    return abs(a[0]-b[0])+abs(a[1]-b[1])

dist = 10**4
def combination(n:int,tmp:list):
    global dist
    if len(tmp) == M:
        D = 0
        for val in home:
            tmpVal = 101
            for t in tmp:
                compVal = distance(val,t)
                if tmpVal > compVal:
                    tmpVal = compVal
            D += tmpVal
        
        if dist > D:
            dist = D

        return
    
    for i in range(n,len(chicken)):
        tmp.append(chicken[i])
        combination(i+1,tmp)
        tmp.pop()

for i in range(N):
    for j,val in enumerate(map(int,s().split())):
        if val == 1:
            home.append((i,j))
        if val == 2:
            chicken.append((i,j))

combination(0,[])
print(dist)