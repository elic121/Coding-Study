from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def bfs():
    d = deque()
    d.append((0, 0))
    dist = [[float('inf')] * N for _ in range(N)]
    dist[0][0] = 0

    while d:
        x, y = d.popleft()
        for i, j in zip(dx, dy):
            nx, ny = x + i, y + j
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                continue
            val = arr[nx][ny]

            if dist[nx][ny] > dist[x][y] + val:
                d.append((nx, ny))
                dist[nx][ny] = dist[x][y] + val

    return dist[N - 1][N - 1]


if __name__ == "__main__":
    T = int(input())
    for tc in range(1, T + 1):
        N = int(input())
        arr = []
        for _ in range(N):
            lst = list(map(int, list(input())))
            arr.append(lst)
        cnt = bfs()
        print(f"#{tc} {cnt}")
