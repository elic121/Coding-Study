N, M = 5, 7
arr = [[float("inf") for _ in range(N + 1)] for _ in range(N + 1)]
for i in range(1, N + 1):
    for j in range(1, N + 1):
        if i == j:
            arr[i][j] = 0

t = [[1, 2], [1, 3], [1, 4], [2, 4], [3, 4], [3, 5], [4, 5]]
x, K = 4, 5

for a, b in t:
    arr[a][b] = 1
    arr[b][a] = 1

for k in range(1, N + 1):
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j])

distance = arr[1][K] + arr[K][x]
if distance >= float("inf"):
    print(-1)
else:
    print(distance)

for i in arr:
    print(*i)
