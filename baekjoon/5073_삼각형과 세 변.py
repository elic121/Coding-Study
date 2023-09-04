# https://www.acmicpc.net/problem/5073
import sys

s = sys.stdin.readline


def tri(a, b, c):
    if (a >= b + c) or (b >= a + c) or (c >= b + a):
        return "Invalid"
    if a == b == c:
        return "Equilateral"
    if a == b or b == c or a == c:
        return "Isosceles"
    return "Scalene"


while True:
    a, b, c = map(int, s().split())
    if a == 0 and b == 0 and c == 0:
        break
    print(tri(a, b, c))
