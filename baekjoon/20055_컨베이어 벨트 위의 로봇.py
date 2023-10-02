# https://www.acmicpc.net/problem/20055
from sys import stdin
from collections import deque
s = stdin.readline
N, K = map(int,s().split())
d = deque(list(map(int,s().split())))
robot = deque([0 for _ in range(N)])

def rotate():
    d.appendleft(d.pop())
    robot.pop()
    robot.appendleft(0)

def move():

    for idx in range(len(robot)-1,-1,-1):
        if not robot[idx]:
            continue
        
        next_move = idx+1
        if next_move >= N:
            robot[idx] = 0
            continue

        if robot[next_move] == 1:
            continue
        
        if d[next_move] < 1:
            continue

        robot[idx] = 0
        robot[next_move] = 1
        d[next_move] -= 1

def up():
    if d[0] >= 1 and robot[0] == 0:
        robot[0] = 1
        d[0] -= 1
        
def check():
    cnt = 0
    for i in d:
        cnt+=i==0
    return cnt >= K

state = 0
while True:
    state += 1
    rotate()
    move()
    up()
    if check():
        break
    
print(state)