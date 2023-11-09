def sol(key):
    d = {}
    for k in key:
        if k in d:
            d[k] += 1
        else:
            d[k] = 1
    
    for k, val in d.items():
        print(f"{k}({val})", end = ' ')


if __name__ == "__main__":

    N, *arr, result = map(int,input().split())

    dp = [[] for _ in range(result+1)]

    for val in arr:
        dp[val].append([val])
        

    for idx in range(1,result+1):
        if not dp[idx]:
            continue
        
        for s in dp[idx]:
            s_sum = sum(s)

            for element in arr:
                go_to_idx = s_sum + element
                
                if result < go_to_idx:
                    continue
                
                tmps = [i for i in s]
                tmps.append(element)
                dp[go_to_idx].append(tmps)
    

    dict_res = {}        
    for val in dp[result]:
        dict_res[tuple(sorted(val))] = 1
    
    for lst in dict_res.keys():
        sol(lst)
        print()