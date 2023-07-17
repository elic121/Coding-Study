# https://www.acmicpc.net/problem/7569
import sys
s = sys.stdin.readline
M, N, H = map(int, s().split())
arr = [[list(map(int, s().split())) for _ in range(N)] \
       for _ in range(H)]
dx = [0,0,1,-1,0,0]
dy = [0,0,0,0,1,-1]
dz = [-1,1,0,0,0,0]

def dfs(i,j,k):
    return 

for i in range(H):
    for j in range(N):
        for k in range(M):
            if arr[i][j][k] == 1:
                dfs(i,j,k)