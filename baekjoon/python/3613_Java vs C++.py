# https://www.acmicpc.net/problem/3613

def check_c(l: str):
    if l.count('__') >= 1:
        return False

    for i in l:
        if i == '_':
            continue
        if i.isupper():
            return False

    if (l and l[0] == '_') or (l and l[-1] == '_'):
        return False

    for val in l.split('_'):
        if val and val[0].isupper():
            return False

    return True


def check_java(l: str):
    return not (l.count('__') >= 1 or l[0].isupper() or l.count('_') >= 1)


if __name__ == "__main__":

    l = input().strip()
    cc = check_c(l)
    cj = check_java(l)
    sol = ''

    if cc and not cj:
        tmp = l.split('_')
        arr = [tmp[0]]
        for idx in range(1, len(tmp)):
            if not tmp[idx]:
                continue
            tmpL = tmp[idx][0].upper() + tmp[idx][1:]
            arr.append(tmpL)

        sol = ''.join(arr)

    elif not cc and cj:
        arr = []
        tmp = []
        for i in l:
            if i.isupper():
                tmpL = ''.join(tmp)
                arr.append(tmpL)
                tmp.clear()
                tmp.append(i.lower())
            else:
                tmp.append(i)
        if tmp:
            arr.append(''.join(tmp))

        sol = '_'.join(arr)

    elif not cc and not cj:
        sol = 'Error!'
    else:
        sol = l

    print(sol)
