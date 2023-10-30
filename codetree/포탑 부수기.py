from collections import deque

# 우하좌상
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

mx = [-1, -1, -1, 0, 0, 1, 1, 1]
my = [-1, 0, 1, -1, 1, -1, 0, 1]


def check():
    cnt = 0
    for i in range(N):
        for j in range(M):
            if arr[i][j] > 0:
                cnt += 1
    if cnt < 2:
        return True
    else:
        return False


def picks():
    tmp = []
    for i in range(N):
        for j in range(M):
            val = arr[i][j]
            if val <= 0:
                continue
            tmp.append((val, i, j))

    tmp.sort(key=lambda o: (o[0], -recent[o[1]][o[2]], -(o[1] + o[2]), -o[2]))
    return tmp[0]


def picka():
    tmp = []
    for i in range(N):
        for j in range(M):
            val = arr[i][j]
            if val <= 0:
                continue
            tmp.append((val, i, j))

    tmp.sort(key=lambda o: (-o[0], recent[o[1]][o[2]], (o[1] + o[2]), o[2]))
    return tmp[0]


def bfs(sx, sy, ax, ay):
    visited = [[0 for _ in range(M)] for _ in range(N)]
    prev_node = [[None for _ in range(M)] for _ in range(N)]
    d = deque()
    d.append((sx, sy))
    visited[sx][sy] = 1

    while d:
        x, y = d.popleft()

        if x == ax and y == ay:
            path = []
            tmp = (x, y)
            while tmp is not None:
                path.append(tmp)
                tmp = prev_node[tmp[0]][tmp[1]]
            return path[::-1]

        for X, Y in zip(dx, dy):
            nx, ny = (x + X) % N, (y + Y) % M
            if arr[nx][ny] <= 0:
                continue
            if visited[nx][ny]:
                continue
            visited[nx][ny] = 1
            prev_node[nx][ny] = (x, y)
            d.append((nx, ny))

    return []


def broken():
    for i in range(N):
        for j in range(M):
            if arr[i][j] < 0:
                arr[i][j] = 0


def repair():
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                continue
            if direct_attack[i][j] >= 1:
                continue
            arr[i][j] += 1


if __name__ == "__main__":
    N, M, K = map(int, input().split())
    PLUS = N + M
    arr = []

    for _ in range(N):
        lst = list(map(int, input().split()))
        arr.append(lst)

    recent = [[0 for _ in range(M)] for _ in range(N)]
    for t in range(1, K + 1):
        s = picks()
        a = picka()
        _, sx, sy = s
        _, ax, ay = a

        path = bfs(sx, sy, ax, ay)

        arr[sx][sy] += PLUS
        recent[sx][sy] = t

        damage = arr[sx][sy] // 2

        direct_attack = [[0 for _ in range(M)] for _ in range(N)]
        direct_attack[sx][sy] = 1
        direct_attack[ax][ay] = 1

        if path:
            for idx in range(1, len(path) - 1):
                x, y = path[idx]
                arr[x][y] -= damage
                direct_attack[x][y] = 1
            arr[ax][ay] -= arr[sx][sy]

        else:
            for X, Y in zip(mx, my):
                nx, ny = ax + X, ay + Y
                nx, ny = nx % N, ny % M
                if nx == sx and ny == sy:
                    continue
                if arr[nx][ny] <= 0:
                    continue
                arr[nx][ny] -= damage
                direct_attack[nx][ny] = 1
            arr[ax][ay] -= arr[sx][sy]

        broken()
        repair()

        if check():
            break

    MAX = 0
    for i in range(N):
        for j in range(M):
            MAX = max(MAX, arr[i][j])
    print(MAX)
