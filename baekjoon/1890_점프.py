# https://www.acmicpc.net/problem/1890
from sys import stdin
s = stdin.readline
N = int(s())
arr = [list(map(int,s().split())) for _ in range(N)]
tmp = [[0 for _ in range(N)] for _ in range(N)]
tmp[0][0] = 1
pos = [(0,1),(1,0)]

for i in range(N):
    for j in range(N):
        if arr[i][j] == 0:
            continue
        if tmp[i][j] == 0:
            continue
        val = arr[i][j]
        for x,y in pos:
            nx, ny = i+x*val,j+y*val
            if nx>=N or ny>=N:
                continue
            tmp[nx][ny] += tmp[i][j]
print(tmp[-1][-1])

# c = 0
# def dfs(x,y):
#     global c
#     if x == y == N-1:
#         c+=1
#         return
#     step = arr[x][y]
#     for i,j in pos:
#         nx, ny = x+i*step, y+j*step
#         if nx>=N or ny>=N:
#             continue
#         dfs(nx,ny)
# dfs(0,0)
# print(c)