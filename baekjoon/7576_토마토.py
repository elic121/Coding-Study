# https://www.acmicpc.net/problem/7576
import sys
from collections import deque

s = sys.stdin.readline
M, N = map(int,s().split())

arr = []
for _ in range(N):
    arr.append(list(map(int,s().split())))

ripe = deque()
zero_cnt = 0
for i in range(N):
    for j in range(M):
        if arr[i][j] == 1:
            ripe.append((i,j))
        if arr[i][j] == 0:
            zero_cnt +=1

dx = [1,-1,0,0]
dy = [0,0,1,-1]

def bfs(arr):
    cnt=0
    while ripe:
        L = len(ripe)
        for _ in range(L):
            x,y = ripe.popleft()
            for X,Y in zip(dx,dy):
                nx, ny = x+X, y+Y

                if (nx<0 or nx>=N) or (ny<0 or ny>=M):
                    continue
                if arr[nx][ny] >= 1:
                    continue
                if arr[nx][ny] == -1:
                    continue
                if arr[nx][ny] == 0:
                    arr[nx][ny] = arr[x][y] + 1
                    ripe.append((nx,ny))
        cnt+=1
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                return -1
    return cnt

if zero_cnt >=1:
    val = bfs(arr)
    print(val-(val>=1))
else:
    print(0)