def condition(x, y):
    if (x < 0 or x >= N) or (y < 0 or y >= N):
        return False
    return True


dx = [-1, -1, -1, 0, 0, 1, 1, 1]
dy = [-1, 0, 1, -1, 1, -1, 0, 1]


def check(x, y, i):
    find = -1
    for m in range(1, N):
        nx, ny = x + dx[i] * m, y + dy[i] * m
        if not condition(nx, ny):
            return False
        if arr[nx][ny] == 0:
            return False
        if arr[nx][ny] == arr[x][y]:
            find = m
            break
    for m in range(1, find):
        nx, ny = x + dx[i] * m, y + dy[i] * m
        arr[nx][ny] = arr[x][y]

    return [False, True][find != -1]

def sol():
    w, b = 0, 0
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 1:
                b += 1
            if arr[i][j] == 2:
                w += 1
    return b, w

def init_game():
    tmp = [[0 for _ in range(N)] for _ in range(N)]
    tmp[N // 2][N // 2] = 2
    tmp[N // 2 - 1][N // 2 - 1] = 2
    tmp[N // 2 - 1][N // 2] = 1
    tmp[N // 2][N // 2 - 1] = 1

    return tmp


if __name__ == "__main__":
    T = int(input())
    for tc in range(1, T + 1):
        N, M = map(int, input().split())

        arr = init_game()

        for _ in range(M):
            y, x, s = map(int, input().split())
            cnt = 0
            arr[x-1][y-1] = s

            for i in range(8):
                cnt += check(x-1, y-1, i)

            if cnt == 0:
                arr[x-1][y-1] = 0

        B, W = sol()
        print(f"#{tc} {B} {W}")