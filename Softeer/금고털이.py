# https://softeer.ai/practice/6288

import sys

s = sys.stdin.readline

if __name__ == "__main__":
    W, N = map(int, s().split())
    arr = []
    for _ in range(N):
        M, P = map(int, s().split())
        arr.append((P, M))

    arr.sort(key=lambda x: (-x[0]))
    score = 0
    for P, M in arr:
        weight = min(W, M)
        score += weight * P
        W -= weight
        if W <= 0:
            break

    print(score)
