# https://www.acmicpc.net/problem/15989
from sys import stdin

s = stdin.readline
N = int(s())
arr = [1 for _ in range(10001)]
for i in range(2, 10001):
    arr[i] += arr[i - 2]
for i in range(3, 10001):
    arr[i] += arr[i - 3]
for _ in range(N):
    n = int(s())
    print(arr[n])
