def solution(N, M, d):
    d.sort()
    d_max = d[-1]
    s, e = 0, d_max-1
    height = 0
    while s <= e:
        mid = (e+s)//2
        cnt = 0
        for i in d:
            cnt += max(i-mid, 0)

        if cnt < M:
            e = mid - 1
        elif cnt >= M:
            s = mid + 1
            height = mid

    return height


N, M = 4, 6
d = [19, 15, 10, 17]
print(solution(N, M, d))
