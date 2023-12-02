# https://www.acmicpc.net/problem/25425
N,M,a,K=map(int,input().split())
a-=K;print(min(a+1,N),1+(a%M>=1)+a//M)