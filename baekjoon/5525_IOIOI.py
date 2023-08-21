# https://www.acmicpc.net/problem/5525
from sys import stdin
from collections import deque
s = stdin.readline
N = int(s())
M = int(s())
l = s().strip()

# solution 1
# deque
C = 0
def solution1():
    global C
    d = deque()
    idx = 0
    while True:

        if idx == M:
            break

        symbol = l[idx]
        
        
        if d and d[-1] == symbol:
            d.clear()
            d.append(symbol)
            idx += 1
            continue

        if d and d[0] == 'O':
            d.popleft()
            d.append(symbol)
            idx += 1
            continue
        
        d.append(symbol)
        idx += 1
        
        if len(d) == 2*N+1:
            C+=1
            d.popleft()
            d.popleft()

solution1()
print(C)