# 상우하좌
dx = [-1,0,1,0]
dy = [0,1,0,-1]

def clear_gun():
    for i in range(n):
        for j in range(n):
            if gun[i][j] and len(gun[i][j]) == 1 and gun[i][j][0] == 0:
                gun[i][j].clear()
def condition(x,y):
    if (x<0 or x>=n) or (y<0 or y>=n):
        return False
    return True

def move_lose(x,y,d):
    for i in range(4):
        nx, ny = x+dx[(d+i)%4], y+dy[(d+i)%4]
        if not condition(nx,ny):
            continue
        if player_pos[nx][ny]:
            continue
        return nx, ny, (d+i)%4
    return x,y,d
def choose_gun(x:list,y:int):
    if not x:
        return x,y

    if y <= 0:
        max_x = max(x)
        idx = x.index(max_x)
        x.pop(idx)
        return x, max_x

    max_x = max(x)
    max_y = y
    tmp = []
    if max_x > max_y:
        idx = x.index(max_x)
        for i in range(len(x)):
            if i == idx:
                continue
            tmp.append(x[i])

        if y > 0:
            tmp.append(y)

        return tmp, max_x

    if max_x == max_y:
        return x,y

    if max_x < max_y:
        return x,y

def check_gun(x,y):
    if not gun[nx][ny]:
        return False
    for val in gun[nx][ny]:
        if val > 0:
            return True
    return False

def who_win(x1,y1,x2,y2):
    z1 = x1 + y1
    z2 = x2 + y2
    if z1 > z2:
        return 0
    if z1 == z2:
        if x1 > x2:
            return 0
        if x1 < x2:
            return 1
    if z1 < z2:
        return 1
def check_player(x,y):
    return player_pos[x][y]
def move_player(x,y,d):
    nx, ny = x+dx[d], y+dy[d]
    if not condition(nx,ny):
        d = (d+2)%4
        nx, ny = x+dx[d], y+dy[d]
        return nx,ny,d
    return nx,ny,d


if __name__ == '__main__':
    n, m, k =map(int,input().split())
    gun = [[] for _ in range(n)]
    for i in range(n):
        lst = list(map(int,input().split()))
        for val in lst:
            gun[i].append([val])

    player_info = {}
    player_pos = [[0 for _ in range(n)] for _ in range(n)]
    player_score = [0 for _ in range(m+1)]
    for idx in range(m):
        x,y,d,s = map(int,input().split())
        player_info[idx+1] = [x-1,y-1,d,s,0]
        player_pos[x-1][y-1] = idx+1

    for cs in range(1,k+1):
        # print(f"<<<<<<  {cs}  >>>>>>")
        tmp_player = [[[] for _ in range(n)] for _ in range(n)]
        tmp_player_info = {}
        for key, val in player_info.items():
            idx = key
            x, y, d, s, g = val
            nx, ny, D = move_player(x,y,d)
            # print(f"{idx}번 {x,y}에서 {nx,ny}로 움직입니다.")

            # 이동경로에 플레이어가 없는 경우
            if not check_player(nx,ny):
                # print("이동 경로에 플레이어가 없습니다.")
                # 총이 있으면
                if check_gun(nx,ny):
                    # print("이동 경로에 총이 있습니다.")

                    # print(f"{nx,ny}에는 {gun[nx][ny]}의 총이 있습니다.")
                    tmp1, tmp2 = choose_gun(gun[nx][ny],g)

                    # print(f"최종적으로 {nx,ny}에는 {tmp1}, 플레이어는 {tmp2}의 총을 가집니다.")
                    gun[nx][ny] = tmp1
                    player_info[idx] = [nx,ny,D,s,tmp2]
                    player_pos[nx][ny] = idx
                    player_pos[x][y] = 0

                else:
                    # print("이동 경로에 총이 없습니다.")
                    player_info[idx] = [nx,ny,D,s,g]
                    player_pos[nx][ny] = idx
                    player_pos[x][y] = 0

            # 이동경로에 플레이어가 있는 경우
            else:
                enemy_idx = player_pos[nx][ny]
                ex, ey, ed, es, eg = player_info[enemy_idx]

                w = who_win(s,g,es,eg)
                # 이동한 플레이어가 이겼을 경우
                if w == 0:
                    # print(f"{idx}번 플레이어가 {enemy_idx}번 플레이어를 이겼습니다!!")
                    player_score[idx] += (s+g) - (es+eg)
                    # print(f"{idx}번 플레이어가 {(s+g) - (es+eg)}를 획득합니다!!")

                    player_pos[nx][ny] = idx
                    player_pos[x][y] = 0

                    # 진 사람은 총을 내려놓는다.
                    if eg > 0:
                        gun[ex][ey].append(eg)
                    player_info[enemy_idx][-1] = 0
                    # print(f"{enemy_idx}번은 {eg}짜리 총을 내려놓습니다.")

                    # 튄다.
                    # print("튀기 시작합니다!")

                    lx, ly, ld = move_lose(nx,ny,ed)
                    # print(f"{dx[ld]} {dy[ld]} 방향으로 갑니다.")
                    # print(f"{enemy_idx}번은 {lx,ly}로 튑니다.")
                    ltmp, lg = choose_gun(gun[lx][ly],0)
                    gun[lx][ly] = ltmp
                    player_info[enemy_idx] = [lx,ly,ld,es,lg]
                    player_pos[lx][ly] = enemy_idx

                    # 이긴 사람은 총을 줍줍.
                    tmp1, tmp2 = choose_gun(gun[nx][ny],g)
                    gun[nx][ny] = tmp1
                    player_info[idx] = [nx,ny,d,s,tmp2]

                # 이동한 플레이어가 졌을 경우
                else:
                    # print(f"{enemy_idx}번 플레이어가 {idx}번 플레이어를 이겼습니다!!")
                    player_score[enemy_idx] += (es+eg) - (s+g)
                    # print(f"{enemy_idx}번 플레이어가 {(es+eg) - (s+g)}를 획득합니다!!")

                    player_pos[nx][ny] = enemy_idx
                    player_pos[x][y] = 0

                    # 진 사람은 총을 내려놓는다.
                    if g > 0:
                        gun[nx][ny].append(g)
                    player_info[idx][-1] = 0
                    # print(f"{idx}번은 {g}짜리 총을 내려놓습니다.")


                    # print("튀기 시작합니다!!")
                    # print(f"{nx=} {ny=} {d=}")
                    lx, ly, ld = move_lose(nx, ny, d)
                    # print(f"{ld=}")
                    # print(f"{dx[ld]} {dy[ld]} 방향으로 갑니다.")
                    # print(f"{idx}번은 {lx, ly}로 튑니다.")
                    ltmp, lg = choose_gun(gun[lx][ly], 0)
                    gun[lx][ly] = ltmp
                    player_info[idx] = [lx, ly, ld, s, lg]
                    player_pos[lx][ly] = idx

                    tmp1, tmp2 = choose_gun(gun[nx][ny], eg)
                    gun[nx][ny] = tmp1
                    player_info[enemy_idx] = [nx, ny, ed, es, tmp2]

        clear_gun()

        # print(f"플레이어 위치")
        # for i in player_pos:
        #     print(*i)
        # print(f"총 위치")
        # for i in gun:
        #     print(*i)
        # print("플레이어 정보")
        # for key,val in player_info.items():
        #     print(f"{key}: {val}")
    print(*player_score[1:])