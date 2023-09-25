# https://www.acmicpc.net/problem/15683
from sys import stdin
s = stdin.readline
cctv = {}

N, M = map(int,s().split())
arr = []
for i in range(N):
    lst = list(map(int,s().split())) 
    for idx,j in enumerate(lst):
        if j != 0:
            cctv[(i,idx)] = j
    arr.append(lst)

def check(lst):
    cnt = 0
    for i in range(N):
        for j in range(M):
            if lst[i][j] == '#':
                cnt+=1
    return cnt

def backtracking():
    return