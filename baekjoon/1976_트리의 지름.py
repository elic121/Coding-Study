import sys
sys.setrecursionlimit(10**8)

def sol(n, cost):
    for b, w in arr[n]:
        if visited[b] == -1:
            new_cost = cost + w
            visited[b] = new_cost
            sol(b, new_cost)

    return


if __name__ == "__main__":
    N = int(input())

    arr = {}
    for i in range(N):
        arr[i] = []

    for _ in range(N - 1):
        a, b, w = map(int, input().split())
        arr[a - 1].append((b - 1, w))
        arr[b - 1].append((a - 1, w))

    visited = [-1 for _ in range(N + 1)]
    visited[0] = 0
    sol(0, 0)

    idx, val = 0, 0
    for key, i in enumerate(visited):
        if val < i:
            val = i
            idx = key

    visited = [-1 for _ in range(N + 1)]
    visited[idx] = 0
    sol(idx, 0)

    print(max(visited))
