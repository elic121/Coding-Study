# https://www.acmicpc.net/problem/3190
from sys import stdin
from collections import deque
s = stdin.readline
N = int(s())
arr = [[0 for _ in range(N+1)] for _ in range(N+1)]

apple_num = int(s())
apple_pos = []
for _ in range(apple_num):
    r,c = map(int,s().split())
    arr[r][c] = 1
    apple_pos.append((r,c))

turn_num = int(s())
turn_pos = {}
for _ in range(turn_num):
    t,d = s().split()
    turn_pos[int(t)] = d

# 오위왼아
dx = [0,-1,0,1]
dy = [1,0,-1,0]

time = 0
move = deque()
move.append((1,1))
now_x, now_y = 1, 1
arr[1][1] = 2
now_d = 0
while True:

    time += 1

    x, y = dx[now_d], dy[now_d]
    nx, ny = now_x + x, now_y + y
    
    if (nx<=0 or nx>N) or (ny<=0 or ny>N):
        break

    # meet snake
    if arr[nx][ny] == 2:
        break

    # meet apple
    if arr[nx][ny] == 1:
        move.append((nx,ny))
        arr[nx][ny] = 2

    # meet nothing
    if arr[nx][ny] == 0:
        arr[nx][ny] = 2
        d_x,d_y = move.popleft()
        move.append((nx,ny))
        arr[d_x][d_y] = 0
    
    if time in turn_pos:
        direction = turn_pos[time]
        
        if direction == 'L':
            now_d = (now_d+1) % 4
        if direction == 'D':
            now_d = (now_d-1) % 4
    
    now_x, now_y = nx, ny

print(time)
