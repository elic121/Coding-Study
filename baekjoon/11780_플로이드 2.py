# https://www.acmicpc.net/problem/11780
from sys import stdin
s = stdin.readline
N = int(s())
M = int(s())
arr = [[float('inf') for _ in range(N+1)] for _ in range(N+1)]

for _ in range(M):
    i, j, c = map(int, s().split())
    arr[i][j] = min(arr[i][j], c)

mark = [[0 for _ in range(N+1)] for _ in range(N+1)]
for k in range(1, N+1):
    for i in range(1, N+1):
        for j in range(1, N+1):
            if i == j:
                continue
            if arr[i][j] > arr[i][k]+arr[k][j]:
                arr[i][j] = arr[i][k]+arr[k][j]
                mark[i][j] = k

def path(i,j):
    if mark[i][j] == 0:
        return []
    k = mark[i][j]
    return path(i,k) + [k] + path(k,j)

for val in arr[1:]:
    for v in val[1:]:
        print([0,v][v!=float('inf')],end=' ')
    print()

for i in range(1,N+1):
    for j in range(1,N+1):
        if arr[i][j] == float('inf'):
            print(0)
            continue
        key = [i]+path(i,j)+[j]
        print(len(key),*key)