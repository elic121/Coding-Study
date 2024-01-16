def convert(num, n):
    tmp = []
    d = {10: "A", 11: "B", 12: "C", 13: "D", 14: "E", 15: "F"}
    while num >= n:
        num, b = divmod(num, n)
        if b >= 10:
            b = d[b]
        tmp.append(str(b))
    tmp.append(str(num))
    return "".join(list(reversed(tmp)))


print(convert(45, 16))
