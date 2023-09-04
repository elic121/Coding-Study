# https://www.acmicpc.net/problem/11286
from sys import stdin
from queue import PriorityQueue as PQ

s = stdin.readline
q = PQ()
N = int(s())
for _ in range(N):
    val = int(s())
    if val != 0:
        q.put((abs(val), val))
    else:
        if q.empty():
            print(0)
        else:
            print(q.get()[1])
