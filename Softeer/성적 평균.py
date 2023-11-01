# https://softeer.ai/practice/6294
import sys

s = sys.stdin.readline

if __name__ == "__main__":
    N, K = map(int, s().split())
    arr = list(map(int, s().split()))
    i_sum = [0]
    for i in range(len(arr)):
        i_sum.append(i_sum[-1]+arr[i])
    for _ in range(K):
        a, b = map(int, s().split())
        val = (i_sum[b]-i_sum[a-1])/(b-a+1)
        print(f"{val:.2f}")