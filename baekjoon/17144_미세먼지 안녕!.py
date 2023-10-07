# https://www.acmicpc.net/problem/17144
from sys import stdin
s = stdin.readline

R,C,T = map(int,s().split())
ac = []
arr = []
for i in range(R):
    lst = list(map(int,s().split()))
    for idx,j in enumerate(lst):
        if j == -1:
            ac.append((i,idx))
    arr.append(lst)

dx = [1,0,-1,0]
dy = [0,1,0,-1]
def difussion():
    global arr
    tmp = [[0 for _ in range(C)] for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if arr[i][j] == 0:
                continue
            if arr[i][j] == -1:
                continue
            cnt = 0
            val = arr[i][j] // 5
            for X,Y in zip(dx,dy):
                nx,ny = i+X, j+Y
                if (nx<0 or nx>=R) or (ny<0 or ny>=C):
                    continue
                if arr[nx][ny] == -1:
                    continue
                cnt += 1
                tmp[nx][ny] += val

            tmp[i][j] += arr[i][j] - val * cnt

    arr = tmp

def aircleaner():
    global arr
    tmp = [[0 for _ in range(C)] for _ in range(R)]
    fir, sec = ac

    for row in range(1,fir[0]):
        for col in range(1,C-1):
            tmp[row][col] = arr[row][col]

    for col in range(2,C):
        tmp[fir[0]][col] = arr[fir[0]][col-1]
    tmp[fir[0]][1] = 0

    for row in range(fir[0]-1,-1,-1):
        tmp[row][C-1] = arr[row+1][C-1]

    for col in range(C-2,-1,-1):
        tmp[0][col] = arr[0][col+1]

    for row in range(1,fir[0]):
        tmp[row][0] = arr[row-1][0]

    for row in range(sec[0],R-1):
        for col in range(1,C-1):
            tmp[row][col] = arr[row][col]

    for col in range(2,C):
        tmp[sec[0]][col] = arr[sec[0]][col-1]
    tmp[sec[0]][1] = 0

    for row in range(sec[0]+1,R):
        tmp[row][C-1] = arr[row-1][C-1]

    for col in range(C-2,-1,-1):
        tmp[R-1][col] = arr[R-1][col+1]

    for row in range(R-2,sec[0],-1):
        tmp[row][0] = arr[row+1][0]

    tmp[fir[0]][fir[1]] = -1
    tmp[sec[0]][sec[1]] = -1

    arr = tmp

for _ in range(T):
    difussion()
    aircleaner()

cnt = 0
for i in range(R):
    for j in range(C):
        if arr[i][j] == -1:
            continue
        cnt += arr[i][j]
print(cnt)
        

