def sol1(M, N):
    if M > N:
        n = N % 2
        x, y, d = (1 - n) + (N // 2) * [1,-1][n] + n * M, (N + n) // 2, 2 * N - 1
        # if N % 2 == 0:
        #     x, y = N // 2 + 1, N // 2, 
        # else:
        #     x, y= M - N // 2 , (N + 1) // 2
        # d = 2 * N - 1
    else:
        m = M % 2
        x, y, d = (M + m) // 2 + (1 - m), m * N + (M // 2) * [1,-1][m], 2 * (M - 1)
        # if M % 2 == 0:
        #     x, y = M // 2 + 1, M // 2 
        # else:
        #     x, y = (M + 1) // 2, N - M // 2
        # d = 2 * (M - 1)
    
    return x, y, d



if __name__ == "__main__":
    M, N = map(int, input().split())
    x, y, d = sol1(M,N)
    print(d)
    print(x,y)