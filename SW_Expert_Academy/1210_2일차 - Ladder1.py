def condition(x, y):
    if (x < 0 or x >= 100) or (y < 0 or y >= 100):
        return False
    return True


def move_left(x, y):
    return x, y - 1


def move_right(x, y):
    return x, y + 1


def move_down(x, y):
    return x + 1, y


def move(x, y):
    vis = [[0 for _ in range(100)] for _ in range(100)]
    while True:
        if x == 99 and arr[x][y] == 2:
            return True
        elif x == 99 and arr[x][y] != 2:
            return False

        if condition(x, y - 1) and arr[x][y - 1] and not vis[x][y - 1]:
            x, y = move_left(x, y)
            vis[x][y] = 1
        elif condition(x, y + 1) and arr[x][y + 1] and not vis[x][y + 1]:
            x, y = move_right(x, y)
            vis[x][y] = 1
        elif condition(x + 1, y) and arr[x + 1][y]:
            x, y = move_down(x, y)
            vis[x][y] = 1


if __name__ == "__main__":
    for _ in range(10):
        N = int(input())
        arr = []
        for _ in range(100):
            lst = list(map(int, input().split()))
            arr.append(lst)

        for idx in range(100):
            if arr[0][idx] and move(0, idx):
                print(f"#{N} {idx}")
                break
