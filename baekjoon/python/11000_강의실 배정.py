import sys
from heapq import heappop, heappush
s = sys.stdin.readline


if __name__ == "__main__":
    N = int(s())
    arr = []
    for _ in range(N):
        a, b = map(int, s().split())
        arr.append((a, b))
    arr.sort()

    room = []
    heappush(room, arr[0][1])    
    for i in range(1, N):
        if arr[i][0] < room[0]:
            heappush(room,arr[i][1])
        else:
            heappop(room)
            heappush(room,arr[i][1])

    print(len(room))
    