# https://www.acmicpc.net/problem/17070
from sys import stdin

s = stdin.readline
N = int(s())
arr = []
for _ in range(N):
    arr.append(list(map(int, s().split())))

dp = [[[0 for _ in range(N)] for _ in range(N)] for _ in range(3)]

dp[0][0][1] = 1
for i in range(2,N):
    if arr[0][i] == 0:
        dp[0][0][i] = dp[0][0][i-1]

for r in range(1,N):
    for c in range(1,N):

        if arr[r][c] ==0:

            if arr[r-1][c]==0 and arr[r][c-1]==0:
                
                dp[2][r][c] = dp[2][r-1][c-1] + dp[1][r-1][c-1] + dp[0][r-1][c-1]
            
            dp[0][r][c] = dp[0][r][c-1] + dp[2][r][c-1]
            dp[1][r][c] = dp[1][r-1][c] + dp[2][r-1][c]

print(sum(dp[i][N-1][N-1] for i in range(3)))


