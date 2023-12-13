import sys
s = sys.stdin.readline
sen = s().strip()
com = s().strip()
L = len(com)
idx = sol = 0
while (L+idx <= len(sen)):
    cnt = 0
    for i in range(idx, idx+L):
        if com[i-idx] == sen[i]:cnt += 1
        else:break
    c = cnt == L
    idx += 1 + c*(L-1)
    sol += c
print(sol)
