# https://www.acmicpc.net/problem/17471
from sys import stdin
from collections import defaultdict
from collections import deque

# 1. 6개인 경우 1,5 2,4 3,3의 경우의 수가 있음
# 2. 경우의 수에 대해 조합을 함
# 3. 각 조합에 대해 bfs를 돌려 모두 탐색할 수 있는지 검사
# 4. 둘 다 모두 탐색이 가능한 경우 인구 차이를 구함
# 5. 최솟값 갱신

s = stdin.readline
N = int(s())
pop_lst = list(map(int,s().split()))
d = defaultdict(list)

for i in range(N):
    a,*b = map(int,s().split())
    for j in b:
        d[i+1].append(j)

def cal(pop_lst,lst):
    tmp = 0
    for i in lst:
        tmp += pop_lst[i-1]
    return tmp

def bfs(lst:list):
    Q = deque()
    Q.append(lst[0])
    visited = [0 for _ in range(N)]
    visited[lst[0]-1] = 1

    while Q:
        val = Q.popleft()
        for i in d[val]:
            if i not in lst:
                continue
            if visited[i-1]:
                continue
            Q.append(i)
            visited[i-1] = 1
    
    return sum(visited) == len(lst)

L = []
def combination(i,n,tmp:list):
    global L
    if len(tmp) == i:
        L.append(tmp.copy())
        return
    
    for idx in range(n,N):
        tmp.append(idx+1)
        combination(i,idx+1,tmp)
        tmp.pop()


minimum = 100_000_000_9
S = set([i for i in range(1,N+1)])
for i in range(1,N//2+1):
    combination(i,0,[])
    for lst in L:
        res1 = bfs(lst)
        if not res1:
            continue
        tmp2 = S.difference(set(lst))
        res2 = bfs(list(tmp2))
        if not res2:
            continue
        
        cal1 = cal(pop_lst,lst)
        cal2 = cal(pop_lst,list(tmp2))
        RES = abs(cal1-cal2)
        minimum = min(minimum,RES)

print([-1,minimum][minimum!=100_000_000_9])