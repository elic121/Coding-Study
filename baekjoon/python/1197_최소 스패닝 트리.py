# https://www.acmicpc.net/problem/1197


def find(parent, x):
    if parent[x] == x:
        return x
    return find(parent, parent[x])


def union(a, b, parent):
    a = find(parent, a)
    b = find(parent, b)
    if a > b:
        parent[a] = b
    else:
        parent[b] = a


def kruscal(edge):
    parent = [i for i in range(V)]
    cnt = 0

    edge.sort()
    for C, A, B in edge:
        if find(parent, A) == find(parent, B):
            continue
        cnt += C
        union(A, B, parent)

    return cnt


if __name__ == "__main__":
    V, E = map(int, input().split())
    edge = []
    for _ in range(E):
        A, B, C = map(lambda x: int(x) - 1, input().split())
        edge.append((C + 1, A, B))

    print(kruscal(edge))
