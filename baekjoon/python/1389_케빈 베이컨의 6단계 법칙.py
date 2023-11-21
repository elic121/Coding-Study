# https://www.acmicpc.net/problem/1389
from sys import stdin

s = stdin.readline
N, M = map(int, s().split())
arr = [[float("inf") for _ in range(N)] for _ in range(N)]
for _ in range(M):
    x, y = map(int, s().split())
    arr[x - 1][y - 1] = 1
    arr[y - 1][x - 1] = 1


def floyd_warshall():
    for k in range(N):
        for i in range(N):
            for j in range(N):
                if i == j:
                    continue
                arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j])


floyd_warshall()

MIN = 10**6
key = 0
for i in range(1, N + 1):
    tmp = sum([j for j in arr[i - 1] if j != float("inf")])
    if tmp < MIN:
        MIN = tmp
        key = i

print(key)
