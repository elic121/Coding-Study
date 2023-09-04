from sys import stdin

s = stdin.readline
N = int(s())
tmp1 = ""
tmp2 = ""
d = []
case = 0
if N > 1:
    for i in range(N):
        d.append(s().strip())
    for i in range(N):
        if d[i] == "?" and (i >= 1 and i <= N - 2):
            tmp1 = d[i - 1][-1]
            tmp2 = d[i + 1][0]
            case = 1
            break
        # ?가 맨앞에 있으므로 그 다음 단어의 첫번째 문자가 젤 앞에 와야됨
        elif d[i] == "?" and (i == 0):
            tmp2 = d[i + 1][0]
            case = 2
            break
        # ?가 맨끝에 있으므로 그 전 단어의 마지막 문자가 젤 앞에 와야됨
        elif d[i] == "?" and (i == N - 1):
            tmp1 = d[i - 1][-1]
            case = 3
            break

    n = int(s())
    for _ in range(n):
        t = s().strip()
        if t in d:
            continue

        if t[0] == tmp1 and t[-1] == tmp2 and case == 1:
            print(t)
            break
        elif t[-1] == tmp2 and tmp1 == "" and case == 2:
            print(t)
            break
        elif t[0] == tmp1 and tmp2 == "" and case == 3:
            print(t)
            break
else:
    s()
    int(s())
    print(s().strip())
