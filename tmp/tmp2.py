a = [
 "0000011",
 "0000000",
 "0011100",
 "0010111",
 "0110010",
 "0011100",
 "0000000"
]

arr = [[int(j) for j in i] for i in a]
N=len(arr)
dx = [1,-1,0,0]
dy = [0,0,1,-1]

def dfs(x,y):
    arr[x][y] = 0
    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]
        if (nx<0 or nx>=N) or (ny<0 or ny>=N):
            continue
        if arr[nx][ny] == 0:
            continue
        dfs(nx,ny)

cnt = 0
for i in range(N):
    for j in range(N):
        if arr[i][j] == 1:
            dfs(i,j)
            cnt+=1
print(cnt)