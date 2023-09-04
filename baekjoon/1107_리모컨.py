# https://www.acmicpc.net/problem/1107
from sys import stdin

s = stdin.readline
n = int(s())
N = int(s())
if N:
    I = list(map(int, s().split()))
else:
    I = []

only_updown = abs(n - 100)


def sub_sol():
    global only_updown
    for i in range(1000001):
        l = str(i)
        C = True
        for j in l:
            if int(j) in I:
                C = False
                break
        if C:
            only_updown = min(only_updown, len(l) + abs(i - n))


sub_sol()
print(only_updown)
