# https://www.acmicpc.net/problem/13023

sol = 0


def dfs(n, idx, vis):
    global sol
    if n == 5:
        sol = 1
        return
    for val in relation[idx]:
        if vis[val] == 1:
            continue
        vis[val] = 1
        dfs(n + 1, val, vis)
        vis[val] = 0

    return


if __name__ == '__main__':
    N, M = map(int, input().split())
    relation = {}
    for i in range(N):
        relation[i] = []
    for _ in range(M):
        a, b = map(int, input().split())
        relation[a].append(b)
        relation[b].append(a)

    for i in range(N):
        if not relation[i]:
            continue
        visited = [0 for _ in range(N)]
        visited[i] = 1
        dfs(1, i, visited)
        if sol == 1:
            print(1)
            exit()

    print(0)
