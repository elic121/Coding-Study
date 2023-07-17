arr = ['A','B','C','D','E']
sel = [0, 0, 0]

def combination(idx, sidx):
    if sidx ==3:
        print(*sel)
        return
    if idx == 5:
        return 
    
    sel[sidx] = arr[idx]
    combination(idx+1,sidx+1)
    combination(idx+1,sidx)

combination(0,0)