# https://www.acmicpc.net/problem/9251
from sys import stdin
s = stdin.readline
l1 = s().strip()
l2 = s().strip()
n = len(l1)
m = len(l2)

arr = [[0 for _ in range(m+1)] for _ in range(n+1)]
for i in range(n+1):
    for j in range(m+1):
        if i==0 or j==0:
            continue
        if l1[i-1] == l2[j-1]:
            arr[i][j] = arr[i-1][j-1] + 1
        else:
            arr[i][j] = max(arr[i-1][j],arr[i][j-1])

print(arr[n][m])
        