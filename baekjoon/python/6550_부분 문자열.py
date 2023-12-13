while len(l := input().strip()) != 0:
    s, t = l.split()
    I = F = 0
    Q = len(t)
    while I < Q and F < len(s):
        T = F
        for i in range(I, Q):
            if s[F] == t[i]:
                F += 1
                I = i+1
                break
        if T == F:
            break
    print(["No", "Yes"][F == len(s)])


"""
import sys
S = sys.stdin.readline

while len(line:=S().strip())!=0:
    s, t = line.split()
    idx = find = 0
    while (idx < len(t) and find < len(s)):
        tmp = find
        for i in range(idx,len(t)):
            if s[find] == t[i]:
                find += 1
                idx = i + 1
                break
        if tmp == find:
            break
    if find == len(s):
        print("Yes")
    else:
        print("No")

"""
