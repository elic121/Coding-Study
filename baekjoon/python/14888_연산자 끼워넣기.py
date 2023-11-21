# https://www.acmicpc.net/problem/14888
from sys import stdin

s = stdin.readline
N = int(s())
num = list(map(int, s().split()))
sign = list(map(int, s().split()))

res = []


def permutation(sign, result, idx):
    global res
    if sum(sign) == 0:
        res.append(result)
        return

    for i in range(4):
        if sign[i] > 0:
            sign[i] -= 1
            if i == 0:
                permutation(sign, result + num[idx], idx + 1)
            if i == 1:
                permutation(sign, result - num[idx], idx + 1)
            if i == 2:
                permutation(sign, result * num[idx], idx + 1)
            if i == 3:
                permutation(sign, int(result / num[idx]), idx + 1)
            sign[i] += 1
    return


permutation(sign, num[0], 1)
print(max(res))
print(min(res))
