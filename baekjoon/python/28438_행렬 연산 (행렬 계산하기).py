if __name__ == '__main__':
    N, M, Q = map(int, input().split())

    arr = [[0 for _ in range(max(N, M))]
           for _ in range(2)]

    for _ in range(Q):
        n, r, v = map(int, input().split())
        arr[n - 1][r - 1] += v

    lst = [[0 for _ in range(M)]
           for _ in range(N)]

    for idx in range(N):
        val = arr[0][idx]
        for col in range(M):
            lst[idx][col] += val

    for idx in range(M):
        val = arr[1][idx]
        for row in range(N):
            lst[row][idx] += val

    for row in lst:
        print(*row)
