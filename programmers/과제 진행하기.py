# https://school.programmers.co.kr/learn/courses/30/lessons/176962

plans = [["science", "12:40", "50"], ["music", "12:20", "40"],
         ["history", "14:00", "30"], ["computer", "12:30", "100"]]

plans2 = [["aaa", "12:00", "20"], ["bbb", "12:10", "30"], ["ccc", "12:40", "10"]]
def calc_time(stt):
    h,m = stt.split(':')
    s_time = int(h)*60 + int(m)
    return s_time

def solution(plans):
    plans.sort(key=lambda x:x[1],reverse=True)
    answer = []
    stack = []
    while plans:
        if len(plans) == 1:
            answer.append(plans[0][0])
            break
        plan = plans.pop()
        end, minute = plan[1], plan[2]
        end_time = calc_time(end) + int(minute)
        next_time = plans[-1][1]
        h,m = next_time.split(':')
        next_time = int(h)*60 + int(m)
        
        if end_time > next_time:
            stack.append(plan[0])
        else:
            answer.append(plan[0])
    
    for i in range(len(stack)-1,-1,-1):
        answer.append(stack[i])
        
    return answer

print(solution(plans2))