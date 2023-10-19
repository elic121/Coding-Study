# https://www.acmicpc.net/problem/2638
from sys import stdin
from collections import deque
s = stdin.readline

dx = [0,1,0,-1]
dy = [1,0,-1,0]

def bfs():
    tmp = [[0 for _ in range(M)] for _ in range(N)]
    visited = [[0 for _ in range(M)] for _ in range(N)]
    d = deque()
    d.append((0,0))
    tmp[0][0] = -1
    visited[0][0] = 1
    cheeze = [[0 for _ in range(M)] for _ in range(N)]
    while d:
        x, y = d.popleft()
        for X,Y in zip(dx,dy):
            nx,ny = x+X,y+Y
            if (nx<0 or nx>=N) or (ny<0 or ny>=M):
                continue
            if arr[nx][ny] == 1:
                cheeze[nx][ny] += 1
                continue
            if visited[nx][ny]:
                continue
            visited[nx][ny] = 1
            tmp[nx][ny] = -1
            d.append((nx,ny))
    
    for i in range(N):
        for j in range(M):
            if tmp[i][j] == -1:
                arr[i][j] = -1
            if cheeze[i][j] >= 2:
                arr[i][j] = -1

def check_all():
    for i in range(1,N-1):
        for j in range(1,M-1):
            if arr[i][j] == 1:
                return True
    return False

if __name__ == '__main__':
    N, M = map(int,s().split())
    arr = []
    for _ in range(N):
        lst = list(map(int,s().split()))
        arr.append(lst)

    sec = 0
    while check_all():
        sec += 1
        bfs()
    print(sec)
