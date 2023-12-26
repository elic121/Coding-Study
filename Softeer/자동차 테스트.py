import sys
import bisect
s = sys.stdin.readline

if __name__ == "__main__":
    n, q = map(int, s().split())
    arr = list(map(int, s().split()))
    visited = set(arr)
    arr.sort()
    for _ in range(q):
        N = int(s())
        if N not in visited:
            print(0)
            continue
        x = bisect.bisect_left(arr, N)
        ans = x * (n - x - 1)
        print([0,ans][ans > 0])