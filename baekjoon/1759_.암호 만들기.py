# https://www.acmicpc.net/problem/1759
from sys import stdin
s = stdin.readline

L, C = map(int, s().split())
lst = s().strip().split()
lst.sort()
c, v = 0, 0

def solution1(n,tmp:list):
    global c,v

    if len(tmp) == L:
        if v<1 or c<2:
            return
        print(''.join(tmp))
        return
    
    for i in range(n,C):
        key = lst[i]
        
        c1, c2 = False, False

        if key in ['a','i','e','o','u']:
            v+=1
            c1 = True
        else:
            c+=1
            c2 = True

        tmp.append(key)
        solution1(i+1,tmp)

        if c1:
            v-=1
        else:
            c-=1

        tmp.pop()
    
    return

solution1(0,[])