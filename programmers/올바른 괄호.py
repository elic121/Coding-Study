def solution(s):
    answer = True
    stack = []
    s = list(s)
    while s:
        x = s.pop()
        stack.append(x)
        while True: 
            if len(stack) >= 2 and stack[-1] == '(' and stack[-2] == ')':
                stack.pop()
                stack.pop()
            else:
                break
    if not stack:
        return answer
    else:
        return False


if __name__ == "__main__":
    print(solution("()()"))
    print(solution("(())()"))
    print(solution(")()("))
    print(solution("(()("))
