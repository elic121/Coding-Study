# https://www.acmicpc.net/problem/9663
# https://rebas.kr/761
from sys import stdin
s = stdin.readline

def promise(x):
    key = arr[x]
    for i in range(x):
        com = arr[i]
        if key == com:
            return False
        if abs(key-com) == x-i:
            return False
    return True

def queen(x):
    global c

    if x == N:
        c += 1
        return
    
    for i in range(N):
        if visited[i]:
            continue

        arr[x] = i
        if promise(x):
            visited[i] = True
            queen(x+1)
            visited[i] = False

N = int(s())
c = 0
arr = [0 for _ in range(N)]
visited = [0 for _ in range(N)]
queen(0)
print(c)
