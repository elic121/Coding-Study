def backtracking(n, cnt):
    global M
    ans = int(''.join(num))
    if ans in visited[cnt]:
        return
    else:
        visited[cnt].append(ans)

    if cnt == n:
        return

    for i in range(L):
        for j in range(i + 1, L):
            num[i], num[j] = num[j], num[i]
            backtracking(n, cnt + 1)
            num[i], num[j] = num[j], num[i]

    return


if __name__ == "__main__":
    t = int(input())
    for tc in range(1, t + 1):
        M = 0
        num, N = map(int, input().split())
        num = list(str(num).strip())
        L = len(num)
        visited = {i: list() for i in range(N+1)}
        backtracking(N, 0)

        print(f"#{tc} {max(visited[N])}")
