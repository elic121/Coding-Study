
def sol1():
    for _ in range(N):
        max_num = 0
        min_num = 101
        max_idx = -1
        min_idx = -1
        for i in range(len(arr)):
            if arr[i] < min_num:
                min_num = arr[i]
                min_idx = i
            if arr[i] > max_num:
                max_num = arr[i]
                max_idx = i

        arr[max_idx] -= 1
        arr[min_idx] += 1

if __name__ == "__main__":
    for tc in range(1, 11):
        N = int(input())
        arr = list(map(int, input().split()))
        sol1()
        MAX = max(arr)
        MIN = min(arr)
        print(f"#{tc} {MAX-MIN}")
