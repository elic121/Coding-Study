# https://www.acmicpc.net/problem/1911
import sys
s = sys.stdin.readline
N, L = map(int,s().split())
lst = [tuple(map(int,s().split())) for _ in range(N)]
lst.sort(key=lambda x:x[0])

