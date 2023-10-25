

def c(arr):
    return sum([arr[idx] - max(max(arr[idx+1],arr[idx+2]),max(arr[idx-1],arr[idx-2]))\
        for idx in range(2,len(arr)-2) if max(max(arr[idx+1],arr[idx+2]),max(arr[idx-1],arr[idx-2]))<arr[idx]])

if __name__ == '__main__':
        # r = range;i = input;m = max
        # for q in r(10):
        #     _, a = i(), list(map(int, i().split()))
        #     print(
        #         f"#{q + 1} {sum([a[w] - m(m(a[w + 1], a[w + 2]), m(a[w - 1], a[w - 2])) for w in r(2, len(a) - 2) if m(m(a[w + 1], a[w + 2]), m(a[w - 1], a[w - 2])) < a[w]])}")
        for tc in range(1,11):
            cnt = 0
            _ = input()
            arr = list(map(int,input().split()))
            for idx in range(2,len(arr)-2):
                M1 = max(arr[idx-1],arr[idx-2])
                M2 = max(arr[idx+1],arr[idx+2])
                M = max(M1,M2)
                if M >= arr[idx]:
                    continue
                cnt += arr[idx] - M

            print(f"#{tc} {cnt}")
            # print(f"#{tc} {c(arr)}")




