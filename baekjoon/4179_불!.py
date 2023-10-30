# https://www.acmicpc.net/problem/4179
from sys import stdin

s = stdin.readline

R, C = map(int, s().strip().split())
arr = []
jPos = []
fPos = []

for i in range(R):
    l = list(s().strip())
    for j, k in enumerate(l):
        if k == "J":
            jPos.append((i, j))
        if k == "F":
            fPos.append((i, j))
    arr.append(l)


def bfs(jPos, fPos):
    i, j = jPos[0]
    arr[i][j] = 1

    while jPos:
        # Jihun
        tmpJ = []
        for jx, jy in jPos:
            if arr[jx][jy] == "F":
                continue

            for X, Y in zip([1, -1, 0, 0], [0, 0, 1, -1]):
                nx, ny = jx + X, jy + Y

                if (nx < 0 or nx >= R) or (ny < 0 or ny >= C):
                    return arr[jx][jy]

                if arr[nx][ny] != ".":
                    continue

                arr[nx][ny] = arr[jx][jy] + 1
                tmpJ.append((nx, ny))
        jPos = tmpJ

        # Fire
        tmp = []
        for fx, fy in fPos:
            for X, Y in zip([1, -1, 0, 0], [0, 0, 1, -1]):
                nx, ny = fx + X, fy + Y
                if (nx < 0 or nx >= R) or (ny < 0 or ny >= C):
                    continue
                if arr[nx][ny] == "#":
                    continue
                if arr[nx][ny] == "F":
                    continue
                arr[nx][ny] = "F"
                tmp.append((nx, ny))
        fPos = tmp

    return "IMPOSSIBLE"


print(bfs(jPos, fPos))


# # https://www.acmicpc.net/problem/4179
# from sys import stdin
# s = stdin.readline

# R, C = map(int, s().strip().split())
# arr,jPos,fPos=[],[],[]

# for i in range(R):
#     l = list(s().strip())
#     for j, k in enumerate(l):
#         if k == 'J':
#             jPos.append((i, j))
#         if k == 'F':
#             fPos.append((i, j))
#     arr.append(l)

# dx,dy=[1, -1, 0, 0], [0, 0, 1, -1]
# def bfs(jPos, fPos):
#     i,j = jPos[0]
#     arr[i][j] = 1

#     while jPos:

#         # Jihun
#         tmpJ = []
#         for jx,jy in jPos:
#             key = arr[jx][jy]
#             if key == 'F':
#                 continue

#             for X, Y in zip(dx,dy):
#                 nx, ny = jx+X, jy+Y

#                 if (nx < 0 or nx >= R) or (ny < 0 or ny >= C):
#                     return key

#                 if arr[nx][ny] != '.':
#                     continue

#                 arr[nx][ny] = key + 1
#                 tmpJ.append((nx, ny))
#         jPos = tmpJ

#         # Fire
#         tmp = []
#         for fx, fy in fPos:
#             for X, Y in zip(dx,dy):
#                 nx, ny = fx+X, fy+Y
#                 if (nx < 0 or nx >= R) or (ny < 0 or ny >= C):
#                     continue
#                 if arr[nx][ny] == 'F' or arr[nx][ny] == '#':
#                     continue
#                 arr[nx][ny] = 'F'
#                 tmp.append((nx, ny))
#         fPos = tmp

#     return "IMPOSSIBLE"


# print(bfs(jPos, fPos))
