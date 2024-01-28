import sys

def back(idx, prev, sol, visited):
    global MAX_STRING, MIN_STRING, MAX, MIN
    if idx == N+1:
        num = ''.join(map(str,sol))
        if (int(num) > MAX):
            MAX = int(num)
            MAX_STRING = num
        if (int(num) < MIN):
            MIN = int(num)
            MIN_STRING = num
        return
    
    c = arr[idx-1] == '<'
    stt = prev+1 if c else prev-1
    end = 10 if c else -1
    step = 1 if c else -1
    for i in range(stt,end,step):
        if visited[i]: continue
        visited[i] = True
        sol.append(i)
        back(idx+1, i, sol, visited)
        sol.pop()
        visited[i] = False

s = sys.stdin.readline

N = int(s())
arr = s().split()
visited = [False for _ in range(10)]
MAX = -sys.maxsize
MIN = sys.maxsize

MAX_STRING = ""
MIN_STRING = ""

for i in range(10):
    visited[i] = True
    back(1, i, [i], visited)
    visited[i] = False

print(MAX_STRING)
print(MIN_STRING)
