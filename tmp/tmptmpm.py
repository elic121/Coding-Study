T = int(input())
for test_case in range(1, T + 1):
    arr = list(map(int,input().strip().split()))
    print("#"+test_case+" "+max(arr))