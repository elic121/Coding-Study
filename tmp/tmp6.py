# r=range(int(input()))
# a=[[*map(int,input()[::2])] for _ in r]
# for k in r:
#  for w in r:
#   for j in r:a[w][j]|=a[w][k]&a[k][j]
# for q in a:print(*q)



# R=range(int(input()))
# a=[[*map(int,input()[::2])]for _ in R]
# for i in R:
#  for j in R:
#   for k in R:a[j][k]|=a[j][i]&a[i][k]
# for i in a:print(*i)