# https://www.acmicpc.net/problem/11404
from sys import stdin
s = stdin.readline
N = int(s())
M = int(s())
arr = [[float('inf') for _ in range(N+1)] for _ in range(N+1)]

for _ in range(M):
    i,j,c = map(int,s().split())
    arr[i][j] = min(arr[i][j],c)

for k in range(1,N+1):
    for i in range(1,N+1):
        for j in range(1,N+1):
            if i==j:
                continue
            arr[i][j] = min(arr[i][j],arr[i][k]+arr[k][j])

for val in arr[1:]:
    for v in val[1:]:
        print([0,v][v!=float('inf')],end=' ')
    print()