# https://www.acmicpc.net/problem/23291
from sys import stdin
s = stdin.readline

N, K = map(int, s().split())
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
lst = list(map(int, s().split()))

if max(lst) - min(lst) <= K:
    print(0)
    exit()

arr = [[0 for _ in range(N)] for _ in range(N)]
for i in range(N):
    arr[-1][i] = lst[i]


def feedMin():
    MIN = min(arr[-1])
    for idx in range(N):
        if arr[-1][idx] == MIN:
            arr[-1][idx] += 1

def stack():
    stt = 0; end = 1
    I = 0
    while True:

        height = 1 + (I+1)//2
        count = 1 + I//2

        if height > N-end:
            break

        sub = []

        for idx in range(stt, stt+count):
            tmp = []
            for j in range(N-1, N-1-height, -1):
                val = arr[j][idx]
                tmp.append(val)
                arr[j][idx] = 0
            sub.append(tmp)

        for i in range(count):
            for j in range(height):
                arr[N-i-2][end+j] = sub[count-1-i][j]

        stt = end
        end += height
        I += 1
    
    return stt

def regulator():
    tmp = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 0:
                continue

            for X, Y in zip(dx, dy):
                nx, ny = i+X, j+Y
                if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                    continue
                if arr[nx][ny] == 0:
                    continue
                if arr[i][j] < arr[nx][ny]:
                    continue
                
                diff = arr[i][j]-arr[nx][ny]
                share = diff//5
                if share > 0:
                    tmp[i][j] -= share
                    tmp[nx][ny] += share

    for i in range(N):
        for j in range(N-1, -1, -1):
            if tmp[i][j] == 0:
                continue
            arr[i][j] += tmp[i][j]

def regulator2():
    tmp = [[0 for _ in range(N//4)] for _ in range(4)]
    for i in range(4):
        for j in range(N//4):
            for X,Y in zip(dx,dy):
                nx, ny = i+X, j+Y
                if (nx<0 or nx>=4) or (ny<0 or ny>=N//4):
                    continue
                if newarr2[i][j] < newarr2[nx][ny]:
                    continue
                
                diff = newarr2[i][j]-newarr2[nx][ny]
                share = diff//5
                if share > 0:
                    tmp[i][j] -= share
                    tmp[nx][ny] += share
    
    for i in range(4):
        for j in range(N//4):
            newarr2[i][j] += tmp[i][j]

def serialization(stt):
    tmp = []
    for idx in range(stt, N):
        for j in range(N-1, -1, -1):
            val = arr[j][idx]
            if val == 0:
                break
            tmp.append(val)

    return tmp

def floating(arr):
    tmp = [[0 for _ in range(N//4)] for _ in range(4)]
    tmp1 = [arr[i] for i in range(N//2-1, -1, -1)]
    tmp2 = [arr[i] for i in range(N//2, N)]

    for j in range(N//4):
        tmp[0][j] = tmp2[N//4-j-1]
        tmp[1][j] = tmp1[N//4-j-1]
        tmp[2][j] = tmp1[j+N//4]
        tmp[3][j] = tmp2[j+N//4]
            
    return tmp

C = 0
while True:
    C += 1
    feedMin()
    S = stack()
    regulator()
    newarr = serialization(S)
    newarr2 = floating(newarr)
    regulator2()

    tmp3 = []
    for i in range(N//4):
        for j in range(3,-1,-1):
            tmp3.append(newarr2[j][i])
    
    if max(tmp3) - min(tmp3) <= K:
        print(C)
        break
    
    A = [[0 for _ in range(N)] for _ in range(N)]
    for col in range(N):
        A[N-1][col] = tmp3[col]
    arr = A