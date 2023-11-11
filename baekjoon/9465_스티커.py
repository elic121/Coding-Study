# https://www.acmicpc.net/problem/9465


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        N = int(input())
        arr = []
        for _ in range(2):
            lst = list(map(int, input().split()))
            arr.append(lst)

        dp = [[0 for _ in range(N + 2)] for _ in range(2)]
        for i in range(N):
            dp[0][i + 2] = arr[0][i]
            dp[1][i + 2] = arr[1][i]

        for idx in range(2, N + 2):
            dp[0][idx] = max(dp[1][idx - 1], dp[1][idx - 2]) + dp[0][idx]
            dp[1][idx] = max(dp[0][idx - 1], dp[0][idx - 2]) + dp[1][idx]

        print(max(dp[0][-1], dp[1][-1]))
