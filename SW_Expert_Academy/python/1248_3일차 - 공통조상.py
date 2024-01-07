def H(n,v):
    D[v]=n;U=1
    if v in A:
        for c in A[v]:P[c]=v;U+=H(n+1,c)
    C[v]=U
    return U
def L(x,y):
    while D[x]!=D[y]:
        if D[x]>D[y]:x=P[x]
        else:y=P[y]
    while P[x]!=P[y]:x=P[x];y=P[y]
    return P[x]
I,r,S,M=int,range,input,map
for t in r(I(S())):
    V,E,n,m=M(I,S().split())
    P,D,C,A=[0]*(V+1),[0]*(V+1),[0]*(V+1),{}
    a=list(M(I,S().split()))
    for i in r(E):k=a[2*i];A[k]=A.get(k,[])+[a[2*i+1]]
    H(0,1);X=L(n,m);print(f"#{t+1}",X,C[X])
    