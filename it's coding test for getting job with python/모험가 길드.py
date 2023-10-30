N = 5
arr = [2, 3, 1, 2, 2]
arr.sort()
result = 0
count = 0
for i in arr:
    count += 1
    if count >= i:
        result += 1
        count = 0
print(result)
