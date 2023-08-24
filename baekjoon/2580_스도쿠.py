# https://www.acmicpc.net/problem/2580
from sys import stdin
s = stdin.readline
zero = []
arr = []
for i in range(9):
    tmp = list(map(int,s().split()))
    for idx,val in enumerate(tmp):
        if val == 0:
            zero.append((i,idx))
    arr.append(tmp)

def checkRow(xPos,val):
    for i in range(9):
        if arr[xPos][i] == val:
            return False
    return True

def checkCol(yPos,val):
    for i in range(9):
        if arr[i][yPos] == val:
            return False
    return True

def checkBox(xPos,yPos,val):
    o,p = 3*(xPos//3), 3*(yPos//3)
    for q in range(o,o+3):
        if q == xPos:
            continue
        for w in range(p,p+3):
            if w == yPos:
                continue
            if arr[q][w] == val:
                return False
    return True

def backTracking(n,arr):

    if n == len(zero):
        for i in arr:
            print(*i)
        exit()
    
    x,y = zero[n]
    for i in range(1,10):
        if not checkRow(x,i): 
            continue
        if not checkCol(y,i):
            continue
        if not checkBox(x,y,i):
            continue

        arr[x][y] = i
        backTracking(n+1,arr)
        arr[x][y] = 0
    
    return

backTracking(0,arr)
