from sys import stdin
from collections import deque as Q

s = stdin.readline
N, M = map(int, s().split())
d = [0, 0, 1, -1]
arr = []
sPos: tuple
for i in range(N):
    l = list(s().strip())
    for idx, j in enumerate(l):
        if j == "I":
            sPos = (i, idx)
    arr.append(l)

cnt = 0


def bfs(i: int, j: int):
    global cnt
    q = Q()
    q.append((i, j))
    while q:
        x, y = q.popleft()
        for idx in range(4):
            nx, ny = x + d[idx], y + d[3 - idx]
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
                continue
            if arr[nx][ny] == "X":
                continue
            if arr[nx][ny] == "P":
                cnt += 1
            arr[nx][ny] = "X"
            q.append((nx, ny))


bfs(sPos[0], sPos[1])
print(["TT", cnt][cnt > 0])
