# https://www.acmicpc.net/problem/2910
from collections import Counter
if __name__ == "__main__":
    N, C = map(int, input().split())
    arr = list(map(int, input().split()))

    # method 1
    arr_to_dict = dict(Counter(arr))
    dict_key_descend = sorted(
        list(arr_to_dict.keys()), key=lambda x: -arr_to_dict[x])

    for key in dict_key_descend:
        for _ in range(arr_to_dict[key]):
            print(key, end=' ')

    # method 2
    key = Counter(arr).most_common()
    for val, it in key:
        for _ in range(it):
            print(val, end=' ')

    # method 3
    new_arr = sorted(arr, key=lambda x: (-arr.count(x), arr.index(x)))
    print(*new_arr)