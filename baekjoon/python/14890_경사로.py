# https://www.acmicpc.net/problem/14890
from sys import stdin

s = stdin.readline

N, L = map(int, s().split())
arr = []
for _ in range(N):
    lst = list(map(int, s().split()))
    arr.append(lst)

C = 0
for i in range(N):
    stt = 0
    visited = [0 for _ in range(N)]

    while True:
        if stt >= N - 1:
            C += 1
            break
        val1 = arr[i][stt]
        val2 = arr[i][stt + 1]
        # 높이가 같으면 다음으로 넘어감
        if val1 == val2:
            stt += 1
            continue
        # 높이가 2이상 차이나면 끝
        if abs(val2 - val1) >= 2:
            break

        else:
            IMP = False

            # 다음이 더 높은 경우
            if val2 - val1 == 1:
                cnt = 0
                # 이전 L개 만큼 높이가 같은지 조사
                for idx in range(stt, max(stt - L, -1), -1):
                    # 이미 경사로가 있으면 끝
                    if visited[idx] == 1:
                        IMP = True
                        break
                    if arr[i][idx] == val1:
                        cnt += 1
                    else:
                        break

                if IMP:
                    break

                # 경사로를 놓을 수 있으면 방문처리
                if cnt == L:
                    for k in range(stt, max(stt - L, -1), -1):
                        visited[k] = 1
                    stt += 1
                else:
                    break

            # 이전이 더 높은 경우
            else:
                cnt = 0
                # 본인포함 L개 만큼 높이가 같은지 조사
                for idx in range(stt + 1, min(stt + 1 + L, N)):
                    # 이미 경사로가 있으면 끝
                    if visited[idx] == 1:
                        IMP = True
                        break
                    if arr[i][idx] == val2:
                        cnt += 1
                    else:
                        break

                if IMP:
                    break

                # 경사로를 놓을 수 있으면 방문처리
                if cnt == L:
                    for k in range(stt + 1, min(stt + 1 + L, N)):
                        visited[k] = 1
                    stt += L
                else:
                    break

for j in range(N):
    stt = 0
    visited = [0 for _ in range(N)]

    while True:
        if stt >= N - 1:
            C += 1
            break
        val1 = arr[stt][j]
        val2 = arr[stt + 1][j]

        if val1 == val2:
            stt += 1
            continue

        if abs(val2 - val1) >= 2:
            break

        else:
            IMP = False

            if val2 - val1 == 1:
                cnt = 0
                for idx in range(stt, max(stt - L, -1), -1):
                    if visited[idx] == 1:
                        IMP = True
                        break
                    if arr[idx][j] == val1:
                        cnt += 1
                    else:
                        break

                if IMP:
                    break

                if cnt == L:
                    for k in range(stt, max(stt - L, -1), -1):
                        visited[k] = 1
                    stt += 1
                else:
                    break

            else:
                cnt = 0
                for idx in range(stt + 1, min(stt + 1 + L, N)):
                    if visited[idx] == 1:
                        IMP = True
                        break
                    if arr[idx][j] == val2:
                        cnt += 1
                    else:
                        break

                if IMP:
                    break

                if cnt == L:
                    for k in range(stt + 1, min(stt + 1 + L, N)):
                        visited[k] = 1
                    stt += L
                else:
                    break

print(C)
