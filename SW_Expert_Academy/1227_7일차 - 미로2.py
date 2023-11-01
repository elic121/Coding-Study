from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def bfs():
    visited = [[0 for _ in range(100)] for _ in range(100)]
    visited[stt[0]][stt[1]] = 1
    d = deque()
    d.append((stt[0], stt[1]))

    while d:
        x, y = d.popleft()
        if x == end[0] and y == end[1]:
            return True+0
        for i, j in zip(dx, dy):
            nx, ny = x + i, y + j
            if (nx < 0 or nx >= 100) or (ny < 0 or ny >= 100):
                continue
            if visited[nx][ny]:
                continue
            if arr[nx][ny] == 1:
                continue
            visited[nx][ny] = 1
            d.append((nx, ny))

    return False+0


if __name__ == "__main__":
    for _ in range(10):
        N = int(input())
        arr = []
        stt = (0, 0)
        end = (-1, -1)
        for i in range(100):
            lst = list(map(int, list(input())))
            for j in range(100):
                if lst[j] == 2:
                    stt = (i, j)
                if lst[j] == 3:
                    end = (i, j)
            arr.append(lst)

        print(f"#{N} {bfs()}")
