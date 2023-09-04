# https://www.acmicpc.net/problem/9935
from sys import stdin

s = stdin.readline

sen = list(s().strip())
L = s().strip()

while True:
    tmp = []
    cnt = 0
    for l in sen:
        tmp.append(l)
        if len(tmp) < len(L):
            continue
        check = True
        for i in range(len(L)):
            if tmp[-i - 1] != L[-i - 1]:
                check = False
                break
        if check:
            for _ in range(len(L)):
                tmp.pop()
            cnt += 1
    if cnt == 0:
        break
    sen = tmp

if sen:
    print("".join(sen))
else:
    print("FRULA")
