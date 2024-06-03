import sys
import copy
s = sys.stdin.readline
n, k = map(int, s().split())
a = list(map(int, s().split()))
v = [i for i in range(n-1)]
t = []
w = 0
while v:
    for i in v:
        if a[i] > a[i+1]:
            a[i], a[i+1] = a[i+1], a[i]
            w += 1
            if w == k:
                print(a[i], a[i+1])
                sys.exit()
            if i:
                t.append(i-1)
    v = copy.deepcopy(t)
    t = []
print(-1)
