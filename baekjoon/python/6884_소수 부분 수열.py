def is_prime(num):
    if num < 2:
        return False
    for prime in primes:
        if prime * prime > num:
            break
        if num % prime == 0:
            return False
    return True


def get_primes():
    N = 10_000
    sosu = [1 for _ in range(N + 1)]
    sosu[0] = 0
    sosu[1] = 0
    for i in range(2, int(N ** 0.5) + 1):
        if sosu[i]:
            for j in range(i*i, N + 1, i):
                sosu[j] = 0
    primes = [2] + [i for i in range(3, N + 1, 2) if sosu[i]]
    return primes


def main():
    for i in range(2, n + 1):
        for j in range(n):
            if i + j <= n:
                if is_prime(vs[i + j] - vs[j]):
                    print(f"Shortest primed subsequence is length {i}:", *arr[j: i + j])
                    return
    print("This sequence is anti-primed.")
    return 

if __name__ == "__main__":
    T = int(input())
    primes = get_primes()

    for _ in range(T):
        n, *arr = map(int, input().split())
        vs = [0];[vs.append(vs[-1] + val) for val in arr]
        main()
