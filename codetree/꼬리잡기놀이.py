from collections import deque

# 서북동남
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
DIR = 2


def change_head_tail(d: deque):
    head_idx = 0
    tail_idx = 0
    for i in range(len(d)):
        if d[i] == 1:
            head_idx = i
            continue
        if d[i] == 3:
            tail_idx = i

    d[head_idx], d[tail_idx] = d[tail_idx], d[head_idx]
    return deque(q for q in d)


def line(round:int):
    share, remind = divmod(round, n)
    DIR = (2 + share) % 4
    if DIR == 2:
        return remind, 0, DIR
    if DIR == 3:
        return n - 1, remind, DIR
    if DIR == 0:
        return n - remind, n , DIR
    if DIR == 1:
        return 0, n - remind, DIR


def find_attack_pos(round):
    x, y, d = line(round)
    print(f"{x,y}에서 {d} 쪽으로 쏘기 시작. (0:서 1:북 2:동 3:남)")

    for c in range(n):
        nx, ny = x + dx[d] * c, y + dy[d] * c
        # print(f"{nx=} {ny=}")
        team_idx = team_pos[nx][ny]
        if team_idx >= 1:
            for idx in range(len(team_val[team_idx])):
                tx, ty = team[team_idx][idx]
                if nx == tx and ny == ty:
                    VAL = team_val[team_idx][idx]
                    if VAL == 4:
                        # print(f"{nx,ny}는 경로여서 취소.")
                        break
                    print(f"{nx, ny} 맞음")
                    print(f"{VAL=}")
                    if VAL == 3:
                        return team_cnt[team_idx], team_idx
                    if VAL == 1:
                        return 1, team_idx
                    if VAL == 2:
                        head_idx = team_val[team_idx].index(1)
                        if idx < head_idx:
                            for i in range(idx+1,head_idx+1):
                                if team_val[team_idx][i] == 3 or team_val[team_idx][i] == 4:
                                    return n - (head_idx-idx) + 1, team_idx
                                if team_val[team_idx][i] == 1:
                                    return head_idx-idx+1, team_idx
                        if idx > head_idx:
                            for i in range(head_idx+1,idx+1):
                                if team_val[team_idx][i] == 3 or team_val[team_idx][i] == 4:
                                    return n - (idx - head_idx) + 1, team_idx
                                if team_val[team_idx][i] == 1:
                                    return (idx - head_idx) + 1, team_idx


    return 0, 0

def check_move_direction(d:deque):
    head_idx = 0
    for i in range(len(d)):
        if d[i] == 1:
            head_idx = i
            break
    if d[(head_idx-1)%len(d)] == 2:
        return 1
    else:
        return -1

def condition(x, y):
    if (x < 0 or x >= n) or (y < 0 or y >= n):
        return False
    return True


pos = deque()


def clear_pos():
    pos.clear()


def dfs(i, j, visited):
    for X, Y in zip(dx, dy):
        nx, ny = i + X, j + Y
        if not condition(nx, ny):
            continue
        if arr[nx][ny] == 0:
            continue
        if visited[nx][ny] == 1:
            continue
        visited[nx][ny] = 1
        V[nx][ny] = 1
        pos.append((nx, ny))
        dfs(nx, ny, visited)

    return


if __name__ == '__main__':
    n, m, k = map(int, input().split())
    arr = []
    score = 0
    for _ in range(n):
        lst = list(map(int, input().split()))
        arr.append(lst)

    team = {}
    for i in range(1, m + 1):
        team[i] = []
    team_val = {}
    for i in range(1, m + 1):
        team_val[i] = deque()
    team_cnt = {}

    team_pos = [[0 for _ in range(n)] for _ in range(n)]

    t = 1
    V = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if V[i][j]:
                continue
            if arr[i][j] == 1:
                c = 0
                dfs(i, j, [[0 for _ in range(n)] for _ in range(n)])

                for x, y in pos:
                    team_pos[x][y] = t
                    if arr[x][y] != 4:
                        c += 1
                    team_val[t].append(arr[x][y])
                    team[t].append((x, y))

                team_cnt[t] = c

                clear_pos()
                t += 1

    # breakpoint()
    for r in range(k):
        print(f"<<<<<<<< {r} >>>>>>>>>")
        # print(team)
        # 머리사람을 따라서 한 칸 이동
        # print(team_val)
        for key,val in team_val.items():
            direction = check_move_direction(val)
            team_val[key].rotate(direction)

        # print(team)
        # print(team_val)
        # 공을 던짐.
        hit_score, hit_idx = find_attack_pos(r)
        score += hit_score * hit_score
        print(f"{hit_score**2}점 얻음.")
        try:
            # print(f"{hit_idx}번 reverse")
            team_val[hit_idx] = change_head_tail(team_val[hit_idx])
        except:
            pass
        # print(team_cnt)
        # print(team)
        # print(f"{team_val=}")

    print(score)