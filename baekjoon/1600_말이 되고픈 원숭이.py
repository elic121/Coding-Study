# https://www.acmicpc.net/problem/1600
from sys import stdin
from collections import deque

s = stdin.readline

h_move = [(1, 2)]
for _ in range(3):
    x, y = h_move[-1]
    h_move.append((-y, x))
h_move.append((2, 1))
for _ in range(3):
    x, y = h_move[-1]
    h_move.append((-y, x))


def init():
    """변수들을 초기화 합니다."""
    K = int(s())
    W, H = map(int, s().split())
    arr = []
    for _ in range(H):
        lst = list(map(int, s().split()))
        arr.append(lst)

    return K, H, W, arr, h_move


def condition(x, y):
    if (x < 0 or x >= H) or (y < 0 or y >= W):
        return False
    return True


dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def bfs():
    d = deque()
    d.append((0, 0, K))
    visited = [[[0 for _ in range(31)] for _ in range(W)] for _ in range(H)]

    while d:
        x, y, cnt = d.popleft()

        if x == H - 1 and y == W - 1:
            return visited[x][y][cnt]

        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if not condition(nx, ny):
                continue
            if visited[nx][ny][cnt] >= 1:
                continue
            if arr[nx][ny] == 1:
                continue
            visited[nx][ny][cnt] = visited[x][y][cnt] + 1
            d.append((nx, ny, cnt))

        if cnt >= 1:
            for idx in range(8):
                sx, sy = h_move[idx]
                nx, ny = x + sx, y + sy
                if not condition(nx, ny):
                    continue
                if visited[nx][ny][cnt - 1] >= 1:
                    continue
                if arr[nx][ny] == 1:
                    continue
                visited[nx][ny][cnt - 1] = visited[x][y][cnt] + 1
                d.append((nx, ny, cnt - 1))

    return -1


if __name__ == "__main__":
    K, H, W, arr, h_move = init()

    print(bfs())
