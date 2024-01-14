import sys
sys.setrecursionlimit(10**8)
s = sys.stdin.readline

N = int(s())
R = 1_000_000_007

def mul_matrix(a, b):
    tmp = [[0]*2 for _ in range(2)]
    for i in range(2):
        for j in range(2):
            for k in range(2):
                tmp[i][j] += (a[i][k]*b[k][j]) % R
            tmp[i][j] %= R
    return tmp

def divide_and_conquer(n):
    if n <= 1:return [[1,1],[1,0]]
    
    sol = divide_and_conquer(n//2)
    mat = mul_matrix(sol, sol)
    if n%2 == 0:return mat
    else:return mul_matrix([[1,1],[1,0]], mat)

print(divide_and_conquer(N-1)[0][0])