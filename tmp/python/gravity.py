arr = [
    [1, 0, 0, 0, 1],
    [0, 0, 0, 1, 1],
    [0, 1, 1, 0, 1],
    [1, 0, 0, 0, 1],
    [0, 0, 0, 0, 0],
]

for j in range(len(arr[0])):
    stack = []
    for i in range(5 - 1, -1, -1):
        if arr[i][j] == 1:
            stack.append(arr[i][j])
            arr[i][j] = 0
    for i in range(5 - 1, 5 - 1 - len(stack), -1):
        arr[i][j] = stack[5 - 1 - i]

for i in arr:
    print(*i)
