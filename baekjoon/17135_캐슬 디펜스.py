# https://www.acmicpc.net/problem/17135
from sys import stdin
from copy import deepcopy
s = stdin.readline
d = lambda x,y : abs(x[0]-y[0])+abs(x[1]-y[1])

N, M, D = map(int,s().split())

arr = []
for i in range(N):
    l = list(map(int,s().split()))
    arr.append(l)

def check(arr,key):
    for i in range(N-key):
        for j in range(M):
            if arr[i][j] == 1:
                return False
    return True

def sol(arr,comb):
    cnt = 0
    key = 0
    while True:
        
        if check(arr,key):
            return cnt
        
        row = N-key
        
        enemy = []
        for i in range(row):
            for j in range(M):
                if arr[i][j] == 1:
                    enemy.append((i,j))

        tmp2 = [] 
        for p in comb:
            tmp = []
            t1 = (row,p)
            for t2 in enemy:
                distance = d(t1,t2)
                if distance <= D:
                    tmp.append((t2[0],t2[1],distance))
            
            if not tmp:
                continue
            
            tmp.sort(key=lambda x:(x[2],x[1]))
            nx,ny = tmp[0][0], tmp[0][1]
            if (nx,ny) not in tmp2:
                tmp2.append((nx,ny))

        for x,y in tmp2:
            arr[x][y] = 0
        cnt += len(tmp2)
        key+=1

MAX = 0
def combination(n,tmp:list):
    global MAX
    if len(tmp) == 3:
        ans = sol(deepcopy(arr),tmp)
        MAX = max(MAX,ans)

    for i in range(n,M):
        tmp.append(i)
        combination(i+1,tmp)
        tmp.pop()
    
combination(0,[])
print(MAX)