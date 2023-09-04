import sys

s = sys.stdin.readline
N = int(s())


def move(drc):
    if drc == 0:
        return (0, 1)
    if drc == 1:
        return (1, 0)
    if drc == 2:
        return (0, -1)
    if drc == 3:
        return (-1, 0)


def snail(num):
    arr = [[0 for _ in range(num)] for _ in range(num)]
    drc = 0
    x, y = 0, 0
    val = 1

    for _ in range(num * num):
        arr[x][y] = val

        dx, dy = move(drc)
        nx, ny = x + dx, y + dy
        if (nx < 0 or nx >= num) or (ny < 0 or ny >= num) or (arr[nx][ny] != 0):
            drc += 1
            drc %= 4
            dx, dy = move(drc)
            x, y = x + dx, y + dy
        else:
            x, y = nx, ny

        val += 1

    return arr


for i in range(N):
    num = int(s())

    print(f"#{i+1}")
    for i in snail(num):
        print(*i)
