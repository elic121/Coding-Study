from collections import deque
def condition(x,y):
    if (x<0 or x>=N) or (y<0 or y>=N):
        return False
    return True

dx = [0,1,0,-1]
dy = [1,0,-1,0]

def boundary(n1, n2):
    cnt = 0
    for i in range(N):
        for j in range(N):
            if new_arr[i][j] == n1:
                for X,Y in zip(dx,dy):
                    nx, ny = i+X, j+Y
                    if not condition(nx,ny):
                        continue
                    if new_arr[nx][ny] != n2:
                        continue
                    cnt += 1
    return cnt


def bfs(i,j,cnt):
    d = deque()
    d.append((i,j))
    visited[i][j] = 1
    val = arr[i][j]
    new_arr[i][j] = cnt
    C = 1
    while d:
        x, y = d.popleft()
        for X,Y in zip(dx,dy):
            nx, ny = x+X, y+Y
            if not condition(nx,ny):
                continue
            if visited[nx][ny] == 1:
                continue
            if arr[nx][ny] != val:
                continue
            new_arr[nx][ny] = cnt
            visited[nx][ny] = 1
            d.append((nx,ny))
            C += 1
    return C


comb = []
def combination(n,l,key):
    if len(l) == 2:
        comb.append([idx for idx in l])
        return

    for i in range(n,key):
        l.append(i)
        combination(i+1,l,key)
        l.pop()

def rotate(N):
    n = N//2
    tmp = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if not (i==n or j==n):
                continue
            tmp[i][j] = arr[j][N-1-i]

    for i in range(n):
        for j in range(n):
            tmp[i][j] = arr[n-1-j][i]
            tmp[n+i+1][j] = arr[2*n-j][i]
            tmp[i][n+j+1] = arr[n-1-j][n+i+1]
            tmp[n+i+1][n+j+1] = arr[2*n-j][n+i+1]
    return tmp

if __name__ == '__main__':
    N = int(input())
    arr = []
    for _ in range(N):
        lst = list(map(int, input().split()))
        arr.append(lst)


    total = 0
    for _ in range(4):
        sub_total = 0
        visited = [[0 for _ in range(N)] for _ in range(N)]
        new_arr = [[0 for _ in range(N)] for _ in range(N)]
        key = 1
        block = {}
        for i in range(N):
            for j in range(N):
                if visited[i][j] == 0:
                    origin = arr[i][j]
                    c = bfs(i,j,key)
                    block[key] = [c,origin]
                    key += 1

        combination(1, [], key)

        for n1,n2 in comb:
            b_cnt = boundary(n1,n2)
            val = block[n1][1]*block[n2][1]*(block[n1][0]+block[n2][0])*b_cnt
            sub_total += val
        comb.clear()

        arr = rotate(N)
        total += sub_total

    print(total)