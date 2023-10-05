# https://www.acmicpc.net/problem/16236
from sys import stdin
from collections import deque
s = stdin.readline
N = int(s())
arr = []
sx, sy = -1, -1
fish = {}
ignore = [[0 for _ in range(N)] for _ in range(N)]

for i in range(N):
    lst = list(map(int, s().split()))
    for j, val in enumerate(lst):
        if val == 0:
            continue
        if val == 9:
            sx, sy = i, j
            continue
        if val in fish:
            fish[val].append((i, j))
        else:
            fish[val] = [(i, j)]

    arr.append(lst)

scale = 2

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]


def bfs(i, j, sc):
    tmp = [[0 for _ in range(N)] for _ in range(N)]
    d = deque()
    d.append((i, j))
    while d:
        fx, fy = d.popleft()
        for X, Y in zip(dx, dy):
            nx, ny = fx+X, fy+Y
            if (nx<0 or nx>=N) or (ny<0 or ny>=N):
                continue
            if tmp[nx][ny] >= 1:
                continue
            if arr[nx][ny] > sc:
                continue
            tmp[nx][ny] = tmp[fx][fy] + 1
            d.append((nx,ny))

    m = {}
    for k in fish.keys():
        if k >= sc:
            continue
        for val in fish[k]:
            tx, ty = val
            if ignore[tx][ty] == 1:
                continue
            D = tmp[tx][ty]
            if D >= 1:
                if D in m:
                    m[D].append((tx,ty))
                else:
                    m[D] = [(tx,ty)]
    
    try:
        MINKEY = min(m.keys())
        where = sorted(m[MINKEY],key = lambda x:(x[0],x[1]))[0]
        return where[0], where[1], MINKEY
    
    except:
        return None, None, None

tmp = 0
time = 0
while True:
    nx, ny, T = bfs(sx,sy,scale)
    if nx == None:
        break
    time += T
    arr[sx][sy] = 0
    sx, sy = nx, ny
    ignore[sx][sy] = 1
    arr[sx][sy] = 9
    tmp += 1
    if scale == tmp:
        scale += 1
        tmp = 0
print(time)