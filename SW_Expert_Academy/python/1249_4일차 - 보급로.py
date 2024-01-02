from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def bfs():
    d = deque()
    d.append((0, 0))
    D = [[float('inf')] * N for _ in range(N)]
    D[0][0] = 0

    while d:
        x, y = d.popleft()
        for i, j in zip(dx, dy):
            nx, ny = x + i, y + j
            if (nx < 0 or nx >= N) or (ny < 0 or ny >= N):
                continue
            val = arr[nx][ny]

            if D[nx][ny] > D[x][y] + val:
                d.append((nx, ny))
                D[nx][ny] = D[x][y] + val

    return D[N - 1][N - 1]


if __name__ == "__main__":
    T = int(input())
    for tc in range(1, T + 1):
        N = int(input())
        arr = []
        for _ in range(N):
            lst = list(map(int, list(input())))
            arr.append(lst)
        cnt = bfs()
        print(f"#{tc} {cnt}")
    

# from collections import deque as Q
# X,Y=[0,1,0,-1],[1,0,-1,0]
# def B():
#     d=Q([(0,0)]);D=[[2001]*N for _ in r(N)];D[0][0]=0
#     while d:
#         x,y=d.popleft()
#         for i,j in zip(X,Y):
#             O,P=x+i,y+j
#             if 0<=O<N and 0<=P<N:
#                 V=D[x][y]+A[O][P]
#                 if D[O][P]>V:D[O][P]=V;d.append((O,P))
#     return D[N-1][N-1]
# I,p,r=int,input,range
# for tc in r(I(p())):
#     N=I(p());A=[list(map(I,list(p()))) for _ in r(N)]
#     print(f"#{tc+1}",B())