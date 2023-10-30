# https://www.acmicpc.net/problem/21611
from sys import stdin

s = stdin.readline
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

DX = [0, 1, 0, -1]
DY = [-1, 0, 1, 0]

N, M = map(int, s().split())
arr = []
for _ in range(N):
    lst = list(map(int, s().split()))
    arr.append(lst)

DS = []
for _ in range(M):
    D, S = map(int, s().split())
    DS.append((D, S))


def p(arr):
    for i in arr:
        print(*i)


def ice(x, y, d, s):
    for i in range(1, s + 1):
        nx, ny = x + dx[d - 1] * i, y + dy[d - 1] * i
        if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
            break
        arr[nx][ny] = 0


def explosion(posx, posy):
    ONE, TWO, THR = 0, 0, 0

    result = []
    stack = []
    pos = []

    direction = 0
    count = 0
    END = False
    CHANGE = False

    while True:
        IT = 1 + count // 2
        for i in range(1, IT + 1):
            nx, ny = posx + i * DX[direction], posy + i * DY[direction]
            if ny == -1:
                END = True
                break
            val = arr[nx][ny]

            if val == 0:
                continue

            if not stack:
                stack.append(val)
                pos.append((nx, ny))

            else:
                if stack[-1] != val:
                    if len(stack) < 4:
                        for tmp in stack:
                            result.append(tmp)
                        stack.clear()

                    else:
                        CHANGE = True
                        if stack[-1] == 1:
                            ONE += len(stack)
                        if stack[-1] == 2:
                            TWO += len(stack)
                        if stack[-1] == 3:
                            THR += len(stack)
                        stack.clear()
                        for x, y in pos:
                            arr[x][y] = 0

                    pos.clear()
                    stack.append(val)
                    pos.append((nx, ny))

                else:
                    stack.append(val)
                    pos.append((nx, ny))
        if END:
            break

        posx, posy = posx + IT * DX[direction], posy + IT * DY[direction]
        count += 1
        direction = (direction + 1) % 4

    if stack and len(stack) >= 4:
        if stack[-1] == 1:
            ONE += len(stack)
        if stack[-1] == 2:
            TWO += len(stack)
        if stack[-1] == 3:
            THR += len(stack)
        for x, y in pos:
            arr[x][y] = 0
        CHANGE = True

    return ONE, TWO, THR, CHANGE


def refactoring(posx, posy, arr):
    lst = []
    stack = []
    direction = 0
    count = 0
    END = False

    while True:
        IT = 1 + count // 2
        for i in range(1, IT + 1):
            nx, ny = posx + i * DX[direction], posy + i * DY[direction]
            if ny == -1:
                END = True
                break

            val = arr[nx][ny]

            if val == 0:
                continue

            if not stack:
                stack.append(val)

            else:
                if stack[-1] != val:
                    lst.append(len(stack))
                    lst.append(stack[-1])
                    stack.clear()

                stack.append(val)

            if len(lst) > N * N - 1:
                END = True
                break

        if END:
            break

        posx, posy = posx + IT * DX[direction], posy + IT * DY[direction]
        count += 1
        direction = (direction + 1) % 4

    if stack:
        lst.append(len(stack))
        lst.append(stack[-1])

    return lst


def grouping(posx, posy, arr):
    exam = refactoring(posx, posy, arr)

    tmp = [[0 for _ in range(N)] for _ in range(N)]

    direction = 0
    count = 0
    END = False

    C = 0
    while True:
        IT = 1 + count // 2
        for i in range(1, IT + 1):
            nx, ny = posx + i * DX[direction], posy + i * DY[direction]
            if ny == -1:
                END = True
                break

            if C >= len(exam):
                END = True
                break

            tmp[nx][ny] = exam[C]
            C += 1

        if END:
            break

        posx, posy = posx + IT * DX[direction], posy + IT * DY[direction]
        count += 1
        direction = (direction + 1) % 4

    return tmp


posx, posy = N // 2, N // 2
one, two, thr = 0, 0, 0
for idx in range(M):
    ice(posx, posy, DS[idx][0], DS[idx][1])

    while True:
        x, y, z, C = explosion(posx, posy)
        if C == False:
            break
        one += x
        two += y
        thr += z

    arr = grouping(posx, posy, arr)

    if idx == M - 1:
        break

print(one + 2 * two + 3 * thr)
