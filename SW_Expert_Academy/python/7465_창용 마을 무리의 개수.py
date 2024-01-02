def f(x):return x if x==q[x]else f(q[x])
def u(a,b):x,y=f(a),f(b);q[min(x,y)]=max(x,y)
p,r,n,m=input,range,int,map;T=n(p())
for t in r(T):
    N,M=m(n,p().split());q=list(r(N+1))
    for _ in r(M):u(*m(n,p().split()))
    print(f"#{t+1}",sum(q[i]==i for i in r(1,N+1)))