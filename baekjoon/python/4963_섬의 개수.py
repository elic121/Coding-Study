# https://www.acmicpc.net/problem/4963
import sys

sys.setrecursionlimit(10**6)
s = sys.stdin.readline
dx = [1, 1, 0, -1, -1, -1, 0, 1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]


def dfs(x, y, xSize, ySize):
    if (x < 0 or x >= ySize) or (y < 0 or y >= xSize):
        return False
    if arr[x][y] == 1:
        arr[x][y] = 0
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            dfs(nx, ny, xSize, ySize)
        return True
    return False


c = []
while True:
    xSize, ySize = map(int, s().split())
    if not (xSize and ySize):
        break

    arr = []
    for i in range(ySize):
        arr.append(list(map(int, s().split())))

    cnt = 0
    for i in range(ySize):
        for j in range(xSize):
            if dfs(i, j, xSize, ySize):
                cnt += 1
    c.append(cnt)

print("\n".join(map(str, c)))
