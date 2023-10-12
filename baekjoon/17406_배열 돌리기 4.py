# https://www.acmicpc.net/problem/17406
def minrow(lst):
    c = sum(lst[0])
    for r in range(1,N):
        c = min(c,sum(lst[r]))
    return c

def backtracking(ord,v):
    global K, N, M, MIN, command
    if len(ord) == K:
        tmp = [[0 for _ in range(M)] for _ in range(N)]
        for i in range(N):
            for j in range(M):
                # breakpoint()
                tmp[i][j] = arr[i][j]
        for idx in ord:
            # tmp = rotate(tmp, command[idx])
            r, c, s = command[idx]
            r, c = r - 1, c - 1
            for dist in range(1, s + 1):
                stack = []
                for col in range(c-dist,c+dist):
                    stack.append(tmp[r-dist][col])
                for row in range(r-dist,r+dist):
                    stack.append(tmp[row][c+dist])
                for col in range(c+dist,c-dist,-1):
                    stack.append(tmp[r+dist][col])
                for row in range(r+dist,r-dist,-1):
                    stack.append(tmp[row][c-dist])

                idx = 0
                new_stack = [stack[-1]] + stack[:-1]
                for col in range(c-dist,c+dist):
                    tmp[r - dist][col] = new_stack[idx]
                    idx+=1
                for row in range(r-dist,r+dist):
                    tmp[row][c+dist] = new_stack[idx]
                    idx+=1
                for col in range(c+dist,c-dist,-1):
                    tmp[r+dist][col] = new_stack[idx]
                    idx+=1
                for row in range(r+dist,r-dist,-1):
                    tmp[row][c-dist] = new_stack[idx]
                    idx+=1

        res = minrow(tmp)
        MIN = min(res, MIN)

        return

    for i in range(K):
        if v[i] == 1:
            continue
        v[i] = 1
        ord.append(i)
        backtracking(ord,v)
        v[i] = 0
        ord.pop()

if __name__ == '__main__':
    MIN = 100_000_000_9
    N, M, K = map(int,input().split())
    arr = []
    command = []
    for _ in range(N):
        lst = list(map(int,input().split()))
        arr.append(lst)
    for _ in range(K):
        r,c,s = map(int,input().split())
        command.append((r,c,s))
    backtracking([],[0 for _ in range(K)])
    print(MIN)