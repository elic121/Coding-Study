# https://www.acmicpc.net/problem/5648
import sys
if __name__ == "__main__":
    n, *arr = sys.stdin.read().split() 
    sol = []
    for val in arr:
        tmp = str(val)
        tmp2 = int(tmp[::-1])
        sol.append(tmp2)
    for val in sorted(sol):
        print(val)