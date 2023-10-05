# https://www.acmicpc.net/problem/23288
from sys import stdin
from collections import deque
s = stdin.readline
N, M, K = map(int, s().split())
arr = []
for _ in range(N):
    lst = list(map(int, s().split()))
    arr.append(lst)

px, py = 0, 0
d = 0
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

# 위앞밑뒤오왼
dice = [1, 5, 6, 2, 3, 4]


def east():
    dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = \
        dice[5], dice[1], dice[4], dice[3], dice[0], dice[2]


def west():
    dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = \
        dice[4], dice[1], dice[5], dice[3], dice[2], dice[0]


def north():
    dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = \
        dice[1], dice[2], dice[3], dice[0], dice[4], dice[5]


def south():
    dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = \
        dice[3], dice[0], dice[1], dice[2], dice[4], dice[5]

def bfs(B,i,j):
    tmp = [[0 for _ in range(M)] for _ in range(N)]
    tmp[i][j] = 1
    d = deque()
    d.append((i,j))
    C = 1
    while d:
        x,y = d.popleft()
        for X,Y in zip(dx,dy):
            nx,ny = x+X,y+Y
            if (nx<0 or nx>=N) or (ny<0 or ny>=M):
                continue
            if arr[nx][ny] != B:
                continue
            if tmp[nx][ny] == 1:
                continue
            tmp[nx][ny] = 1
            d.append((nx,ny))
            C += 1
    
    return C

score = 0
for _ in range(K):
    nx, ny = px + dx[d], py + dy[d]
    if (nx < 0 or nx >= N) or (ny < 0 or ny >= M):
        d = (d+2) % 4
        nx, ny = px + dx[d], py + dy[d]
    
    px, py = nx, ny
    B = arr[px][py]

    if d == 0:
        east()
    if d == 1:
        south()
    if d == 2:
        west()
    if d == 3:
        north()

    A = dice[2]

    if A > B:
        d = (d+1) % 4
    elif A < B:
        d = (d-1) % 4
    elif A == B:
        pass
    
    S = bfs(B,px,py)
    score += S*B

print(score)
