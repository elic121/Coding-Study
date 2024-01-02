from collections import deque as Q
N,M=[-1,-1,-1,0,0,1,1,1],[-1,0,1,-1,1,-1,0,1]
def B(i,j):
    q=Q([(i,j)]);C[i][j]=-1
    while q:
        x,y=q.popleft()
        for X,Y in zip(N,M):
            k,l=x+X,y+Y
            if 0<=k<s and 0<=l<s and C[k][l]>=0:
                if C[k][l]==0:q.append((k,l))
                C[k][l]=-1
r,P=range,input
for l in r(int(P())):
    s,S=int(P()),0
    arr,C=[list(P()) for _ in r(s)],[[-1]*s for _ in r(s)]
    for i in r(s):
        for j in r(s):
            if arr[i][j]!='*':
                t=0
                for q,w in zip(N,M):
                    X,Y=i+q,j+w
                    t+=0<=X<s and 0<=Y<s and arr[X][Y]=='*'
                C[i][j]=t
    for i in r(s):
        for j in r(s):
            if C[i][j]==0:B(i,j);S+=1
    S+=sum(C[i][j]>0 for j in r(s) for i in r(s))
    print(f"#{l+1}",S)