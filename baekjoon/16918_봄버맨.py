# https://www.acmicpc.net/problem/16918 
from sys import stdin
s = stdin.readline
R, C, N = map(int,s().split())
arr = []
for i in range(R):
    tmp = []
    for j in list(s().strip()):
        tmp.append([3,'.'][j=='.'])
    arr.append(tmp)

d = [1,-1,0,0]

def bomb():

    for i in range(R):
        for j in range(C):
            if arr[i][j] != '.':
                arr[i][j] -= 1

    for i in range(R):
        for j in range(C):
            if arr[i][j] == 0:
                arr[i][j] = '.'
                for k in range(4):
                    nx, ny = i+d[k], j+d[3-k]
                    if (nx<0 or nx>=R) or (ny<0 or ny>=C):
                        continue
                    arr[nx][ny] = '.'
    

def getBomb():
    for i in range(R):
        for j in range(C):
            if arr[i][j] == '.':
                arr[i][j] = 3


def p():
    for i in range(R):
        print(*arr[i])
    print("=============")

bomb()
p()

bomb()
getBomb()
p()

bomb()
p()

bomb()
p()