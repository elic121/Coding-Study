# https://www.acmicpc.net/problem/17472
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def find(parent, node):
    if node == parent[node]:
        return node
    return find(parent, parent[node])


def union(parent, a, b):
    a = find(parent, a)
    b = find(parent, b)
    if a > b:
        parent[b] = a
    else:
        parent[a] = b


def kruscal(adj):
    T = 0
    parent = [i for i in range(CNT - 2)]
    edge = []
    for i in range(CNT - 2):
        for j in range(i + 1, CNT - 2):
            if adj[i][j] != 10:
                edge.append((adj[i][j], i, j))

    edge.sort()

    for cost, a, b in edge:
        if find(parent, a) == find(parent, b):
            continue
        union(parent, a, b)
        T += cost

    return T


def findAdj(idx):
    tmp = [10 for _ in range(CNT - 2)]
    for i in range(N):
        for j in range(M):
            if arr[i][j] != idx:
                continue
            for X, Y in zip(dx, dy):
                for s in range(1, max(N, M)):
                    nx, ny = i + s * X, j + s * Y
                    if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                        break
                    if arr[nx][ny] == idx:
                        break
                    if arr[nx][ny] != 0:
                        val = arr[nx][ny]
                        if val <= idx:
                            break
                        if s - 1 < 2:
                            break
                        if s - 1 < tmp[val - 2]:
                            tmp[val - 2] = s - 1
                            break
    return tmp


def bfs(sx, sy):
    global CNT
    d = deque()
    visited = [[0 for _ in range(M)] for _ in range(N)]
    d.append((sx, sy))
    arr[sx][sy] = CNT
    visited[sx][sy] = 1
    while d:
        x, y = d.popleft()
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                continue
            if visited[nx][ny] == 1:
                continue
            if arr[nx][ny] != 1:
                continue
            arr[nx][ny] = CNT
            visited[nx][ny] = 1
            d.append((nx, ny))
    CNT += 1


def is_connected(adj):
    L = len(adj)
    visited = [0 for _ in range(L)]

    def dfs(node):
        visited[node] = 1
        for idx in range(L):
            if adj[node][idx] != 10 and visited[idx] != 1:
                # print(idx)
                dfs(idx)

    dfs(0)
    return all(visited)


if __name__ == "__main__":
    N, M = map(int, input().split())
    CNT = 2
    arr = []
    for _ in range(N):
        lst = list(map(int, input().split()))
        arr.append(lst)

    for i in range(N):
        for j in range(M):
            if arr[i][j] != 1:
                continue
            bfs(i, j)

    adj = []
    for i in range(2, CNT):
        res = findAdj(i)
        adj.append(res)

    for i in range(len(adj)):
        for j in range(i + 1):
            if i == j:
                continue
            adj[i][j] = adj[j][i]

    if not is_connected(adj):
        print(-1)
        exit()

    ans = kruscal(adj)
    print([-1, ans][ans != 0])
