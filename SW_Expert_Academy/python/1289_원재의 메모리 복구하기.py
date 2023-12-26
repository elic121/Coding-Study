if __name__ == "__main__":
    N = int(input())
    for cs in range(1, N + 1):
        cnt = 0

        s = list(input().rstrip())
        c = ['0'] * (len(s))

        idx = 0
        while s != c or idx == len(s):
            if s[idx] == c[idx]:
                idx += 1
                continue

            key = s[idx]
            for i in range(idx, len(s)):
                c[i] = key

            cnt += 1

        print(f"#{cs} {cnt}")
