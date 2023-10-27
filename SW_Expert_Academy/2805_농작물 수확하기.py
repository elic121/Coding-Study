from collections import deque


def condition(x, y):
    if (x < 0 or x >= N) or (y < 0 or y >= N):
        return False
    return True


dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def bfs():
    val = arr[N // 2][N // 2]
    visited = [[0 for _ in range(N)] for _ in range(N)]
    visited[N // 2][N // 2] = 1
    d = deque()
    d.append((N // 2, N // 2))
    while d:
        x, y = d.popleft()
        for i, j in zip(dx, dy):
            nx, ny = x + i, y + j
            if not condition(nx, ny):
                continue
            if visited[nx][ny] >= 1:
                continue
            if visited[x][y] + 1 > (N + 1) // 2:
                break
            d.append((nx, ny))
            val += arr[nx][ny]
            visited[nx][ny] = visited[x][y] + 1

    return val


def iteration():
    cnt = 0
    for i in range(N // 2 + 1):
        for j in range(N // 2 - i, N // 2 + i + 1):
            cnt += arr[i][j]
    for i in range(N // 2 + 1, N):
        for j in range((i - N//2), N - (i - N//2)):
            cnt += arr[i][j]
    return cnt


if __name__ == "__main__":
    T = int(input())
    for tc in range(1, T + 1):
        N = int(input())
        arr = []
        for _ in range(N):
            lst = list(map(int, list(input())))
            arr.append(lst)

        print(f"#{tc} {bfs()}")