# https://www.acmicpc.net/problem/13460
from sys import stdin
from collections import deque
s = stdin.readline

N, M = map(int, s().split())
arr = []
bx, by = 0, 0
rx, ry = 0, 0
ox, oy = 0, 0

dx = [0,-1,0,1]
dy = [1,0,-1,0]

for i in range(N):
    lst = list(s().rstrip())
    for j, val in enumerate(lst):
        if val == 'B':
            bx, by = i, j
        if val == 'R':
            rx, ry = i, j
        if val == 'O':
            ox , oy = i,j
    arr.append(lst)

visited = [[[[0 for _ in range(M)] for _ in range(N)]
            for _ in range(M)] for _ in range(N)]

def move(x, y, dx, dy):
    distance = 0
    while arr[x+dx][y+dy] !='#' and arr[x][y] !='O':
        x += dx
        y += dy
        distance += 1
    
    return x, y, distance

def bfs(rx,ry,bx,by):
    d = deque()
    d.append((rx,ry,bx,by,1))
    visited[rx][ry][bx][by] = 1

    while d:
        RX, RY, BX, BY, cnt = d.popleft()
        if cnt == 11:
            return -1            
        for X, Y in zip(dx,dy):
            redx, redy, redD = move(RX,RY,X,Y)
            blux, bluy, bluD = move(BX,BY,X,Y)

            checkRED = (redx == ox and redy == oy)
            checkBLU = (blux == ox and bluy == oy)

            if not checkBLU:
                if checkRED:
                    return cnt

                if redx == blux and redy == bluy:
                    if redD > bluD:
                        redx -= X
                        redy -= Y
                    elif redD < bluD:
                        blux -= X
                        bluy -= Y
                
                if not visited[redx][redy][blux][bluy]:
                    visited[redx][redy][blux][bluy] = 1
                    d.append((redx,redy,blux,bluy,cnt+1))

    return -1

print(bfs(rx,ry,bx,by))