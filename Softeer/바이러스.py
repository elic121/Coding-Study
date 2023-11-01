# https://softeer.ai/practice/6284
import sys
s = sys.stdin.readline
if __name__ == "__main__":
    K, P, N = map(int,s().split())
    cnt = K
    for _ in range(N):
        cnt = (cnt * P) % 100_000_000_7

    print(cnt % 100_000_000_7)