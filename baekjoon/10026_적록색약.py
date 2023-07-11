# https://www.acmicpc.net/problem/10026
import sys
from copy import deepcopy
sys.setrecursionlimit(10**6)
s = sys.stdin.readline
N = int(s())
arr= [list(s().strip()) for _ in range(N)]
arr1 = deepcopy(arr)

dx = [1,-1,0,0]
dy = [0,0,1,-1]
def rgb(a:list,x,y,sign,handi):
    if (x<0 or x>=N) or (y<0 or y>=N):
        return False
    if a[x][y] != sign:
        if ((a[x][y] == 'R' and sign=='G') or (a[x][y] == 'G' and sign=='R')) and handi:
            pass
        else:
            return False
    if a[x][y] in 'RGB':
        tmp = a[x][y]
        a[x][y] = 'X'
        for X,Y in zip(dx,dy):
            nx, ny = x+X, y+Y
            rgb(a,nx,ny,tmp,handi)
        return True
    return False

cnt=0
cnt1=0
for i in range(N):
    for j in range(N):
        if rgb(arr,i,j,arr[i][j],handi=False):
            cnt+=1
for i in range(N):
    for j in range(N):
        if rgb(arr1,i,j,arr1[i][j],handi=True):
            cnt1+=1

print(cnt,cnt1)