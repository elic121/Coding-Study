# https://www.acmicpc.net/problem/14503
import sys

s = sys.stdin.readline

N, M = map(int, s().split())
D = {0: (-1, 0), 1: (0, 1), 2: (1, 0), 3: (0, -1)}

x, y, direction = map(int, s().split())

dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
arr = [list(map(int, s().split())) for _ in range(N)]
cnt = 0

# 0인 경우 청소되지 않은 빈 칸
# 1인 경우 벽


def robot():
    global x, y
    global direction
    global cnt

    while True:
        # 청소 안한 구역일 경우
        if arr[x][y] == 0:
            # 청소표시
            arr[x][y] = -1
            cnt += 1

        # 청소되지 않은 빈 칸 확인
        # True = 모두 청소됨
        # False = 청소되지 않은 칸 있음
        allMinusOne = True
        for X, Y in zip(dx, dy):
            nx, ny = x + X, y + Y
            # if (nx<0 or nx>=N) or (ny<0 or nx>=M):
            #     continue
            if arr[nx][ny] == 0:
                allMinusOne = False
                break

        # 청소할 칸이 상하좌우로 없는 경우 = 모두 청소된 경우
        if allMinusOne:
            newX, newY = x - D[direction][0], y - D[direction][1]

            # if (newX<0 or newX>=N) or (newY<0 or newY>=M):
            #     continue

            # 바라보는 방향을 유지한 채로
            # 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
            if arr[newX][newY] != 1:
                x, y = newX, newY
                continue
            # 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
            else:
                return cnt

        # 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
        else:
            # 반시계 방향으로 90도 회전한다.
            direction -= 1
            direction %= 4

            # 바라보는 방향을 기준으로 앞쪽 칸이
            # 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
            bx, by = x + D[direction][0], y + D[direction][1]

            # if (bx<0 or bx>=N) or (by<0 or by>=M):
            #     continue

            if arr[bx][by] == 0:
                x, y = bx, by
                continue


print(robot())
