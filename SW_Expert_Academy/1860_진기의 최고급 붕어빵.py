if __name__ == "__main__":
    T = int(input())
    ans = ["Impossible", "Possible"]
    for tc in range(1, T + 1):
        N, M, K = map(int, input().split())
        arr = list(map(int, input().split()))
        d = {}

        for i in arr:
            if i not in d:
                d[i] = 1
            else:
                d[i] += 1

        bread = 0
        p = 0

        P = True

        for sec in range(11112):
            if sec > 0 and sec % M == 0:
                bread += K

            if sec in d:
                if bread - d[sec] < 0:
                    P = False
                    break
                else:
                    bread -= d[sec]

        if P:
            print(f"#{tc} {ans[1]}")
        else:
            print(f"#{tc} {ans[0]}")