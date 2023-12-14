import sys
s=sys.stdin.readline
f=lambda:map(int,s().split())
N,M=f()
A=[];B=[]
for i in range(N):A.append(list(f()))
_,K=f()
for i in range(M):B.append( list(f()))
arr = [[0 for _ in range(K)] for _ in range(N)]
for i in range(N):
    for j in range(K):
        for m in range(M):arr[i][j] += A[i][m] * B[m][j]
for lst in arr:print(*lst)