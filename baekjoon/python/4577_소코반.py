from sys import stdin
s = stdin.readline


def move(c):
    global x, y, arr
    dx, dy = where_to_go(c)

    nx, ny = x + dx, y + dy

    if not is_possible(nx, ny):
        return
    if arr[nx][ny] == '#':
        return

    if arr[nx][ny] == 'b' or arr[nx][ny] == 'B':
        bx, by = nx + dx, ny + dy
        if not is_possible(bx, by):
            return
        if arr[bx][by] == '#':
            return
        if arr[bx][by] == 'b' or arr[bx][by] == 'B':
            return

        arr[bx][by] = 'B' if arr[bx][by] == '+' else 'b'
        arr[nx][ny] = 'w' if arr[nx][ny] == 'b' else 'W'

    else:
        arr[nx][ny] = 'W' if arr[nx][ny] == '+' else 'w'

    arr[x][y] = '+' if arr[x][y] == 'W' else '.'
    x, y = nx, ny
    return


def check():
    cnt = 0
    for i in range(R):
        for j in range(C):
            if arr[i][j] == '+' or arr[i][j] == 'W':
                return False
            if arr[i][j] == 'B':
                cnt += 1
    return cnt == goal


def is_possible(x, y):
    return 0 <= x < R and 0 <= y < C


def where_to_go(c):
    if c == 'U':
        return -1, 0
    if c == 'L':
        return 0, -1
    if c == 'R':
        return 0, 1
    if c == 'D':
        return 1, 0


if __name__ == "__main__":
    cnt = 1
    while True:
        R, C = map(int, input().split())
        if R == 0 and C == 0:
            break
        x, y = -1, -1
        goal = 0
        arr = []
        for i in range(R):
            lst = list(s().strip())
            for j, val in enumerate(lst):
                if val == 'w' or val == 'W':
                    x = i
                    y = j
                    if (val == 'W'):
                        goal += 1
                elif (val == 'B' or val == '+'):
                    goal += 1
            arr.append(lst)

        scr = "incomplete"
        for command in s().strip():
            move(command)
            if check():
                scr = "complete"
                break

        print(f"Game {cnt}: {scr}")
        for i in range(R):
            print(''.join(arr[i]))

        cnt += 1
