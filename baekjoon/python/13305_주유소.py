# https://www.acmicpc.net/problem/13305
from sys import stdin
s = stdin.readline

if __name__ == "__main__":
    N = int(s())
    dist = list(map(int, s().split()))
    price = list(map(int, s().split()))
    total = 0
    idx = 0
    while idx < N:
        check = False
        pivot = price[idx]
        for next_idx in range(idx+1, N):
            # 기름 가격이 더 싸면
            if price[next_idx] < pivot:
                # 출발 위치부터 그 지역 전까지의 비용
                total += pivot * sum(dist[idx:next_idx])
                idx = next_idx
                check = True
                break

        if not check:
            total += pivot * sum(dist[idx:])
            break

    print(total)
