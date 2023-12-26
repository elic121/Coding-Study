def find_pos(n):
    x, y = 1, 1
    cnt, num = 1, 1

    while True:
        tx, ty = x, y
        for i in range(cnt):
            nx, ny = x + i, y - i
            if num == n:
                return nx, ny
            num += 1
        cnt += 1
        x, y = tx, ty + 1


def find_num(qx, qy):
    x, y = 1, 1
    cnt, num = 1, 1

    while True:
        tx, ty = x, y
        for i in range(cnt):
            nx, ny = x + i, y - i
            if nx == qx and ny == qy:
                return num
            num += 1
        cnt += 1
        x, y = tx, ty + 1


if __name__ == '__main__':
    arr = [[0 for _ in range(301)] for _ in range(301)]
    x, y = 1, 1
    cnt, num = 1, 1

    while cnt <= 300:
        tx, ty = x, y
        for i in range(cnt):
            nx, ny = x + i, y - i
            arr[nx][ny] = num
            num += 1
        cnt += 1
        x, y = tx, ty + 1

    T = int(input())
    for tc in range(1, T + 1):
        p, q = map(int, input().split())
        x1, y1 = find_pos(p)
        x2, y2 = find_pos(q)
        x3, y3 = x1 + x2, y1 + y2
        sol = find_num(x3, y3)

        print(f"#{tc} {sol}")
