N = 3
dp = [0]*N
dp[0] = 1
dp[1] = 3
for i in range(2,N):
    dp[i] = dp[i-2]*2 + dp[i-1]
print(dp[N-1])