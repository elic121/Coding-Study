# https://www.acmicpc.net/problem/16987
from sys import stdin
s = stdin.readline
N = int(s())
arr = [list(map(int, s().split())) for _ in range(N)]
C = 0
def dfs(n, arr):
    global C
    if n == N:
        C = max(C, sum([1 for i in range(N) if arr[i][0] <= 0]))
        return

    if arr[n][0] <= 0:
        dfs(n+1, arr)

    else:
        allBroken = True
        for i in range(N):
            if i == n:
                continue
            if arr[i][0] > 0:
                allBroken = False
                arr[n][0] -= arr[i][1]
                arr[i][0] -= arr[n][1]
                dfs(n+1, arr)
                arr[n][0] += arr[i][1]
                arr[i][0] += arr[n][1]
        
        if allBroken:
            dfs(N,arr)

    return

dfs(0, arr)
print(C)
