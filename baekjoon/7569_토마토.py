# https://www.acmicpc.net/problem/7569
import sys
from collections import deque
s = sys.stdin.readline
M, N, H = map(int, s().split())
arr = [[list(map(int, s().split())) for _ in range(N)] \
       for _ in range(H)]


ripe = deque()
for k in range(H):
    for j in range(N):
        for i in range(M):
            if arr[k][j][i] == 1:
                ripe.append((k,j,i))

dx = [0,0,1,-1,0,0]
dy = [0,0,0,0,1,-1]
dz = [-1,1,0,0,0,0]

def find_zero(arr):
    for k in range(H):
        for j in range(N):
            for i in range(M):
                if arr[k][j][i] == 0:
                    return False
    return True

def bfs():
    cnt = 0
    while ripe:
        if find_zero(arr):
            return cnt
        L = len(ripe)
        for _ in range(L):
            z,y,x = ripe.popleft()
            for X,Y,Z in zip(dx,dy,dz):
                nx, ny, nz = x+X, y+Y, z+Z
                if (nx<0 or nx>=M) or (ny<0 or ny>=N) or (nz<0 or nz>=H):
                    continue
                if arr[nz][ny][nx] != 0:
                    continue
                arr[nz][ny][nx] = arr[z][y][x] + 1
                ripe.append((nz,ny,nx))
        cnt+=1
    
    for i in range(H):
        for j in range(N):
            for k in range(M):
                if arr[i][j][k] == 0:
                    return -1
                
    return cnt

print(bfs())