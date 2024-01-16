#include <stdio.h>

#include <vector>

// I apologize in advance for the ugly macros.
// It was an experiment. The experiment has obviously failed.
#define FR(i, a, b) for (int i = a; i < b; i++)
#define FOR(i, n) FR(i, 0, n)
#define FORALL(it, st) \
  for (typeof(st.end()) it = st.begin(); it != st.end(); it++)

// define SHOW_ALL if you want to dump all primed sequences,
// for the purpose of generating examples for the Q
//
// This is an n^2 solution.
// Uncomment the #define VERY_SLOW to make sure
// the naive n^3 fails

//#define SHOW_ALL
//#define VERY_SLOW

using namespace std;

typedef vector<int> VI;

VI pp;

int prime(int n) {
  if (n < 2) return 0;
  for (int i = 0; pp[i] * pp[i] - 1 <= n; i++) {
    if (0 == (n % pp[i])) {
      return 0;
    }
  }
  return 1;
}

int pgen() {
  FR(i, 2, 35000) {
    FORALL(it, pp) {
      if ((i % *it) == 0) {
        goto NP;
      }
    }
    pp.push_back(i);
  NP:;
  }
}

int main() {
  int ds, n, t;
  pgen();
  scanf("%d", &ds);
  while (ds--) {
    VI vv, vs;
    int cnt = 0;
    scanf("%d", &n);
    FOR(i, n) {
      scanf("%d", &t);
      vs.push_back(cnt);
      cnt += t;
      vv.push_back(t);
    }
    vs.push_back(cnt);
#ifdef SHOW_ALL
    int scnt = 0;
#endif
    FR(i, 2, n + 1) {
      FOR(j, n) {
        if (i + j <= n) {
#ifdef VERY_SLOW
          int sum = 0;
          FR(k, j, i + j) sum += vv[k];
          if (prime(sum)) {
#else
          if (prime(vs[j + i] - vs[j])) {
#endif
#ifdef SHOW_ALL
            printf("Sequence %d: length %d:", ++scnt, i);
#else
            printf("Shortest primed subsequence is length %d:", i);
#endif
            FR(k, j, j + i) { printf(" %d", vv[k]); }
            printf("\n");
#ifndef SHOW_ALL
            goto NEXT;
#endif
          }
        }
      }
    }
    printf("This sequence is anti-primed.\n");
  NEXT:;
  }
}
}