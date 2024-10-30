package module_4_binary_search;

// 1<= x <= 2^31 - 1
public class LC_367_Valid_Perfect_Square {

    // solution 1: binary search (reference to LC-69)
    // the whole idea is to search the number a in the search space 2 ~ num, where a^2 == num, or we cannot find it
    // prerequisite: for x >= 2, we optimize the initial search space, from 2 ~x down to 2 ~ x/2
    // we need to narrow down the initial search space: L=2, R=x/2 (instead of R=x)
    // x:         1     2     3    4    5     6     7     8     9    ...
    // sqrt(x):   1    1.4   1.7   2   2.2   2.4   2.6   2.8    3    ...
    //            v     v     v    ||   ^     ^     ^     ^     ^
    // x/2 :     0.5    1    1.5   2   2.5    3    3.5    4    4.5  ...
    //
    // time: O(lognum)
    // space: O(1)
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }

        // for x >= 2, we optimize the initial search space to 2 ~ x/2
        //   - if x == 2 or 3, no value can be found from 0 ~ x
        //   - if x >= 4, sqrt(x) must be within 2 ~ x/2, or no where
        int L = 2;
        int R = num / 2;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            long sqrt = (long) mid * mid; // use long to avoid int overflow

            if (sqrt == num) {
                return true;
            } else if (sqrt < num) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return false;
    }
}
