import math
from collections import defaultdict

fees = [180, 5000, 10, 600]
records = [
    "05:34 5961 IN", "06:00 0000 IN",
    "06:34 0000 OUT", "07:59 5961 OUT",
    "07:59 0148 IN", "18:59 0000 IN",
    "19:09 0148 OUT", "22:59 5961 IN",
    "23:00 5961 OUT"
]


def hourTomin(t: str):
    h, m = t.split(':')
    return 60*int(h)+int(m)


def solution(fees, records):
    default_time, default_fee, \
        unit_time, unit_fee = fees
    lst = defaultdict(list)
    result = {}
    for info in records:
        T, N, E = info.split()
        lst[N].append((hourTomin(T), int(E == 'IN')))

    for key in lst.keys():
        anal = lst[key]
        L = len(anal)
        fee = 0;time_diff = 0
        for idx in range(L//2):
            t1 = anal[2*idx][0]
            t2 = anal[2*idx+1][0]
            time_diff += t2-t1

        if L % 2 != 0:
            time_diff += 1439 - anal[-1][0]

        extra_time = [0, time_diff-default_time][time_diff > default_time]
        fee = default_fee + math.ceil(extra_time/unit_time)*unit_fee
        result[key] = fee
    cResult = [(i,j) for i,j in result.items()] 
    return [x[1] for x in sorted(cResult,key = lambda x: x[0])]


print(solution(fees, records))
