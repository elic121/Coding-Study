# 음료수 얼려먹기
import sys

s = sys.stdin.readline

I = [[0, 0, 1, 1, 0], [0, 0, 0, 1, 1], [1, 1, 1, 1, 1], [0, 0, 0, 0, 0]]

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def dfs(x, y):
    xconst = x < 0 or x >= 4
    yconst = y < 0 or y >= 5
    if xconst or yconst:
        return False
    if I[x][y] == 0:
        I[x][y] = 1
        for X, Y in zip(dx, dy):
            dfs(x + X, y + Y)
        return True
    return False


cnt = 0
for i in range(4):
    for j in range(5):
        if dfs(i, j):
            cnt += 1
print(cnt)

"""
dfs 방식으로 풀이
1. 범위를 벗어나는 x,y 좌표는 거름
2. 범위를 벗어나지 않고 현재 상태가 0인 경우
    2-1. 현재 상태를 1로 고침
    2-2. 상하좌우 좌표에 대해 재귀적으로 다시 탐색
3. 독립된 공간의 개수만 탐색 완료
"""
