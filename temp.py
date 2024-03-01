import sys
s = sys.stdin.readline

N = int(s())

for i in range(N):
    sem = s().strip()
    cnt1 = 0
    cnt2 = 0
    for v in sem:
        if v != '!': break
        cnt1 += 1
    cnt2 = len(sem) - cnt1 - 1
    if cnt1 % 2 == 0:
        if cnt2 == 0:
            print(sem[cnt1])
        else:
            print(1)
    else:
        if cnt2 == 0:
            if sem[cnt1] == '0':
                print(1)
            else:
                print(0)
        else:
            print(0)