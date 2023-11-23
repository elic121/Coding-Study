# https://chan7ee.tistory.com/13

def mutiple(a: list, b: list):
    return sum([(x*y) % 1000 for x, y in zip(a, b)])


def square(a: list, b: list):
    tmp = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        row = a[i]
        for j in range(N):
            col = [b[x][j] for x in range(N)]
            tmp[i][j] = mutiple(row, col)
    return tmp


def devide_and_conquer(n):
    if n == 1:
        return arr

    if n % 2 == 0:
        new_arr = devide_and_conquer(n // 2)
        return square(new_arr, new_arr)
    else:
        new_arr = devide_and_conquer((n - 1) // 2)
        new_sol = square(new_arr, new_arr)
        return square(arr, new_sol)


if __name__ == "__main__":
    N, B = map(int, input().split())
    arr = []
    for _ in range(N):
        lst = list(map(int, input().split()))
        arr.append(lst)

    for l in devide_and_conquer(B):
        for val in l:
            print(val % 1000, end=' ')
        print()
