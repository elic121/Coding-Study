# https://www.acmicpc.net/problem/12865
from sys import stdin
s = stdin.readline
N, K = map(int,s().split())
D = []
weight = []
value = []
for _ in range(N):
    W, V = map(int,s().split())
    # D.append((W,V))
    weight.append(W)
    value.append(V)
# D.sort(key=lambda x:(-x[1]/x[0]))

MAX = 0
def solution1(n,val,weight):
    global MAX

    if weight > K:
        return
    else:
        MAX = max(MAX,val)

    if n == N:
        return
    
    for i in range(n,N):
        newVal = val + D[i][1]
        newWeight = weight + D[i][0] 
        solution1(n+1,newVal,newWeight)
        solution1(n+1,val,weight)

    return

def solution2(N,K,weight,value):
    lst = [[0 for _ in range(K+1)] for _ in range(N+1)]
    for i in range(N+1):
        for j in range(K+1):
            if i==0 or j==0:
                lst[i][j] = 0
            elif weight[i-1] <= j:
                lst[i][j] = max(lst[i-1][j-weight[i-1]]+value[i-1],lst[i-1][j])
            else:
                lst[i][j] = lst[i-1][j]
    
    return lst[N][K]

# solution1(0,0,0)
# print(MAX)

print(solution2(N,K,weight,value))