d=lambda x,y:abs(x[0]-y[0])+abs(x[1]-y[1])
def Q(n,C):
    global M
    if n==N+2:
        M=min(M,sum(d(A[C[i]],A[C[i-1]]) for i in r(1,N+2)))
        return
    for i in r(1,N+1):
        if C[i]:continue
        C[i]=n
        Q(n+1,C)
        C[i]=0
M,r,I,p=2401,range,int,input
for t in r(I(p())):
    N=I(p())
    L=list(map(I,p().split()))
    A=[(L[i],L[i+1]) for i in r(0,2*N+3,2)]
    C=[0]*(N+2);C[-1]=1
    Q(2,C)
    print(f"#{t+1}",M)
    M=2401
