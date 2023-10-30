# https://www.acmicpc.net/problem/17779
from sys import stdin

s = stdin.readline
N = int(s())
arr = []
for _ in range(N):
    lst = list(map(int, s().split()))
    arr.append(lst)

# for d1 in range(1,N):
#     for d2 in range(1,N):
#         for x in range(d1+d2-1,N):
#             for y in range()


def graph(x, y, d1, d2):
    N = 8
    tmp = [[0 for _ in range(N)] for _ in range(N)]
    for r in range(1, x + d1):
        for c in range(1, y + 1):
            tmp[r][c] = 1

    # for r in range(1,x+d2):
    #     for c in range(y+2-r,N):
    #         tmp[r][c] = 2

    # for r in range(x+d1+1,N):
    #     for c in range(1,y-d1+d2):
    #         tmp[r][c] = 3

    for i in tmp:
        print(*i)


graph(2, 5, 3, 2)
