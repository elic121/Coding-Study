# https://www.acmicpc.net/problem/25757
from sys import stdin
s = stdin.readline
N, G = s().split()
D = {'Y':2,'F':3,'O':4}
q = set()
for _ in range(int(N)):
    l = s().strip()
    q.add(l)
L = len(q)
print(L//(D[G]-1))