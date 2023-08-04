# https://www.acmicpc.net/problem/1987
import sys
s = sys.stdin.readline
R, C = map(int, s().split())
lst = [list(s().strip()) for _ in range(R)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
ans = 1
visited = [0 for _ in range(ord('Z')-ord('A')+1)]
def dfs(i, j, cnt):
    
    visited[ord(lst[i][j])-ord('A')] = 1
    
    global ans
    if ans < cnt:
        ans = cnt
        
    for k in range(4):
        nx, ny = i+dx[k], j+dy[k]
        if (nx < 0 or nx >= R) or (ny < 0 or ny >= C):
            continue
        if visited[ord(lst[nx][ny])-ord('A')]:
            continue
        dfs(nx, ny, cnt+1)
        visited[ord(lst[nx][ny])-ord('A')] = 0

dfs(0, 0, ans)
print(ans)
