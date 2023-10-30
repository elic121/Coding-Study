from collections import deque

dx = [-1, 0, 0, 1]
dy = [0, -1, 1, 0]


def check(nx, ny):
    if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
        return False
    if basecamp[nx][ny] < 0:
        return False
    if conv[nx][ny] < 0:
        return False
    return True


def check2(nx, ny, peo):
    if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
        return False
    if basecamp[nx][ny] < 0 and basecamp[nx][ny] != -peo:
        return False
    if conv[nx][ny] < 0:
        return False
    return True


def next_bfs(hx, hy, gx, gy, peo):
    print(f"{hx, hy} {gx, gy}")
    visited = [[0 for _ in range(N)] for _ in range(N)]
    d = deque()
    d.append((gx, gy))
    while d:
        x, y = d.popleft()
        for i, j in zip(dx, dy):
            nx, ny = x + i, y + j
            if not check2(nx, ny, peo):
                continue
            if visited[nx][ny] >= 1:
                continue
            visited[nx][ny] = visited[x][y] + 1
            d.append((nx, ny))

    visited[gx][gy] = 0

    for q in visited:
        print(*q)

    tmp = []
    for idx in range(4):
        nx, ny = hx + dx[idx], hy + dy[idx]
        if not check(nx, ny):
            continue
        val = visited[nx][ny]
        tmp.append((val, idx))
    # print(f"{tmp=}")
    tmp.sort()
    k = tmp[0][1]
    return hx + dx[k], hy + dy[k]


def find_nearest(x, y):
    visited = [[0 for _ in range(N)] for _ in range(N)]
    d = deque()
    d.append((x, y))
    while d:
        x, y = d.popleft()
        for i, j in zip(dx, dy):
            nx, ny = x + i, y + j
            if not check(nx, ny):
                continue
            if visited[nx][ny] >= 1:
                continue
            if basecamp[nx][ny] == 1:
                return nx, ny
            visited[nx][ny] = 1
            d.append((nx, ny))


if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    basecamp = [[0 for _ in range(N)] for _ in range(N)]
    conv = [[0 for _ in range(N)] for _ in range(N)]
    p = {}
    for i in range(N):
        lst = list(map(int, input().split()))
        for j, val in enumerate(lst):
            if val == 1:
                basecamp[i][j] = 1
        arr.append(lst)

    want = {}
    for i in range(M):
        x, y = map(int, input().split())
        conv[x - 1][y - 1] = 1
        want[i + 1] = (x - 1, y - 1)

    sec = 0
    people = 1
    goal = 0
    while True:
        sec += 1
        # print(f"round {sec}!!!!!!!!!!!!!!")

        newc = {}
        newp = {}
        for key, val in p.items():
            i, j = key
            if conv[i][j] < 0:
                continue
            for peo in val:
                val = want[peo]
                # print(f"{val=}")
                mx, my = next_bfs(i, j, val[0], val[1], peo)
                # print(f"{peo}번이 {i,j}에서 {mx,my}로 이동합니다.")

                if mx == val[0] and my == val[1]:
                    # print(f"{peo}번이 {mx,my}로 들어왔습니다.")
                    newc[(mx, my)] = -peo
                    goal += 1

                else:
                    if (mx, my) in newp:
                        newp[(mx, my)].append(peo)
                    else:
                        newp[(mx, my)] = [peo]

                if goal == M:
                    print(sec)
                    exit()

        p = newp

        for key, val in newc.items():
            x, y = key
            conv[x][y] = val

        if people <= M:
            gx, gy = want[people]

            # print(f"{people}번은 {gx,gy}로 가려고 합니다.")
            fx, fy = find_nearest(gx, gy)
            # print(f"{people}번이 베이스캠프로 삼는 곳은 {fx,fy}입니다.")
            basecamp[fx][fy] = -people
            p[(fx, fy)] = [people]

            people += 1

        # print("-> people")
        # for k,v in p.items():
        #     print(f"{k=} {v=}")
        #
        # print("-> conv")
        # for q in conv:
        #     print(*q)
        #
        # print("-> basecamp")
        # for q in basecamp:
        #     print(*q)
        #
        # if sec == 5:
        #     raise
        # breakpoint()

    # print(sec)
