# https://www.acmicpc.net/problem/1991
from sys import stdin
from collections import defaultdict
s = stdin.readline
N = int(s())
d = defaultdict(list)
for _ in range(N):
    a, b, c = s().strip().split()
    d[a].append(b)
    d[a].append(c)

def pre(x):
    if x=='.':
        return
    c1, c2 = d[x]
    print(x,end='')
    pre(c1)
    pre(c2)

def ino(x):
    if x=='.':
        return
    c1, c2 = d[x]
    ino(c1)
    print(x,end='')
    ino(c2)

def pos(x):
    if x=='.':
        return
    c1, c2 = d[x]
    pos(c1)
    pos(c2)
    print(x,end='')

pre('A')
print()
ino('A')
print()
pos('A')
