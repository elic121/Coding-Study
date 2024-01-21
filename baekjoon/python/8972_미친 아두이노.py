from sys import stdin
s = stdin.readline
dx = [1,1,1,0,0,0,-1,-1,-1]
dy = [-1,0,1,-1,0,1,-1,0,1]

def move_jongsoo(p):
    global jx, jy, arr
    nx, ny = jx + dx[int(p)-1], jy + dy[int(p)-1]

    if arr[nx][ny] == 'R':
        return False
    else:
        arr[jx][jy] = '.'
        jx, jy = nx, ny
        arr[jx][jy] = 'I'
        return True
    

def move_robot():
    global jx, jy, arr
    visited = [[0 for _ in range(C)] for _ in range(R)]
    tmp = [['.' for _ in range(C)] for _ in range(R)]
    tmp[jx][jy] = 'I'

    for i in range(R):
        for j in range(C):
            if arr[i][j] == 'R':
                dist = 201
                goX = 0
                goY = 0
                for idx in range(len(dx)):
                    if idx == 4: continue
                    nx, ny = i+dx[idx], j+dy[idx]
                    if (nx<0 or nx>=R) or (ny<0 or ny>=C): continue
                    d = abs(jx - nx) + abs(jy - ny)
                    if (dist > d):
                        dist = d
                        goX = nx
                        goY = ny
                if (arr[goX][goY] == 'I'):
                    return False
                visited[goX][goY] += 1
                tmp[goX][goY] = 'R'

    for i in range(R):
        for j in range(C):
            if visited[i][j] >= 2:
                tmp[i][j] = '.'
    
    arr = tmp
    return True


if __name__ == "__main__":
    R, C = map(int,input().split())
    arr = []
    jx, jy = 0, 0
    for i in range(R):
        lst = list(s())
        for j, val in enumerate(lst):
            if val == 'I':
                jx = i
                jy = j
        arr.append(lst)
    
    S = True
    for n, com in enumerate(s().strip(),1):
        if not move_jongsoo(com):
            print(f"kraj {n}")
            S = False
            break
        if not move_robot():
            print(f"kraj {n}")
            S = False
            break
        
    
    if S:
        for i in range(R):
            print(''.join(arr[i]))