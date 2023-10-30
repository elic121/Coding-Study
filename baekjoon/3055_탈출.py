# https://www.acmicpc.net/problem/3055
from sys import stdin

s = stdin.readline

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]


def condition(x, y):
    if (x < 0 or x >= R) or (y < 0 or y >= C):
        return False
    return True


def estimate_water():
    tmp = [[0 for _ in range(C)] for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if arr[i][j] == "*":
                for X, Y in zip(dx, dy):
                    nx, ny = i + X, j + Y
                    if not condition(nx, ny):
                        continue
                    if arr[nx][ny] == "D":
                        continue
                    if arr[nx][ny] == "X":
                        continue
                    if arr[nx][ny] == "*":
                        continue
                    tmp[nx][ny] = "*"
    return tmp


def check():
    for i in range(R):
        for j in range(C):
            val = arr[i][j]
            if (
                val != "X"
                and val != "D"
                and val != "."
                and val != "S"
                and val != "*"
                and val >= 0
            ):
                return True
    return False


if __name__ == "__main__":
    R, C = map(int, s().split())
    arr = []
    posS = ()
    posD = ()
    for i in range(R):
        lst = list(s().strip())
        for j, val in enumerate(lst):
            if val == "D":
                posD = (i, j)
            if val == "S":
                posS = (i, j)
        arr.append(lst)

    if posD == posS:
        print(0)
        exit()

    arr[posS[0]][posS[1]] = 0
    cnt = 0
    while True:
        cnt += 1
        for i in range(R):
            for j in range(C):
                if arr[i][j] == cnt - 1:
                    for X, Y in zip(dx, dy):
                        nx, ny = i + X, j + Y
                        if not condition(nx, ny):
                            continue
                        if arr[nx][ny] == "X":
                            continue
                        if arr[nx][ny] == "S":
                            continue
                        if arr[nx][ny] == "D":
                            print(cnt)
                            exit()
                        arr[nx][ny] = cnt

        notValid = estimate_water()
        for i in range(R):
            for j in range(C):
                if notValid[i][j] == "*":
                    arr[i][j] = "*"

        if not check():
            print("KAKTUS")
            exit()
