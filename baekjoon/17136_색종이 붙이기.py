# https://www.acmicpc.net/problem/17136
from sys import stdin

M = 100_000_000_9


def check(x, y, c):
    for row in range(x, x + c):
        for col in range(y, y + c):
            if arr[row][col] != 1:
                return False
    return True


def backtracking(x, y, cnt):
    global M, arr, G

    if x >= 10:
        M = min(cnt, M)
        return
    if y >= 10:
        backtracking(x + 1, 0, cnt)
        return

    if arr[x][y] != 1:
        backtracking(x, y + 1, cnt)
        return

    for c in range(1, 6):
        if x + c - 1 >= 10 or y + c - 1 >= 10 or G[c - 1] == 0:
            continue

        if not check(x, y, c):
            break

        for i in range(x, x + c):
            for j in range(y, y + c):
                arr[i][j] = 0
        G[c - 1] -= 1
        backtracking(x, y + c, cnt + 1)
        G[c - 1] += 1
        for i in range(x, x + c):
            for j in range(y, y + c):
                arr[i][j] = 1
    return


if __name__ == "__main__":
    arr = []
    G = [5, 5, 5, 5, 5]
    for i in range(10):
        lst = list(map(int, input().split()))
        arr.append(lst)

    backtracking(0, 0, 0)
    print([M, -1][M == 100_000_000_9])
