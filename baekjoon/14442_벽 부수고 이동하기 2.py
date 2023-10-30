# https://www.acmicpc.net/problem/14442
from sys import stdin
from collections import deque

s = stdin.readline

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]


def condition(x, y):
    if (x < 0 or x >= N) or (y < 0 or y >= M):
        return False
    return True


def bfs():
    visited = [[[0] * (K + 1) for _ in range(M)] for _ in range(N)]
    d = deque()
    d.append((0, 0, K))
    visited[0][0][K] = 1
    while d:
        x, y, cnt = d.popleft()
        if x == N - 1 and y == M - 1:
            return visited[x][y][cnt]

        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if not condition(nx, ny):
                continue
            if arr[nx][ny] == 0:
                if visited[nx][ny][cnt] >= 1:
                    continue
                d.append((nx, ny, cnt))
                visited[nx][ny][cnt] = visited[x][y][cnt] + 1
            if arr[nx][ny] == 1 and cnt >= 1:
                if visited[nx][ny][cnt - 1] >= 1:
                    continue
                d.append((nx, ny, cnt - 1))
                visited[nx][ny][cnt - 1] = visited[x][y][cnt] + 1

    return -1


if __name__ == "__main__":
    N, M, K = map(int, s().split())
    arr = [list(map(int, list(s().strip()))) for _ in range(N)]
    print(bfs())
