def check(idx):
    stack = ''
    cnt = 0
    for row in range(100):
        if arr[row][idx] != 0:
            stack += str(arr[row][idx])

    return stack.count('12')


def check2(idx):
    one = False
    cnt = 0
    for row in range(100):
        if arr[row][idx] == 1:
            one = True
            continue
        if arr[row][idx] == 2:
            if one:
                cnt += 1
                one = False
    return cnt


if __name__ == "__main__":
    for cs in range(1, 1 + 10):
        _ = input()
        arr = []
        for _ in range(100):
            lst = list(map(int, input().split()))
            arr.append(lst)
        C = 0
        for i in range(100):
            C += check2(i)

        print(f"#{cs} {C}")
