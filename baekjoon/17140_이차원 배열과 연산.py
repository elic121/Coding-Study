# https://www.acmicpc.net/problem/17140
from sys import stdin
from collections import Counter
s = stdin.readline
r,c,k = map(int,s().split())
arr = []
for _ in range(3):
    arr.append(list(map(int,s().split())))
cnt = 0
def rotate():
    global arr
    col = len(arr[0])
    arr = [[x[i] for x in arr] for i in range(col)]

while True:
    try:
        if arr[r-1][c-1] == k:
            print(cnt)
            break
    except:
        pass

    if cnt == 101:
        print(-1)
        break

    M = 0
    new_arr = []
    R = False
    if len(arr) >= len(arr[0]):
        pass
    else:
        R = True
        rotate()

    tmp = []
    for lst in arr:
        C = Counter([x for x in lst if x!=0])
        inf = sorted(C.items(),key=lambda x:(x[1],int(x[0])))
        if len(inf) > M:
            M = len(inf)
        for _ in range(M-len(inf)):
            inf.append((0,0))
        tmp2 = []
        for x,y in inf:
            tmp2.append(x)
            tmp2.append(y)
        new_arr.append(tmp2)

    for idx in range(len(new_arr)):
        if len(new_arr[idx]) < 2*M:
            for _ in range(2*M-len(new_arr[idx])):
                new_arr[idx].append(0)

    arr = new_arr
    if R:
        rotate()

    cnt += 1