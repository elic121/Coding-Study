import sys
if __name__ == "__main__":

    # N = 10_000_000
    N = 1000
    arr = [1 for _ in range(N+1)]
    arr[0] = 0; arr[1] = 0
    for i in range(2,int(N ** 0.5) + 1):
        if arr[i]:
            for j in range(i * i, N + 1, i):
                arr[j] = 0

    s = [0 for _ in range(N+1)]
    for i in range(2, N+1):
        s[i] = s[i-1] + (arr[i] == 1)

    while True:
        try:
            m, n = map(int,input().split())
            print(s[n] - s[m - 1])
            print()
            input()
        except:
            break