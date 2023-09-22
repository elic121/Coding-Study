# https://www.acmicpc.net/problem/17281
from sys import stdin
from itertools import permutations
s = stdin.readline

N = int(s())
record = []
for _ in range(N):
    record.append(list(map(int, s().split())))

M = 0
for val in permutations(range(1, 9), 8):
    lst = list(val)
    perm = lst[0:3] + [0] + lst[3:]

    next_runner = 0
    score = 0

    for i in range(N):
        zero_count = 0
        base_1, base_2, base_3 = 0, 0, 0
        tmp = [0 for _ in range(9)]

        for j, idx in enumerate(perm):
            tmp[j] = record[i][idx]

        while True:
            res = tmp[next_runner]
            next_runner = (next_runner+1) % 9

            if res == 0:
                zero_count += 1
                if zero_count == 3:
                    break
                continue

            if res == 1:
                score += base_3
                base_3 = base_2
                base_2 = base_1
                base_1 = 1

            if res == 2:
                score += base_3 + base_2
                base_3 = base_1
                base_2 = 1
                base_1 = 0

            if res == 3:
                score += base_1 + base_2 + base_3
                base_3 = 1
                base_2 = 0
                base_1 = 0

            if res == 4:
                score += base_1 + base_2 + base_3 + 1
                base_3 = 0
                base_2 = 0
                base_1 = 0

    M = max(M, score)

print(M)
