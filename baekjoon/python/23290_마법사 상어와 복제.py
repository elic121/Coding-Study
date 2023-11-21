# https://www.acmicpc.net/problem/23290
from sys import stdin

s = stdin.readline
M, S = map(int, s().split())
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
arr = [[[] for _ in range(4)] for _ in range(4)]
smell = [[0 for _ in range(4)] for _ in range(4)]
dup = [[[] for _ in range(4)] for _ in range(4)]
for _ in range(M):
    fx, fy, d = map(int, s().split())
    arr[fx - 1][fy - 1].append(d)
sx, sy = map(int, s().split())
sx, sy = sx - 1, sy - 1


def magic():
    global arr, dup
    for i in range(4):
        for j in range(4):
            if arr[i][j]:
                for D in arr[i][j]:
                    dup[i][j].append(D)


def moveFish():
    global arr, sx, sy
    tmp = [[[] for _ in range(4)] for _ in range(4)]
    for i in range(4):
        for j in range(4):
            # if i==sx and j==sy:
            #     continue
            if arr[i][j]:
                for D in arr[i][j]:
                    idx = D - 1
                    check = False
                    cnt = 0
                    while True:
                        cnt += 1
                        if cnt == 9:
                            break
                        nx, ny = i + dx[idx], j + dy[idx]
                        if (nx < 0 or nx >= 4) or (ny < 0 or ny >= 4):
                            idx = (idx - 1) % 8
                            continue
                        if smell[nx][ny] > 0:
                            idx = (idx - 1) % 8
                            continue
                        if nx == sx and ny == sy:
                            idx = (idx - 1) % 8
                            continue
                        tmp[nx][ny].append(idx + 1)
                        check = True
                        break

                    if not check:
                        tmp[i][j].append(D)

    arr = tmp


max_food = {}
Max = -1
SX = [-1, 0, 1, 0]
SY = [0, -1, 0, 1]


def backtracking(tmp):
    global max_food, SX, SY, Max, sx, sy, arr
    if len(tmp) == 3:
        x, y = sx, sy
        food = 0
        cache = [[0 for _ in range(4)] for _ in range(4)]
        for idx in tmp:
            nx, ny = x + SX[idx - 1], y + SY[idx - 1]
            if (nx < 0 or nx >= 4) or (ny < 0 or ny >= 4):
                return
            if cache[nx][ny] == 1:
                pass
            else:
                food += len(arr[nx][ny])
                cache[nx][ny] = 1
            x, y = nx, ny
        tmp2 = [i for i in tmp]
        if food >= Max:
            Max = food
            if food in max_food.keys():
                max_food[food].append(tmp2)
            else:
                max_food[food] = [tmp2]

        return

    for i in range(1, 5):
        tmp.append(i)
        backtracking(tmp)
        tmp.pop()


die = [[0 for _ in range(4)] for _ in range(4)]


def moveShark():
    global max_food, SX, SY, sx, sy, die, Max
    backtracking([])
    maxkey = max(max_food.keys())
    move = max_food[maxkey][0]
    Max = 0
    max_food.clear()

    x, y = sx, sy
    for idx in move:
        nx, ny = x + SX[idx - 1], y + SY[idx - 1]
        if arr[nx][ny]:
            smell[nx][ny] = 2
            die[nx][ny] = 1
            arr[nx][ny].clear()
        x, y = nx, ny
    sx, sy = x, y


def checkSmell():
    global smell, die
    for i in range(4):
        for j in range(4):
            if die[i][j]:
                continue
            if smell[i][j] > 0:
                smell[i][j] -= 1
    die = [[0 for _ in range(4)] for _ in range(4)]


def duplication():
    global sx, sy, arr, dup
    for row in range(4):
        for col in range(4):
            if dup[row][col]:
                for D in dup[row][col]:
                    arr[row][col].append(D)
    dup = [[[] for _ in range(4)] for _ in range(4)]


for cs in range(S):
    magic()
    moveFish()
    moveShark()
    checkSmell()
    duplication()

cnt = 0
for i in range(4):
    for j in range(4):
        cnt += len(arr[i][j])
print(cnt)
