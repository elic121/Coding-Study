# https://softeer.ai/practice/6283

def sol():
    if arr == [i for i in range(1,9)]:
        return "ascending"
    if arr == [i for i in range(8,0,-1)]:
        return "descending"

    return "mixed"


if __name__ == '__main__':
    arr = list(map(int,input().split()))
    print(sol())
