if __name__ == '__main__':
    for _ in range(10):
        n = int(input())
        s = input().strip()
        l = input().strip()
        print(f"#{n} {l.count(s)}")
