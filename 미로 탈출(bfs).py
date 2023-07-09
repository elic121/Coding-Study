# 미로 탈출
import sys
from collections import deque
s = sys.stdin.readline

N, M = map(int,s().split())
l = []
for i in range(N):
    l.append(list(map(int,s().strip())))
"""
N , M = 5, 6
example = [
    [1,0,1,0,1,0],
    [1,1,1,1,1,1],
    [0,0,0,0,0,1],
    [1,1,1,1,1,1],
    [1,1,1,1,1,1]
]
"""
dx = [0,1,0,-1]
dy = [1,0,-1,0]

def bfs(x,y):
    q = deque()
    q.append((x,y))
    
    while q:
        X, Y = q.popleft()
        for Dx, Dy in zip(dx,dy):
            nx, ny = X+Dx, Y+Dy
            if nx<0 or nx>=N or ny<0 or ny>=M:
                continue
            if l[nx][ny] == 0:
                continue
            if l[nx][ny] == 1:
                l[nx][ny] = l[X][Y] + 1
                q.append((nx,ny)) 
    
    return l[N-1][M-1]

print(bfs(0,0))

"""
BFS를 통해서 해결
1. queue를 이용한다.
2. 시작좌표를 queue에 저장한다.
3. queue에 아무것도 남지 않을 때까지
    3-1. 가장 좌측의 원소부터 꺼냄 (시작좌표는 1인 것이 보장됨)
    3-2. 상하좌우 좌표에 대해 해당 인덱스의 값이 1인 경우
        3-2-1. 중심좌표값에 1을 더한 값으로 초기화
4. N, M 좌표의 값을 return 
"""