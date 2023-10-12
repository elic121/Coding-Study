
dx = [0,0,-1,1]
dy = [1,-1,0,0]

def simulation(atom):
    tmp = {}
    energy = 0
    cnt1 = 0
    cnt2 = 0
    for key, val in atom.items():
        x,y = key
        for d,k in val:
            cnt1 += 1
            nx, ny = x+dx[d], y+dy[d]
            if nx>2000 or nx<-2000 or ny>2000 or ny<-2000:
                cnt2 += 1
                continue
            if (nx,ny) in tmp:
                tmp[(nx,ny)].append((d,k))
            else:
                tmp[(nx,ny)] = [(d,k)]

    tmpkey = []
    for key, val in tmp.items():
        ene = 0
        if len(tmp[key]) > 1:
            tmpkey.append(key)
            for _,k in tmp[key]:
                ene += k
        energy += ene
    for key in tmpkey:
        del tmp[key]
    # breakpoint()
    return tmp, energy, cnt1 == cnt2

if __name__ == '__main__':
    T = int(input())
    for tc in range(1,T+1):
        N = int(input())
        atom = {}
        energy = 0
        for _ in range(N):
            x,y,D,K = map(int,input().split())
            atom[(2*x,2*y)] = [(D,K)]

        while True:
            atom, E, B = simulation(atom)
            energy += E

            if B:
                break
        print(f"#{tc} {energy}")
