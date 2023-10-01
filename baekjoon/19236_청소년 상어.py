# https://www.acmicpc.net/problem/19236
import sys
from copy import deepcopy
s = sys.stdin.readline
dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, -1, -1, -1, 0, 1, 1, 1]

arr = [[0 for _ in range(4)] for _ in range(4)]
for i in range(4):
    lst = list(map(int, s().split()))
    for idx, j in enumerate(range(len(lst)//2)):
        key, dr = lst[2*j], lst[2*j+1]
        arr[i][idx] = [key,dr]

def SHARK(i, j, arr, cnt):
    global MAX

    KEY = arr[i][j]
    cnt += KEY[0]
    P = KEY[1]
    arr[i][j] = [0,None]
    
    MAX = max(MAX, cnt)

    for idx in range(1, 17):

        exist = False
        for X in range(4):
            for Y in range(4):
                if arr[X][Y][0] == idx:
                    exist = True
                    break
            if exist:
                break

        if exist == False:
            continue

        d = arr[X][Y][1]

        for I in range(8):
            pos = (d - 1 + I) % 8
            nx, ny = X + dx[pos], Y + dy[pos]
            if (nx < 0 or nx >= 4) or (ny < 0 or ny >= 4):
                continue
            if nx == i and ny == j:
                continue
            arr[X][Y], arr[nx][ny] = arr[nx][ny], [idx,pos+1]
            break

    for idx in range(1, 4):

        nx, ny = i + dx[P-1]*idx, j + dy[P-1]*idx

        if (nx < 0 or nx >= 4) or (ny < 0 or ny >= 4):
            continue
        
        if arr[nx][ny][0] == 0:
            continue

        SHARK(nx, ny, deepcopy(arr), cnt)

    return


MAX = 0
SHARK(0, 0, arr, 0)
print(MAX)
