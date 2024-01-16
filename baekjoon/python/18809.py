from collections import deque
import sys

s = sys.stdin.readline

MAX = 0
def someThing():
    global MAX
    d = deque()

    return

def separate(l):
    ext = []
    def b2(n, tmp):
        if len(tmp) == G:
            ext.append(tmp.copy())
            return
        for i in range(n, l):
            tmp.append(i)
            b2(i+1, tmp)
            tmp.pop()
        return
    b2(0, [])
    return ext


def backTracking(n, tmp):
    if len(tmp) == G+R:
        sep = separate(G+R)
        for s in sep:
            someThing(tmp, s)        
        return

    for i in range(n, len(possible)):
        tmp.append(i)
        backTracking(i+1, tmp)
        tmp.pop()


if __name__ == "__main__":
    N, M, G, R = map(int, input().split())
    if G > R:G, R = R, G
    arr = []
    possible = []
    for i in range(N):
        lst = list(map(int, s().split()))
        for j, val in enumerate(lst):
            if val == 2:
                possible.append((i, j))
        arr.append(lst)
    
    backTracking(0, [])
    print(MAX)