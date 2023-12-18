import sys
s = sys.stdin.readline

if __name__ == "__main__":
    N = int(s())
    arr = list(map(int,s().split()))
    sol = [1 for _ in range(N)]
    for i in range(N):
        for j in range(i+1,N):
            if arr[i] < arr[j]:
                sol[j] = max(sol[j], sol[i] + 1)
    
    print(max(sol))
            