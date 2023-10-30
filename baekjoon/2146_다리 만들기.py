# https://www.acmicpc.net/problem/2146
from sys import stdin
from collections import deque

s = stdin.readline

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def condition(x, y):
    if (x < 0 or x >= N) or (y < 0 or y >= N):
        return False
    return True


def fill(i, j, val):
    d = deque()
    d.append((i, j))
    arr[i][j] = val
    visited = [[0 for _ in range(N)] for _ in range(N)]
    visited[i][j] = 1
    while d:
        x, y = d.popleft()
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if not condition(nx, ny):
                continue
            if visited[nx][ny] == 1:
                continue
            if arr[nx][ny] != 1:
                continue
            arr[nx][ny] = val
            visited[nx][ny] = 1
            d.append((nx, ny))


def seperate():
    "각 섬들을 구분"
    cnt = 2
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 1:
                fill(i, j, cnt)
                cnt += 1

    return cnt


def check_around(i, j):
    "하나라도 바다가 있으면 True"
    cnt = 0
    val = arr[i][j]
    for x, y in zip(dx, dy):
        nx, ny = i + x, j + y
        if not condition(nx, ny):
            cnt += 1
            continue
        if arr[nx][ny] == val:
            cnt += 1
    return cnt < 4


def find_other_island(i, j, val):
    if not check_around(i, j):
        return

    visited = [[0 for _ in range(N)] for _ in range(N)]
    d = deque()
    d.append((i, j))

    while d:
        x, y = d.popleft()
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            if not condition(nx, ny):
                continue
            if visited[nx][ny] >= 1:
                continue
            if arr[nx][ny] == val:
                continue
            if arr[nx][ny] > val:
                dist = visited[x][y]
                if island[val][arr[nx][ny]] > dist:
                    island[val][arr[nx][ny]] = dist
                visited[nx][ny] = dist + 1
                continue
            visited[nx][ny] = visited[x][y] + 1
            d.append((nx, ny))


def find_minimum():
    cnt = 100_000_000_9
    for key, val in island.items():
        for k in val.values():
            cnt = min(cnt, k)
    print(cnt)


if __name__ == "__main__":
    N = int(s())
    arr = []
    island = {}
    for _ in range(N):
        lst = list(map(int, s().split()))
        arr.append(lst)

    island_cnt = seperate()
    for c in range(island_cnt - 3):
        island[c + 2] = {}
        for r in range(c + 1 + 2, island_cnt):
            island[c + 2][r] = 100_000_000_9

    for i in range(N):
        for j in range(N):
            if 2 <= arr[i][j] <= 2 + island_cnt - 2:
                find_other_island(i, j, arr[i][j])

    find_minimum()
