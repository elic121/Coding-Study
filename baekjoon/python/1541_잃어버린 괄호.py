# https://www.acmicpc.net/problem/1541
"""
'-'를 기준으로 나누는 게 핵심
"""
from sys import stdin

s = stdin.readline
l = s().strip().split("-")
for i in range(len(l)):
    tmp = l[i].split("+")
    l[i] = "+".join([str(int(i)) for i in tmp])

c = eval(l[0])
for i in l[1:]:
    c -= eval(i)
print(c)
