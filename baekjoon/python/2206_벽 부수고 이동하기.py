# https://www.acmicpc.net/problem/2206
from sys import stdin
from collections import deque

s = stdin.readline
N, M = map(int, s().split())
wall: list[tuple[int, int]] = []
arr: list[list[str]] = []

for _ in range(N):
    l = [int(i) for i in s().strip()]
    arr.append(l)

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
visited = [[[0 for _ in range(2)] for _ in range(M)] for _ in range(N)]


def bfs():
    d = deque()
    d.append((0, 0, 0))
    visited[0][0][0] = 1

    while d:
        i, j, v = d.popleft()

        if i == N - 1 and j == M - 1:
            return visited[i][j][v]

        for x, y in zip(dx, dy):
            nx, ny = i + x, j + y

            if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                continue

            # 이동할 수 있는 곳일 경우
            if arr[nx][ny] == 0:
                # 이미 찍먹해본 곳이면 나가리.
                if visited[nx][ny][v] == 1:
                    continue

                # 만약 v==1이라면 이미 벽을 통과한 상태이므로 (원코사용)
                # 계속해서 v==1일 수 밖에 없다.
                # 반대로 v==0이라면 아직 원코를 가지고 있는 상태이므로
                # 동일하게 v==0이다.
                visited[nx][ny][v] = visited[i][j][v] + 1
                d.append((nx, ny, v))
                continue

            # 이동할 수 없는 곳일 경우(벽)
            if arr[nx][ny] == 1:
                # v==1 (이미 원코 사용. 통과 못함. 나가리)
                if v == 1:
                    continue

                # v==0 (아직 원코를 사용하지 않은 경우)
                # 원코를 사용한다.(v==1)
                visited[nx][ny][1] = visited[i][j][0] + 1
                d.append((nx, ny, 1))
                continue

    return -1


print(bfs())
