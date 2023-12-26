from collections import deque

dx = [0, -1, 0, 1, 0]
dy = [0, 0, 1, 0, -1]


def priority(A, B, AP):
    MAX = 0
    if A and B:
        for a in A:
            for b in B:
                if a == b:
                    MAX = max(MAX, AP[a][-1])
                else:
                    MAX = max(MAX, AP[a][-1] + AP[b][-1])
    else:
        if A:
            for a in A:
                MAX = max(MAX, AP[a][-1])
        if B:
            for b in B:
                MAX = max(MAX, AP[b][-1])

    return MAX


def R(x, y, C):
    tmp = [[0 for _ in range(11)] for _ in range(11)]
    visited = [[0 for _ in range(11)] for _ in range(11)]
    d = deque()
    d.append((x, y))
    visited[x][y] = 1
    pos = [(x, y)]
    while d:
        i, j = d.popleft()
        if tmp[i][j] + 1 > C:
            continue
        for X, Y in zip(dx[1:], dy[1:]):
            nx, ny = i + X, j + Y
            if (nx < 1 or nx > 10) or (ny < 1 or ny > 10):
                continue
            if visited[nx][ny] == 1:
                continue
            if tmp[nx][ny] > 0:
                continue
            tmp[nx][ny] = tmp[i][j] + 1
            visited[nx][ny] = 1
            pos.append((nx, ny))
            d.append((nx, ny))

    return pos


def bfs(AP, a, b, M):
    arr = [[[] for _ in range(10 + 1)] for _ in range(10 + 1)]
    for key, val in AP.items():
        x, y, C, P = val
        for i, j in R(x, y, C):
            arr[i][j].append(key)

    aCharge = 0
    ax, ay = 1, 1
    if arr[ax][ay]:
        for idx in arr[ax][ay]:
            aCharge = max(aCharge, AP[idx][-1])

    bCharge = 0
    bx, by = 10, 10
    if arr[bx][by]:
        for idx in arr[bx][by]:
            bCharge = max(bCharge, AP[idx][-1])

    K = 0
    cnt = 0
    for x, y in zip(a, b):
        if cnt == M:
            break
        cnt += 1
        ax, ay = ax + dx[x], ay + dy[x]
        bx, by = bx + dx[y], by + dy[y]

        if (
            (ax < 1 or ax > 10)
            or (ay < 1 or ay > 10)
            or (bx < 1 or bx > 10)
            or (by < 1 or by > 10)
        ):
            ax, ay = ax - dx[x], ay - dy[x]
            bx, by = bx - dx[y], by - dy[y]
            continue

        aChoose = arr[ax][ay]
        bChoose = arr[bx][by]

        if aChoose or bChoose:
            K += priority(aChoose, bChoose, AP)

    return K + aCharge + bCharge


if __name__ == "__main__":
    test_case = int(input())
    for i in range(1, test_case + 1):
        M, A = map(int, input().split())
        a = list(map(int, input().split()))
        b = list(map(int, input().split()))
        AP = {}
        for idx in range(A):
            y, x, C, P = map(int, input().split())
            AP[idx + 1] = (x, y, C, P)
        res = bfs(AP, a, b, M)
        print(f"#{i} {res}")
