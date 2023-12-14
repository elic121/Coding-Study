import sys
s = sys.stdin.readline
N = int(s())
f = N // 5
check = False
while f >= 0:
    if (N - f * 5) % 2 == 1:
        f -= 1
        continue
    else:
        print(f + (N - f * 5) // 2)
        check = True
        break
if not check:print(-1)
