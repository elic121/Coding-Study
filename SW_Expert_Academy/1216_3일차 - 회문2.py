def check(tmp):
    L = len(tmp)
    for i in range(L // 2):
        if tmp[i] != tmp[L - i - 1]:
            return False
    return True


if __name__ == '__main__':
    T = 10
    for tc in range(1, T + 1):
        N = int(input())
        arr = []
        for _ in range(100):
            lst = list(input().strip())
            arr.append(lst)

        new_arr = list(zip(*arr))

        ans = 101
        FIND = False
        for size in range(100, 0, -1):
            for i in range(100):
                for j in range(100):
                    if j + size > 100:
                        break
                    if check(arr[i][j:j + size]):
                        ans = size
                        FIND = True
                        break
                    if check(new_arr[i][j:j + size]):
                        ans = size
                        FIND = True
                        break
                if FIND:
                    break
            if FIND:
                break

        print(f"#{N} {ans}")
