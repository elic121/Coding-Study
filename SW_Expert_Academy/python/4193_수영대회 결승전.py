from collections import deque as L
def B():
    global S
    d=L([(g,h,0)]);V=[[0]*N for _ in r(N)]
    V[g][h]=1
    while d:
        x,y,s=d.popleft()
        if x==j and y==k:S=s;break
        for q,w in zip(Q,W):
            X,Y=x+q,y+w
            if X<0 or X>=N or Y<0 or Y>=N or V[X][Y] or A[X][Y]==1:continue
            if A[X][Y]==0 or(A[X][Y]==2 and s%3==2):V[X][Y]=1;d.append((X,Y,s+1))
            else:V[x][y]=1;d.append((x,y,s+1))
Q,W=[0,1,0,-1],[1,0,-1,0]
r,P,I,m=range,input,int,map
for T in r(I(P())):
    S,N=250,I(P())
    A=[list(m(I,P().split())) for _ in r(N)]
    g,h=m(I,P().split());j,k=m(I,P().split())
    B();print(f"#{T+1}",[-1,S][S<250])