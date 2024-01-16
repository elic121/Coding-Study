dxs = [1, 1, 1, 0, 0, -1, -1, -1]
dys = [1, 0, -1, 1, -1, 1, 0, -1]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]


def distance(x1, y1, x2, y2):
    z1 = (x2 - x1) * (x2 - x1)
    z2 = (y2 - y1) * (y2 - y1)
    return z1 + z2


def condition(x, y):
    if (x < 0 or x >= N) or (y < 0 or y >= N):
        return False
    return True


def move_person(id):
    px, py = person_dict[id]
    cx, cy = c_pos

    pivot = distance(px, py, cx, cy)
    dist_list = []
    for X, Y in zip(dx, dy):
        nx, ny = px + X, py + Y
        if not condition(nx, ny):
            continue
        if person_list[nx][ny] >= 1:
            continue
        d = distance(nx, ny, cx, cy)
        if d < pivot:
            pivot = d
            dist_list.append((d, X, Y))

    if not dist_list:
        return 0, 0
    else:
        dist_list.sort()
        here = dist_list[0]
        return here[1], here[2]


def interaction(x, y, nx, ny):
    stack = []
    for s in range(N):
        ax, ay = x + nx * s, y + ny * s
        if not condition(ax, ay):
            break
        if person_list[ax][ay] == 0:
            break
        val = person_list[ax][ay]
        stack.append((val, ax, ay))
    return stack


def score():
    for key in person_dict:
        person_score[key] += 1


def move_cow():
    cx, cy = c_pos
    dist_list = []
    for key, val in person_dict.items():
        px, py = val
        d = distance(cx, cy, px, py)
        dist_list.append((d, px, py, key))

    dist_list.sort(key=lambda x: (x[0], -x[1], -x[2]))
    move = dist_list[0]

    kx, ky = person_dict[move[-1]]
    how_move = []
    for X, Y in zip(dxs, dys):
        nx, ny = cx + X, cy + Y
        if not condition(nx, ny):
            continue
        d = distance(kx, ky, nx, ny)
        how_move.append((d, X, Y))

    how_move.sort()
    lx, ly = how_move[0][1], how_move[0][2]
    return lx, ly


if __name__ == "__main__":
    t = int(input())
    for cs in range(1, t + 1):
        N, M, P, S, D = map(int, input().split())

        person_score = {}
        person_dict = {}
        person_list = [[0 for _ in range(N)] for _ in range(N)]
        person_stop = [0 for _ in range(P + 1)]
        for _ in range(1, P + 1):
            id, x, y = map(int, input().split())
            person_score[id] = 0
            person_dict[id] = (x, y)
            person_list[x][y] = id

        c_pos = tuple(map(int, input().split()))

        for m in range(M):
            if not person_dict:
                break
            hx, hy = move_cow()
            c_pos = c_pos[0] + hx, c_pos[1] + hy
            if person_list[c_pos[0]][c_pos[1]] >= 1:
                pid = person_list[c_pos[0]][c_pos[1]]
                person_score[pid] += S
                person_stop[pid] = 2

                person_list[c_pos[0]][c_pos[1]] = 0
                fx, fy = c_pos[0] + S * hx, c_pos[1] + S * hy

                if not condition(fx, fy):
                    del person_dict[pid]

                else:
                    push = interaction(fx, fy, hx, hy)
                    cnt = 0
                    for id, px, py in push:
                        mx, my = px + hx, py + hy
                        if not condition(mx, my):
                            break
                        cnt += 1
                        person_list[mx][my] = id
                        person_dict[id] = (mx, my)
                    for id, px, py in push[cnt:]:
                        del person_dict[id]

                    person_list[fx][fy] = pid
                    person_dict[pid] = (fx, fy)

            for id in range(1, P + 1):
                if id not in person_dict.keys():
                    continue
                if person_stop[id] >= 1:
                    person_stop[id] -= 1
                    continue

                hx, hy = move_person(id)
                if hx == 0 and hy == 0:
                    continue
                px, py = person_dict[id]
                person_list[px][py] = 0
                nx, ny = px + hx, py + hy

                if nx == c_pos[0] and ny == c_pos[1]:
                    person_score[id] += D
                    person_stop[id] = 1

                    fx, fy = nx - D * hx, ny - D * hy
                    if not condition(fx, fy):
                        del person_dict[id]

                    else:
                        push = interaction(fx, fy, -hx, -hy)
                        cnt = 0
                        for idx, px, py in push:
                            mx, my = px - hx, py - hy
                            if not condition(mx, my):
                                break
                            cnt += 1
                            person_list[mx][my] = idx
                            person_dict[idx] = (mx, my)

                        for idx, px, py in push[cnt:]:
                            del person_dict[idx]

                        person_list[fx][fy] = id
                        person_dict[id] = (fx, fy)

                else:
                    person_list[nx][ny] = id
                    person_dict[id] = (nx, ny)

            score()

        sc = []
        for val in person_score.values():
            sc.append(val)

        print(f"#{cs}", *sc)
