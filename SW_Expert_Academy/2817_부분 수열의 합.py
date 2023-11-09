def backtracking(idx, key, s):
    global cnt

    if s == key:
        cnt += 1
        return

    if s > key:
        return

    for i in range(idx, len(arr)):
        s += arr[i]
        backtracking(i + 1, key, s)
        s -= arr[i]

    return

if __name__ == "__main__":
    N = int(input())
    for i in range(1, N + 1):
        n, k = map(int, input().split())
        arr = list(map(int, input().split()))
        cnt = 0
        backtracking(0, k, 0)

        print(f"#{i} {cnt}")
