# https://www.acmicpc.net/problem/14891
from sys import stdin
from collections import deque

s = stdin.readline
arr = [deque(list(map(int, list(s().strip())))) for _ in range(4)]

for _ in range(int(s())):
    idx, d = map(int, s().split())

    rotate = [(idx - 1, d)]

    a = d
    b = d
    for D in range(idx - 2, -1, -1):
        if arr[D + 1][6] != arr[D][2]:
            a *= -1
            rotate.append((D, a))
        else:
            break

    for D in range(idx, 4):
        if arr[D - 1][2] != arr[D][6]:
            b *= -1
            rotate.append((D, b))
        else:
            break

    for i, D in rotate:
        arr[i].rotate(D)

print(sum([(1 << i) * (arr[i][0] == 1) for i in range(4)]))
