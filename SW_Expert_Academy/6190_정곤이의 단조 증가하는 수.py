def check(n):
    key = 0
    for val in str(n):
        if key > int(val):
            return False
        key = int(val)
    return True


if __name__ == '__main__':
    T = int(input())

    for tc in range(1, T + 1):
        res = [-1]
        N = int(input())
        arr = list(map(int, input().split()))
        for i in range(N):
            for j in range(i + 1, N):
                value = arr[i] * arr[j]
                if check(value):
                    res.append(value)

        print(f"#{tc} {max(res)}")
