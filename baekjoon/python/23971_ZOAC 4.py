# https://www.acmicpc.net/problem/23971
import sys
import math as m

s = sys.stdin.readline
H, W, N, M = map(int, s().split())
print(m.ceil(H / (N + 1)) * m.ceil(W / (M + 1)))
