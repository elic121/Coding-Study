cnt = 0
while True:
    cnt += 1
    l, p, v = map(int, input().split())
    if (l == 0 and p == 0 and v == 0):
        break
    ans = (v // p) * l + min(l, v % p)
    print(f"Case {cnt}: {ans}")
