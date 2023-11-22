

def solution(n, computers):
    answer = 0
    visited = [0 for _ in range(n)]
    cnt = 0

    def dfs(i):
        for idx in range(n):
            if computers[i][idx] == 0:
                continue
            if visited[idx]:
                continue            
            visited[idx] = 1
            dfs(idx)
        return

    for i in range(n):
        if visited[i]:
            continue
        visited[i] = 1
        dfs(i)
        cnt += 1

    answer = cnt
    return answer


if __name__ == "__main__":
    solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]])
    solution(3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]])
