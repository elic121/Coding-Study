# https://www.acmicpc.net/problem/19237
from sys import stdin

s = stdin.readline


def clear():
    for i in range(N):
        for j in range(N):
            if smell[i][j][0] == 0:
                continue
            smell[i][j][0] -= 1


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

N, M, K = map(int, s().split())
arr = []
S_pos = {}
smell = [[[0, 0] for _ in range(N)] for _ in range(N)]

for i in range(N):
    lst = list(map(int, s().split()))
    for j, x in enumerate(lst):
        if x != 0:
            S_pos[x] = [i, j, 0]
            smell[i][j][0] = K
            smell[i][j][1] = x
    arr.append(lst)

for idx, i in enumerate(map(int, s().split())):
    S_pos[idx + 1][2] = i

pri = {key: [] for key, _ in enumerate(range(M), 1)}
for i in range(1, M + 1):
    for j in range(4):
        pri[i].append(list(map(int, s().split())))

cnt = 0
while True:
    cnt += 1
    if cnt > 1000:
        print(-1)
        break

    future_move = []
    for key, val in S_pos.items():
        x, y, d = val
        possible_pos = []
        for idx, X, Y in zip(range(1, 5), dx, dy):
            nx, ny = x + X, y + Y
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                continue
            if smell[nx][ny][0] != 0:
                continue
            possible_pos.append((nx, ny, idx, key))
        if not possible_pos:
            for idx, X, Y in zip(range(1, 5), dx, dy):
                nx, ny = x + X, y + Y
                if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                    continue
                if smell[nx][ny][0] == 0:
                    continue
                if smell[nx][ny][1] == key:
                    possible_pos.append((nx, ny, idx, key))

        if len(possible_pos) == 1:
            go_to_pos = possible_pos[0]
        else:
            priority = pri[key][d - 1]
            IDX = 0
            for i in priority:
                FIND = False
                for k, V in enumerate(possible_pos):
                    nx, ny, idx, key = V
                    if idx == i:
                        FIND = True
                        IDX = k
                        break
                if FIND:
                    break
            go_to_pos = possible_pos[IDX]

        future_move.append(go_to_pos)

    clear()

    tmp_pos = {}
    for FM in sorted(future_move, key=lambda x: x[3]):
        nx, ny, idx, key = FM

        if smell[nx][ny][0] == K:
            continue
        smell[nx][ny][0] = K
        smell[nx][ny][1] = key
        tmp_pos[key] = [nx, ny, idx]

    S_pos = tmp_pos

    if len(S_pos.keys()) == 1:
        print(cnt)
        break
