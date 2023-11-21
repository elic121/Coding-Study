# https://www.acmicpc.net/problem/4659
import sys


def ans(word):
    m = ["a", "e", "i", "o", "u"]
    find = False
    for i in m:
        if i in word:
            find = True
    if not find:
        return False

    contm = 0
    contj = 0
    for w in word:
        if w in m:
            contm += 1
            contj = 0
        else:
            contj += 1
            contm = 0
        if contm >= 3 or contj >= 3:
            return False

    for i in range(1, len(word)):
        if word[i] == word[i - 1]:
            if word[i] == "e" or word[i] == "o":
                continue
            else:
                return False

    return True


while True:
    word = sys.stdin.readline().strip()
    if word == "end":
        break
    if ans(word):
        print(f"<{word}> is acceptable.")
    else:
        print(f"<{word}> is not acceptable.")
