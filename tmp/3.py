from sys import stdin

s = stdin.readline
N, M, Q = map(int, s().split())
arr = [[0 for _ in range(M)] for _ in range(N)]
for _ in range(Q):
    n, rc, v = map(int, s().split())

    if n == 1:
        for i in range(M):
            arr[rc - 1][i] += v

    else:
        for i in range(N):
            arr[i][rc - 1] += v

for i in arr:
    print(*i)
