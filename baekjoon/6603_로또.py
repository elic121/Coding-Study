# https://www.acmicpc.net/problem/6603
from sys import stdin
s = stdin.readline

def combination(n,tmp:list):
    
    if len(tmp) == 6:
        print(*tmp)
        return
    
    for i in range(n,N):
        tmp.append(arr[i])
        combination(i+1,tmp)
        tmp.pop()

while True:
    N,*arr = map(int,s().split())
    if N == 0:
        break
    combination(0,[])
    print()
