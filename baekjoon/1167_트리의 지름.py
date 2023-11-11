def dfs(node, cost):
    for d, w in V[node]:
        if visited[d] >= 0:
            continue
        new_cost = cost + w
        visited[d] = new_cost
        dfs(d, new_cost)

    return


if __name__ == "__main__":
    N = int(input())
    V = {}
    for i in range(1, N + 1):
        V[i] = []
    for _ in range(1, N + 1):
        idx, *arr, _ = map(int, input().split())
        for i in range(len(arr) // 2):
            V[idx].append((arr[2 * i], arr[2 * i + 1]))

    visited = [-1 for _ in range(N + 1)]
    visited[1] = 0
    dfs(1, 0)
    new_node = visited.index(max(visited))
    visited = [-1 for _ in range(N + 1)]
    visited[new_node] = 0
    dfs(new_node, 0)
    print(max(visited))
