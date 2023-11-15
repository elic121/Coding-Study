# https://www.acmicpc.net/problem/1238

from heapq import *


def dijkstra(start, arr):
    dist = [100_000_000_9 for _ in range(N)]
    queue = [(0, start)]

    while queue:
        val, idx = heappop(queue)
        for seq in range(N):
            if dist[seq] > val + arr[idx][seq]:
                dist[seq] = val + arr[idx][seq]
                heappush(queue, (dist[seq], seq))

    return dist


def dijkstra_implement():
    rev_arr = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            rev_arr[i][j] = arr[j][i]

    res1 = dijkstra(X - 1, arr)
    res2 = dijkstra(X - 1, rev_arr)

    return max([x+y for x, y in zip(res1,res2)])


if __name__ == '__main__':
    N, M, X = map(int, input().split())
    arr = [[100_000_000_9 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        arr[i][i] = 0

    for i in range(M):
        x, y, t = map(int, input().split())
        arr[x - 1][y - 1] = t

    ans = dijkstra(X - 1, arr)
    for i in range(N):
        if i == X - 1:
            continue
        res = dijkstra(i, arr)
        ans[i] += res[X - 1]

    print(max(ans))

    # print(dijkstra_implement())