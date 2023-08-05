# https://www.acmicpc.net/problem/1074
from sys import stdin
s = stdin.readline
N, r, c = map(int,s().split())
ans = 0
def Z(i,j,N):
    global ans

    if N==1:
        if i==r and j==c: 
            print(ans)
            exit()

        ans += 1
        return
    
    n = N//2
    if (i<=r<i+n) and (j<=c<j+n):
        Z(i,j,n)
    elif (i<=r<i+n) and (j+n<=c):
        ans += n**2
        Z(i,j+n,n)
    elif (i+n<=r) and (j<=c<j+n):
        ans += 2*n**2
        Z(i+n,j,n)
    else:
        ans += 3*n**2
        Z(i+n,j+n,n)

Z(0,0,2**N)