# https://www.acmicpc.net/problem/9205
import sys
from collections import deque
s = sys.stdin.readline
test_N = int(s())


def dis(s: tuple, d: tuple):
    return abs(d[0]-s[0])+abs(d[1]-s[1])


def bfs(N, st_x, st_y, de_x, de_y, con_list):
    d = deque()
    d.append((st_x, st_y))
    visited = [0 for _ in range(N)]
    while d:
        pos = d.popleft()
        if dis(pos, (de_x, de_y)) <= 1000:
            print("happy")
            return
        for i in range(N):
            if not visited[i]:
                if dis(con_list[i], pos) <= 1000:
                    d.append(con_list[i])
                    visited[i] = 1
    print("sad")
    return


for _ in range(test_N):
    N = int(s())
    st_x, st_y = map(int, s().split())
    con_list = []
    for _ in range(N):
        x, y = map(int, s().split())
        con_list.append((x, y))
    de_x, de_y = map(int, s().split())
    bfs(N, st_x, st_y, de_x, de_y, con_list)