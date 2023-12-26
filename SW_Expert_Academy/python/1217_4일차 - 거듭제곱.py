def sol(N, M):
    if M == 1:
        return N

    if M % 2 == 0:
        return sol(N, M//2) * sol(N, M//2)
    else:
        return N * sol(N, (M-1)//2) * sol(N, (M-1)//2)


if __name__ == "__main__":
    for _ in range(10):
        t = int(input())
        N, M = map(int,input().split())

        print(f"#{t} {sol(N, M)}")