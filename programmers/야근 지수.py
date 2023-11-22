from heapq import *

M = 1000000009
def backtracking(idx, max_hour, res, works):
    global M
    if idx == len(works):
        if max_hour != 0:
            return
        M = min(M, res)
        return

    for i in range(min(works[idx], max_hour + 1)):
        remain = works[idx] - i
        backtracking(idx+1, max_hour - i, res + remain * remain, works)
    return


def solution(n, works):
    global M
    M = 1000000009
    answer = 0
    if sum(works) <= n:
        return 0
    backtracking(0, n, 0, works)
    answer = M
    return answer


def solution2(n, works):
    if sum(works) <= n:
        return 0
    arr = []
    for val in works:
        heappush(arr, -val)
    for _ in range(n):
        x = heappop(arr)
        heappush(arr, x+1)

    return sum([x*x for x in arr])


if __name__ == "__main__":
    # print(solution(4, [4, 3, 3]))
    # print(solution(1, [2, 1, 2]))
    # print(solution(3, [1, 1]))

    print(solution2(4, [4, 3, 3]))
    print(solution2(1, [2, 1, 2]))
    print(solution2(3, [1, 1]))
