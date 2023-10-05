# https://www.acmicpc.net/problem/20061
from sys import stdin
from collections import deque
s = stdin.readline
N = int(s())
inf = []

for _ in range(N):
    tmp = list(map(int, s().split()))
    inf.append(tmp)

blue = deque([[0 for _ in range(4)] for _ in range(6)])
green = deque([[0 for _ in range(4)] for _ in range(6)])

def block(arr, idx, x, y):

    if x == 1 and y == 1:
        for row in range(6):
            if arr[row][idx] == 1:
                break
        if row == 5 and arr[row][idx] == 0:
            arr[row][idx] = 1
        else:
            arr[row-1][idx] = 1

    if x == 1 and y == 2:
        for row in range(6):
            if arr[row][idx] == 1 or arr[row][idx+1] == 1:
                break
        if row == 5 and arr[row][idx] == 0 and arr[row][idx+1] == 0:
            arr[row][idx] = 1
            arr[row][idx+1] = 1
        else:
            arr[row-1][idx] = 1
            arr[row-1][idx+1] = 1

    if x == 2 and y == 1:
        
        for row in range(2,6):
            if arr[row][idx] == 1:
                break

        if row == 5 and arr[row][idx] == 0 and arr[row-1][idx] == 0:
            arr[row][idx] = 1
            arr[row-1][idx] = 1
        else:
            arr[row-1][idx] = 1
            arr[row-2][idx] = 1

def lineCheck(arr):
    s = 0
    tmp = deque()
    for row in range(5,1,-1):
        c = 0
        for col in range(4):
            c += arr[row][col] == 1
        if c == 4:
            s += 1
            arr.pop()
        else:
            tmp.appendleft(arr.pop())
    
    for _ in range(2):
        tmp.appendleft(arr.pop())
    for _ in range(s):
        tmp.appendleft([0 for _ in range(4)])

    return s, tmp

def exception(arr):
    if sum(arr[1]) == 2:
        stt = 0
        for i in range(4):
            if arr[1][i] == 1:
                stt = i
                break
        G = 2
        for idx in range(2,6):
            if arr[idx][stt] == 1 or arr[idx][stt+1] == 1:
                G = idx
                break
        if G == 5 and arr[G][stt] == 0 and arr[G][stt+1]:
            arr[G][stt] = 1
            arr[G][stt+1] = 1
        else:
            arr[G-1][stt] = 1
            arr[G-1][stt+1] = 1

    else:
        for col in range(4):
            if arr[1][col] == 1:
                G = 2
                for idx in range(2,6):
                    if arr[idx][col] == 1:
                        G = idx
                        break
                if G == 5 and arr[G][col] == 0:
                    arr[G][col] = 1
                else:
                    arr[G-1][col] = 1

def linepop(arr):
    s = 0
    for i in range(1,-1,-1):
        if sum(arr[i]) >= 1:
            s+=1
            arr.pop()
    for _ in range(s):
        arr.appendleft([0 for _ in range(4)])

score = 0
for t, x, y in inf:

    if t == 1:
        i, j = 1, 1
    if t == 2:
        i, j = 1, 2
    if t == 3:
        i, j = 2, 1

    block(green,y,i,j)
    block(blue,x,j,i)

    sco, green = lineCheck(green)
    score += sco

    sco, blue = lineCheck(blue)
    score += sco

    exception(green)
    exception(blue)

    sco, green = lineCheck(green)
    score += sco

    sco, blue = lineCheck(blue)
    score += sco

    linepop(green)
    linepop(blue)

    sco, green = lineCheck(green)
    score += sco

    sco, blue = lineCheck(blue)
    score += sco

print(score)
block_cnt = 0
for i in range(6):
    for j in range(4):
        block_cnt += (blue[i][j]==1) + (green[i][j]==1)
print(block_cnt)