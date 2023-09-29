# https://www.acmicpc.net/problem/20058
from sys import stdin
from collections import deque
s = stdin.readline
N, Q = map(int, s().split())
num = 2**N
arr = []
for _ in range(num):
    arr.append(list(map(int, s().split())))
L = list(map(int, s().split()))
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
MAX = 0
SCORE = 0

def rotate(lst: list, l):
    tmp = [[0 for _ in range(l)] for _ in range(l)]
    for i in range(l):
        for j in range(l):
            tmp[i][j] = lst[l-1-j][i]
    return tmp


def check():
    C = [[0 for _ in range(num)] for _ in range(num)]
    for i in range(num):
        for j in range(num):
            if arr[i][j] == 0:
                continue
            cnt = 0
            for x, y in zip(dx, dy):
                nx, ny = x+i, y+j
                if (nx < 0 or nx >= num) or (ny < 0 or ny >= num):
                    continue
                if arr[nx][ny] == 0:
                    continue
                cnt += 1
            if cnt < 3:
                C[i][j] = 1

    for i in range(num):
        for j in range(num):
            if C[i][j]:
                arr[i][j] -= 1


def devide(x, y, r, c, com):
    if r == 2**com and c == 2**com:
        tmp = [[0 for _ in range(c)] for _ in range(r)]
        for i in range(x, x+r):
            for j in range(y, y+r):
                tmp[i-x][j-y] = arr[i][j]
        tmp2 = rotate(tmp, c)
        for i in range(x, x+r):
            for j in range(y, y+r):
                arr[i][j] = tmp2[i-x][j-y]
        return

    R = r//2
    C = c//2
    devide(x, y, R, C, com)
    devide(x+R, y, R, C, com)
    devide(x, y+C, R, C, com)
    devide(x+R, y+C, R, C, com)


def bfs():
    global MAX
    visited = [[0 for _ in range(num)] for _ in range(num)]
    for i in range(num):
        for j in range(num):
            if arr[i][j] == 0:
                continue
            if visited[i][j]:
                continue
            visited[i][j] = 1
            d = deque()
            d.append((i,j))
            cnt = 1
            while d:
                x,y  = d.popleft()
                for X,Y in zip(dx,dy):
                    nx,ny = x+X, y+Y
                    if (nx<0 or nx>=num) or (ny<0 or ny>=num):
                        continue
                    if visited[nx][ny] == 1:
                        continue
                    if arr[nx][ny] == 0:
                        continue
                    visited[nx][ny] = 1
                    d.append((nx,ny))
                    cnt += 1
            
            MAX = max(MAX,cnt)


for com in L:
    if com != 0:
        devide(0, 0, num, num, com)
    check()

bfs()
NUM = 0
for i in range(num):
    for j in range(num):
        NUM += arr[i][j]
print(NUM)
print(MAX)