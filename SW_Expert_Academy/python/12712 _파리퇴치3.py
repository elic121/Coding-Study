dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

dx1 = [1, 1, -1, -1]
dy1 = [1, -1, 1, -1]


def plus(i: int, j: int) -> None:
    global MAX
    cnt = arr[i][j]
    for X, Y in zip(dx, dy):
        for c in range(1, M):
            x = i + c * X
            if x < 0 or x >= N:
                continue
            y = j + c * Y
            if y < 0 or y >= N:
                continue
            cnt += arr[x][y]
    MAX = max(cnt, MAX)


def cross(i: int, j: int) -> None:
    global MAX
    cnt = arr[i][j]
    for X, Y in zip(dx1, dy1):
        for c in range(1, M):
            x = i + c * X
            if x < 0 or x >= N:
                continue
            y = j + c * Y
            if y < 0 or y >= N:
                continue
            cnt += arr[x][y]
    MAX = max(cnt, MAX)


T = int(input())
for tc in range(1, T + 1):
    N, M = map(int, input().split())
    MAX = 0
    arr = []
    for _ in range(N):
        arr.append(list(map(int, input().split())))

    for i in range(N):
        for j in range(N):
            plus(i, j)
            cross(i, j)

    print(f"#{tc} {MAX}")
