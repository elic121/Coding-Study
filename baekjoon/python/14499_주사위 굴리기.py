# https://www.acmicpc.net/problem/14499
from sys import stdin

s = stdin.readline

map_info = []
N, M, x, y, K = map(int, s().split())
for _ in range(N):
    map_info.append(list(map(int, s().split())))

command = list(map(int, s().split()))

dice = [0, 0, 0, 0, 0, 0]
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]


def turn(i):
    global dice
    a, b, c, d, e, f = dice
    if i == 1:
        dice = [d, b, a, f, e, c]
    if i == 2:
        dice = [c, b, f, a, e, d]
    if i == 3:
        dice = [b, f, c, d, a, e]
    if i == 4:
        dice = [e, a, c, d, f, b]


for i in command:
    nx, ny = dx[i - 1], dy[i - 1]
    tx, ty = x + nx, y + ny

    if (tx < 0 or tx >= N) or (ty < 0 or ty >= M):
        continue

    turn(i)

    if map_info[tx][ty] == 0:
        map_info[tx][ty] = dice[5]
    else:
        dice[5] = map_info[tx][ty]
        map_info[tx][ty] = 0

    print(dice[0])

    x, y = tx, ty
