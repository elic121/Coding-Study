from collections import deque

# 하상좌우
dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

def rotate(x, y, n):
    global ex, ey, user
    tmp = [[0 for _ in range(n)] for _ in range(n)]
    tmp_user = [[[] for _ in range(n)] for _ in range(n)]
    for r in range(n):
        for c in range(n):
            tmp[r][c] = max(0, arr[x+r][y+c] - 1)
            tmp_user[r][c] = user[x+r][y+c]

    for i in range(n):
        for j in range(n):
            arr[x+i][y+j] = tmp[n - 1 - j][i]
            user[x+i][y+j] = tmp_user[n-1-j][i]

    ex, ey = ex - x, ey - y
    ex, ey = ey, n - 1 - ex
    ex, ey = ex + x, ey + y

def dist(x, y, ex, ey):
    return abs(ex - x) + abs(ey - y)

def move(x, y, ex, ey):
    D = dist(x, y, ex, ey)
    if D == 0:
        return x,y
    for X, Y in zip(dx, dy):
        nx, ny = x + X, y + Y
        if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
            continue
        if arr[nx][ny] > 0:
            continue
        if dist(nx, ny, ex, ey) < D:
            return nx, ny
    return x, y

def position(x,y,s):
    p = []
    sx, sy = x-s, y-s
    Ex, Ey = x+s, y+s

    for row in range(max(sx,0),min(Ex+1,N)):
        if row == sx or row == Ex:
            for col in range(max(sy,0),min(Ey+1,N)):
                p.append((row,col))
        else:
            for col in [sy,Ey]:
                p.append((row,col))
    return p

def find_square(ex, ey):
    global user
    find = False
    gx, gy = 0, 0
    l = 0

    POS = []
    for s in range(1, N):
        P = position(ex,ey,s)
        for nx, ny in P:
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                continue
            if arr[nx][ny] > 0:
                continue
            if not user[nx][ny]:
                continue
            POS.append((nx,ny))
            l = s
            find = True

        if find:
            break

    S = []
    for x, y in POS:
        for i in range(max(ex-l,0),min(ex+1,N)):
            for j in range(max(0,ey-l),min(ey+1,N)):
                if (i<=x<=i+l) and (j<=y<=j+l):
                    S.append((i,j))

    S.sort(key=lambda x:(x[0],x[1]))

    try:
        return S[0][0], S[0][1], l+1
    except:
        return None, None, None

if __name__ == "__main__":
    N, M, K = map(int, input().split())
    arr = []
    for _ in range(N):
        lst = list(map(int, input().split()))
        arr.append(lst)

    user = [[[] for _ in range(N)] for _ in range(N)]
    user_cnt = M
    for _ in range(M):
        x, y = map(lambda x: int(x) - 1, input().split())
        user[x][y].append(0)

    ex, ey = map(lambda x: int(x) - 1, input().split())

    distance = 0
    for sec in range(1,K+1):
        tmp = [[[] for _ in range(N)] for _ in range(N)]
        for i in range(N):
            for j in range(N):
                if user[i][j]:
                    mx, my = move(i,j,ex,ey)
                    if mx == ex and my == ey:
                        distance += sum([q+1 for q in user[i][j]])
                    elif i == mx and j == my:
                        for val in user[i][j]:
                            tmp[mx][my].append(val)
                    else:
                        for val in user[i][j]:
                            tmp[mx][my].append(val+1)
        user = tmp

        fx, fy, d = find_square(ex,ey)
        if fx == None:
            break

        rotate(fx, fy, d)

    for i in range(N):
        for j in range(N):
            if user[i][j]:
                distance += sum(user[i][j])

    print(distance)
    print(ex+1,ey+1)
