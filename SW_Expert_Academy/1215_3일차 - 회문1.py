def check(tmp):
    L = len(tmp)
    for i in range(L // 2):
        if tmp[i] != tmp[L - i - 1]:
            return False
    return True


if __name__ == '__main__':
    T = 10
    for tc in range(1, T + 1):
        size = int(input())
        arr = []
        for _ in range(8):
            lst = list(input().strip())
            arr.append(lst)

        new_arr = list(zip(*arr))

        ans = 0

        for i in range(8):
            for j in range(8):
                if j + size > 8:
                    break
                if check(arr[i][j:j + size]):
                    ans += 1
                if check(new_arr[i][j:j + size]):
                    ans += 1

        print(f"#{tc} {ans}")
