N = 8
arr = [[0 for _ in range(N)] for _ in range(N)]
CNT = 0


def dac(x, y, r, c):
    global CNT
    if r == 2 or c == 2:
        for i in range(x, x + r):
            for j in range(y, y + c):
                CNT += 1
                arr[i][j] = CNT
    else:
        k = r // 2
        dx = [0, 0, k, k]
        dy = [0, k, 0, k]
        for idx in range(4):
            return dac(x + dx[idx], y + dy[idx], k, k)


dac(0, 0, N, N)
for i in arr:
    print(*i)
