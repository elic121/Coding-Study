from sys import stdin
s = stdin.readline

MIN = 100_000_000_9

def cal(tmp):
    cnt = 0
    for idx in range(len(tmp)):
        a = arr[tmp[idx]][tmp[(idx+1)%len(tmp)]]
        if a == 0:return 100_000_000_9
        cnt += a
    return cnt

def backTracking(visited, tmp):
    global MIN
    if len(tmp) == N:
        val = cal(tmp)
        MIN = min(MIN, val)
        return 
    
    for i in range(N):
        if visited[i]:continue
        visited[i] = 1
        tmp.append(i)
        backTracking(visited, tmp)
        tmp.pop()
        visited[i] = 0

if __name__ == "__main__":
    N = int(s())
    arr = []
    for _ in range(N):
        lst = list(map(int,input().split()))
        arr.append(lst)

    backTracking([0 for _ in range(N)], [])
    print(MIN)