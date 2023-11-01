M = {}


def backtracking(n, tmp, goal):
    global M
    if n == N:
        cnt = 0
        for i in range(N):
            if tmp[i]:
                cnt += arr[i]
        if cnt not in M:
            M[cnt] = 1
        else:
            pass
        return

    for i in range(N):
        tmp.append(i)
        backtracking(n + 1, tmp, goal)
        tmp.pop()

    return


def solution2():
    visited = [0 for _ in range(sum(arr) + 1)]
    visited[0] = 1

    for score in arr:
        for idx in range(len(visited) - 1, -1, -1):
            if visited[idx]:
                visited[idx + score] = 1

    return sum(visited)


if __name__ == "__main__":
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        arr = list(map(int, input().split()))
        # for cnt in range(1, N + 1):
        #     backtracking(0, [], cnt)
        print(f"#{i} {solution2()}")
        M.clear()
