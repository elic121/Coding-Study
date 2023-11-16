def backtracking(n, tmp):
    global sat
    if sum(map(lambda x: x[1], tmp)) > L:
        return

    sat = max(sat, sum(map(lambda x: x[0], tmp)))

    for i in range(n, N):
        tmp.append(arr[i])
        backtracking(i + 1, tmp)
        tmp.pop()

    return


if __name__ == '__main__':
    T = int(input())
    for tc in range(1, T + 1):
        N, L = map(int, input().split())
        arr = []
        for _ in range(N):
            t, k = map(int, input().split())
            arr.append((t, k))

        # arr.sort(key=lambda x: (-x[0] / x[1]))

        sat = 0
        # for t, k in arr:
        #     next_k = L - k
        #     if next_k < 0:
        #         continue
        #     L = next_k
        #     sat += t

        backtracking(0, [])

        print(f"#{tc} {sat}")
