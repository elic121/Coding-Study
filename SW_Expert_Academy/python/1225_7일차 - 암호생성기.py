from collections import deque


def make_code():
    cnt = 1
    while True:
        P = d.popleft()
        P = max(0, P - cnt)
        d.append(P)
        if P == 0:
            return False
        cnt += 1
        if cnt == 6:
            return True


if __name__ == "__main__":
    while True:
        try:
            T = int(input())
            d = deque()
            for i in map(int, input().split()):
                d.append(i)

            while True:
                if make_code():
                    continue
                break

            print(f"#{T}", *list(d))
        except:
            break
