from heapq import *

def solution(operations):
    answer = []
    # ascending order
    arr = []
    for ord, key in map(lambda x:x.split(),operations):
        key = int(key)
        if ord == 'I':
            heappush(arr,key)
        else:
            if not arr:
                continue
            if key == 1:
                m = max(arr)
                arr.remove(m)
            else:
                heappop(arr)

    if not arr:
        answer = [0, 0]
    else:
        m = max(arr)
        x = heappop(arr)
        answer = [m, x]

    return answer


if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        N = int(input())
        arr = []
        for _ in range(N):
            ord, key = input().split()
            key = int(key)
            if ord == 'I':
                heappush(arr,key)
            else:
                if not arr:
                    continue
                if key == 1:
                    m = max(arr)
                    arr.remove(m)
                else:
                    heappop(arr)
        
        if not arr:
            answer = 'EMPTY'
        else:
            m = max(arr)
            x = heappop(arr)
            answer = f"{m} {x}"
        
        print(answer)