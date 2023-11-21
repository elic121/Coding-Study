from collections import defaultdict


def sol1():
    arr.sort()
    for i in range(N):
        pivot1 = arr[i]
        cnt = 1
        for j in range(i + 1, N):
            pivot2 = arr[j]
            if pivot2 - pivot1 <= K:
                cnt += 1
            else:
                break
        M = max(M, cnt)

    return


if __name__ == '__main__':
    T = int(input())
    for tc in range(1, T + 1):
        N, K = map(int, input().split())
        arr = list(map(int, input().split()))
        M = 0
        d = defaultdict(int)

        for val in arr:
            d[val] += 1

        for key in sorted(d.keys()):
            cnt = d[key]
            for plus in range(1, K + 1):
                if key + plus in d.keys():
                    cnt += d[key + plus]
            M = max(M, cnt)

        print(f"#{tc} {M}")

"""
N명의 선수들에 대해 선수들의 실력 값이 차례대로 주어진다.
이 선수들 중 일부를 골라서 팀을 구성할 것이다.
유일한 조건은 실력 차이가 K를 초과하는 선수들이 팀에 선발되면 안된다는 것이다.
조건을 만족하며 최대 인원이 되도록 팀을 구성할 때 그 인원 수를 제시하라.

예를 들어, N = 4, K = 2이고 선수들의 실력이 각각 6, 4, 2, 3인 경우를 생각하자.
실력이 2, 3, 4인 선수들을 선발하여 총 3명인 팀을 구성하는 것은 가능하다.
그러나, 총 4명인 팀을 구성할 방법은 없다.

[제약사항]
    1.     선수의 수 N은 1 이상 500 이하이다. (1 ≤ N ≤ 500)
    2.     선수들의 실력 값은 1이상 1000 이하의 정수이다.
    3.     K의 값은 1 이상 1000 이하의 정수이다.

[입력]
가장 첫 줄에는 테스트 케이스의 총 수가 주어진다.
그 다음 줄부터 각 테스트 케이스가 주어지며, 각 테스트 케이스는 2 줄로 구성된다.
각 테스트 케이스의 첫 줄에는 N과 K가 주어진다.
다음 줄에는 선수들의 실력 값들이 주어진다.

[출력]
출력의 각 줄은 ‘#x’로 시작하고, 공백을 한 칸 두고 만들 수 있는 가장 큰 팀의 인원 수를 출력한다. 단, x는 테스트 케이스의 번호이다.
"""
