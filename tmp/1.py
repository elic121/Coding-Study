from sys import stdin
from collections import defaultdict
s=stdin.readline
d = defaultdict(int)
for _ in range(5):
    tmp = int(s())
    d[tmp] += 1
for i in d.keys():
    if d[i] % 2 != 0:
        print(i)

