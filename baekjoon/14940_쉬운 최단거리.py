# https://www.acmicpc.net/problem/14940
import sys
from collections import deque
s = sys.stdin.readline
N, M = map(int,s().split())
d = deque()
arr = [[0 for _ in range(M)] for _ in range(N)]
A = []
for i in range(N):
    tmp = []
    for idx,j in enumerate(map(int,s().split())):
        if j == 2:
            start = (i,idx)
        tmp.append(j)
    A.append(tmp)
d.append(start)
dx = [1,-1,0,0]
dy = [0,0,1,-1]

def bfs():
    while d:
        X,Y = d.popleft()
        for i,j in zip(dx,dy):
            nx, ny = X+i, Y+j
            if nx == start[0] and ny == start[1]:
                continue
            if (nx<0 or nx>=N) or (ny<0 or ny>=M):
                continue
            if A[nx][ny] == 0:
                continue
            if arr[nx][ny] != 0:
                continue
            arr[nx][ny] = arr[X][Y] + 1
            d.append((nx,ny))

bfs()
for i in range(N):
    for j in range(M):
        if A[i][j] == 1 and arr[i][j] == 0:
            arr[i][j] = -1
for i in range(N):
    print(*arr[i])