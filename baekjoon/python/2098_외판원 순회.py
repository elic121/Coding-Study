from sys import stdin
s = stdin.readline
N = int(s())
arr = [list(map(int,s().split())) for _ in range(N)]
dp = [[40_000_001 for _ in range(1 << N)] for _ in range(N)]

def travel(node, visited):
    visited |= 1<<node
    if visited == (1 << N) - 1:
        return arr[node][0] if arr[node][0] else 20_000_001
 
    if dp[node][visited] != 40_000_001:
        return dp[node][visited]
    
    for i in range(N):
        if visited & (1 << i) != 0: continue
        if arr[node][i] == 0: continue
        dist = arr[node][i] + travel(i, visited | 1<<i)
        dp[node][visited] = min(dp[node][visited], dist)

    return dp[node][visited]

print(travel(0, 0))