# https://www.acmicpc.net/problem/6324


def output(tc, protocol, host, port, path):
    print(f"URL #{tc}")
    print(f"Protocol = {protocol}")
    print(f"Host     = {host}")
    print(f"Port     = {port}")
    print(f"Path     = {path}")
    print()


def separate_protocol(L):
    a = L.split('://')
    tmp = '://'.join(a[1:])
    return a[0], tmp


def separate_path(L):
    if L.count('/') == 0:
        return '<default>', L

    arr = L.split('/')
    path = '/'.join(arr[1:])
    return path, arr[0]


def separate_port(L):
    if L.count(':') != 1:
        return '<default>', L

    arr = L.split(':')
    return arr[1], arr[0]


if __name__ == '__main__':
    T = int(input())
    for tc in range(1, T + 1):
        L = input().strip()
        protocol, L = separate_protocol(L)
        # print(protocol, L)
        path, L = separate_path(L)
        # print(path, L)
        port, L = separate_port(L)
        # print(port, L)

        output(tc, protocol, L, port, path)
