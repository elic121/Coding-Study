# https://www.acmicpc.net/problem/4386

def find(parent, x):
    if parent[x] == x:
        return x
    return find(parent, parent[x])


def union(parent, a, b):
    a_find = find(parent, a)
    b_find = find(parent, b)

    if a_find > b_find:
        parent[a_find] = b_find
    else:
        parent[b_find] = a_find


def kruscal(edge):
    parent = [i for i in range(N)]
    edge.sort()

    cnt = 0
    for dist, x, y in edge:

        if find(parent, x) == find(parent, y):
            continue
        union(parent, x, y)
        cnt += dist

    return cnt


def distance(p1, p2):
    dx = (p2[0] - p1[0]) ** 2
    dy = (p2[1] - p1[1]) ** 2
    return (dx + dy) ** (1 / 2)


if __name__ == "__main__":
    N = int(input())
    pos = []
    for _ in range(N):
        x, y = map(float, input().split())
        pos.append((x, y))

    edge = []
    for i in range(N):
        for j in range(i + 1, N):
            dist = distance(pos[i], pos[j])
            edge.append((dist, i, j))

    print(kruscal(edge))
