def dfs(i, cnt, visited):
    global MAX_LEN
    MAX_LEN = max(MAX_LEN, cnt)
    visited[i] = 1
    for idx in edge[i]:
        if visited[idx]:
            continue
        visited[idx] = 1
        dfs(idx, cnt+1, visited)
        visited[idx] = 0

    return


if __name__ == "__main__":
    T = int(input())
    for t in range(1, T + 1):
        N, M = map(int, input().split())
        edge = {}
        MAX_LEN = 1
        for i in range(1, N + 1):
            edge[i] = []
        for _ in range(M):
            x, y = map(int, input().split())
            edge[x].append(y)
            edge[y].append(x)

        for i in range(1, N + 1):
            if i in edge:
                dfs(i,1, [0 for _ in range(N+1)])

        print(f"#{t} {MAX_LEN}")
