# https://www.acmicpc.net/problem/2573
import sys
from copy import deepcopy
from collections import deque

s = sys.stdin.readline
N, M = map(int,s().split())
arr = [list(map(int,s().split())) for _ in range(N)]

dx = [1,-1,0,0]
dy = [0,0,1,-1]

def bfs(x,y,Arr):
    d = deque()
    d.append((x,y))
    while d:
        X, Y = d.popleft()
        for i,j in zip(dx,dy):
            nx, ny = X+i, Y+j
            if (nx<1 or nx>=N-1) or (ny<1 or ny>=M-1):
                continue
            if Arr[nx][ny] == 0:
                continue
            Arr[nx][ny] = 0
            d.append((nx,ny))

def check(tmp):
    cnt =  0
    for i in range(1,N-1):
        for j in range(1,M-1):
            if tmp[i][j] != 0:
                bfs(i,j,tmp)
                cnt+=1
    return cnt

def around():
    tmp = [[0 for _ in range(M)] for _ in range(N)]
    for i in range(1,N-1):
        for j in range(1,M-1):
            if arr[i][j] == 0:
                continue
            cnt = 0
            for X,Y in zip(dx,dy):
                nx, ny = X+i, Y+j
                if (nx<0 or nx>=N) or (ny<0 or ny>=M):
                    continue
                if arr[nx][ny] != 0:
                    continue
                cnt+=1
            tmp[i][j] = cnt
    
    for i in range(1,N-1):
        for j in range(1,M-1):
            if tmp[i][j] != 0:
                arr[i][j] = max(arr[i][j] - tmp[i][j], 0)


CNT = 0
while True:

    tmp = deepcopy(arr)
    ANS = check(tmp)
    
    if ANS == 0:
        print(0)
        break

    if ANS >= 2:
        print(CNT)
        break
    
    around()

    CNT+=1