dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def condition(x, y):
    return 0 <= x < H and 0 <= y < W


def p():
    global pos, stt
    x, y = stt
    nx, ny = x + dx[pos], y + dy[pos]
    if not condition(nx, ny):
        return
    if arr[nx][ny] == '.':
        stt = (nx, ny)
        arr[nx][ny] = arr[x][y]
        arr[x][y] = '.'
    return


def s():
    global stt, pos
    x, y = stt
    nx, ny = x + dx[pos], y + dy[pos]
    while condition(nx, ny):
        if arr[nx][ny] == '*':
            arr[nx][ny] = '.'
            break
        if arr[nx][ny] == '#':
            break
        nx, ny = nx + dx[pos], ny + dy[pos]
    return


if __name__ == '__main__':
    T = int(input())

    for tc in range(1, T + 1):
        H, W = map(int, input().split())
        arr = []
        stt = (0, 0)
        pos = 0

        for i in range(H):
            lst = list(input().strip())
            for j, val in enumerate(lst):
                if val in ('<', '^', '>', 'v'):
                    stt = (i, j)
                    if val == '>':
                        pos = 0
                    if val == 'v':
                        pos = 1
                    if val == '<':
                        pos = 2
                    if val == '^':
                        pos = 3
            arr.append(lst)
        N = int(input())
        for command in input().strip():
            x, y = stt
            if command == 'S':
                s()
            else:
                if command == 'R':
                    arr[x][y] = '>'
                    pos = 0
                    p()
                if command == 'D':
                    arr[x][y] = 'v'
                    pos = 1
                    p()
                if command == 'L':
                    arr[x][y] = '<'
                    pos = 2
                    p()
                if command == 'U':
                    arr[x][y] = '^'
                    pos = 3
                    p()

        print(f'#{tc}', ''.join(arr[0]))
        for i in range(1, H):
            print(''.join(arr[i]))
