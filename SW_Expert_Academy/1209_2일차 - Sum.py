def sum_row():
    M = 0
    for r in range(100):
        s = sum(arr[r])
        M = max(M,s)
    return M

def sum_col():
    M = 0
    for c in range(100):
        s = 0
        for r in range(100):
            s += arr[r][c]
        M = max(s,M)

    return M

def sum_diagonal():
    M = 0
    s = 0
    for a in range(100):
        s += arr[a][a]
    M = max(M,s)
    s = 0
    for a in range(99,-1,-1):
        s += arr[a-99][a]
    M = max(M,s)

    return M

if __name__ == "__main__":
    for tc in range(1, 11):
        arr = []
        _ = input()
        for _ in range(100):
            lst = list(map(int, input().split()))
            arr.append(lst)

        sol1 = sum_row()
        sol2 = sum_col()
        sol3 = sum_diagonal()

        print(f"#{tc}",max([sol1,sol2,sol3]))
