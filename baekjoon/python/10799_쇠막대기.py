# https://www.acmicpc.net/problem/10799
l, s, c = input(), [], 0
for i in range(len(l)):
    if l[i] == "(":
        s.append(l[i])
    else:
        s.pop()
        c += [1, len(s)][l[i - 1] == "("]
print(c)
