# https://www.acmicpc.net/problem/2636
from sys import stdin
from collections import deque
s = stdin.readline
N, M = map(int,s().split())
arr =[list(map(int,s().split())) for _ in range(N)]

dx = [1,-1,0,0]
dy = dx[::-1]


def bfs(arr:list):
    d = deque()
    d.append((0,0))
    while d:
        i,j = d.popleft()
        for x,y in zip(dx,dy):
            nx,ny = i+x,j+y
            if (nx<0 or nx>=N) or (ny<0 or ny>=M):
                continue
            if arr[nx][ny] != 0:
                continue    
            arr[nx][ny] = -1
            d.append((nx,ny))
    
    tmpList = []
    for i in range(1,N-1):
        for j in range(1,M-1):
            if arr[i][j] == 1:
                for x,y in zip(dx,dy):
                    nx, ny = i+x, j+y
                    if arr[nx][ny] == -1:
                        tmpList.append((i,j))
                        break
    
    for x,y in tmpList:
        arr[x][y] = -1
    
    return arr,len(tmpList)

cnt = 0
tmp = 0
while True:
    arr,n = bfs(arr)
    for i in range(N):
        for j in range(M):
            if arr[i][j] == -1:
                arr[i][j] = 0
    if n == 0:
        print(cnt)
        print(tmp)
        break
    cnt += 1
    tmp = n
