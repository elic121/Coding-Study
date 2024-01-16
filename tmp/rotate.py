arr = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]


def rotate(arr):
    row, col = len(arr[0]), len(arr)
    tmp = [[0 for _ in range(col)] for _ in range(row)]
    for i in range(row):
        for j in range(col):
            tmp[i][j] = arr[col - 1 - j][i]

    for i in tmp:
        print(*i)


def rotateC(arr):
    row, col = len(arr[0]), len(arr)
    tmp = [[0 for _ in range(col)] for _ in range(row)]
    for i in range(row):
        for j in range(col):
            tmp[i][j] = arr[j][row - 1 - i]

    for i in tmp:
        print(*i)


def rotateR(arr):
    row, col = len(arr), len(arr[0])
    tmp = [[0 for _ in range(col)] for _ in range(row)]
    for i in range(row):
        for j in range(col):
            tmp[i][j] = arr[row - 1 - i][col - 1 - j]

    for i in tmp:
        print(*i)


if __name__ == "__main__":
    arr = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
    rotate(arr)
    print()
    rotateC(arr)
    print()
    rotateR(arr)
