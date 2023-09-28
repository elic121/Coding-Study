# https://www.acmicpc.net/problem/20056
from sys import stdin
s = stdin.readline

def condition1(x, y, direction, speed):
    nx, ny = (x + speed*dx[direction]) % N, (y + speed*dy[direction]) % N
    return nx, ny


def condition2(lst: list or tuple):
    mass = 0
    velo = 0
    odd = 0
    even = 0
    for m, v, d in lst:
        mass += m
        velo += v
        if d % 2 == 0:
            even += 1
        else:
            odd += 1
    if even == 0 or odd == 0:
        dire = 'even'
    else:
        dire = 'odd'

    return mass//5, velo//len(lst), dire


N, M, K = map(int, s().split())
arr = [[0 for _ in range(N)] for _ in range(N)]
dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

visited = [[[] for _ in range(N)] for _ in range(N)]
for _ in range(M):
    r, c, m, S, d = map(int, s().split())
    visited[r-1][c-1].append((m, S, d))

for _ in range(K):
    Tvisited = [[[] for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                continue
            for val in visited[i][j]:
                MASS = val[0]
                SPEED = val[1]
                DIRE = val[2]
                newx, newy = condition1(i, j, DIRE, SPEED)
                Tvisited[newx][newy].append((MASS, SPEED, DIRE))

    tmpD = {}
    tmpvisited = [[[] for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if not Tvisited[i][j]:
                continue
            if len(Tvisited[i][j]) < 2:
                tmpvisited[i][j].append(Tvisited[i][j][0])
                continue
            M, V, D = condition2(Tvisited[i][j])
            if M == 0:
                continue
            for k in range((D == 'odd'), 8, 2):
                tmpvisited[i][j].append((M, V, k))
    
    visited = tmpvisited

SUM = 0
for i in range(N):
    for j in range(N):
        if visited[i][j]:
            for val in visited[i][j]:
                SUM += val[0]
print(SUM)
