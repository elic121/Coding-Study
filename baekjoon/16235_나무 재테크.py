# https://www.acmicpc.net/problem/16235
from sys import stdin
from collections import deque

s = stdin.readline
N, M, K = map(int, s().split())
mineral = []
for _ in range(N):
    lst = list(map(int, s().split()))
    mineral.append(lst)
arr = [[5 for _ in range(N)] for _ in range(N)]
tree = [[deque() for _ in range(N)] for _ in range(N)]
for _ in range(M):
    x, y, z = map(int, s().split())
    if not tree[x - 1][y - 1]:
        tree[x - 1][y - 1].append(z)
    else:
        if tree[x - 1][y - 1][-1] <= z:
            tree[x - 1][y - 1].append(z)
        else:
            tree[x - 1][y - 1].appendleft(z)

dx = [-1, -1, -1, 0, 0, 1, 1, 1]
dy = [-1, 0, 1, -1, 1, -1, 0, 1]


def springAndSummer():
    global tree
    dieLst = [[0 for _ in range(N)] for _ in range(N)]

    for i in range(N):
        for j in range(N):
            key = tree[i][j]
            if len(key) == 0:
                continue

            cnt = 0
            for idx in range(len(key)):
                if tree[i][j][idx] <= arr[i][j]:
                    arr[i][j] -= tree[i][j][idx]
                    tree[i][j][idx] += 1
                    cnt += 1
                else:
                    break

            for _ in range(len(key) - cnt):
                dieLst[i][j] += tree[i][j].pop() // 2

    for i in range(N):
        for j in range(N):
            if dieLst[i][j] >= 1:
                arr[i][j] += dieLst[i][j]


def autumn():
    global tree

    for i in range(N):
        for j in range(N):
            key = tree[i][j]
            for val in key:
                if val % 5 != 0:
                    continue
                for X, Y in zip(dx, dy):
                    nx, ny = i + X, j + Y
                    if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                        continue
                    tree[nx][ny].appendleft(1)


def winter():
    for i in range(N):
        for j in range(N):
            arr[i][j] += mineral[i][j]


for _ in range(K):
    springAndSummer()
    autumn()
    winter()

cnt = 0
for i in range(N):
    for j in range(N):
        cnt += len(tree[i][j])
print(cnt)
