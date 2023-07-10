# https://www.acmicpc.net/problem/7562
import sys
from collections import deque
s = sys.stdin.readline
N = int(s())

dx = [1,1,2,2,-1,-1,-2,-2]
dy = [2,-2,1,-1,2,-2,1,-1]
def bfs(size: int, pos: tuple[int, int], des: tuple[int, int]) -> int:
    if pos == des:
        return 0
    
    arr = [[0 for _ in range(size)] for _ in range(size)]
    Q = deque()
    Q.append(pos)
    while Q:
        x, y = Q.popleft()
        
        for X,Y in zip(dx,dy):
            nx, ny = x+X, y+Y

            if (nx<0 or nx>=size) or (ny<0 or ny>=size):
                continue
            
            if arr[nx][ny] >= 1:
                continue

            if arr[nx][ny] == 0:
                arr[nx][ny] = arr[x][y] + 1
                Q.append((nx,ny))
                
    return arr[des[0]][des[1]]


for _ in range(N):
    S = int(s())
    P = tuple(map(int, s().split()))
    D = tuple(map(int, s().split()))
    print(bfs(S, P, D))
