from collections import deque


def condition(x, y):
    if (x < 0 or x >= N) or (y < 0 or y >= M):
        return False
    return True


dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def bfs():
    d = deque()
    d.append((0, 0, 0))
    visited[0][0] = 1
    while d:
        x, y, cnt = d.popleft()
        if x == N - 1 and y == M - 1:
            return cnt
        for i, j in zip(dx, dy):
            nx, ny = x + i, y + j
            if not condition(nx, ny):
                continue
            if visited[nx][ny]:
                continue
            visited[nx][ny] = 1
            if arr[nx][ny]:
                d.append((nx, ny, cnt + 1))
            else:
                d.appendleft((nx, ny, cnt))


if __name__ == "__main__":
    M, N = map(int, input().split())
    arr = []
    for _ in range(N):
        lst = list(map(int, list(input().strip())))
        arr.append(lst)

    visited = [[0 for _ in range(M)] for _ in range(N)]
    res = bfs()
    print(res)
