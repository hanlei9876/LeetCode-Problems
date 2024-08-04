package module_3_recursion_I_and_II;

// it doesn't matter if x is positive or negative
// it matters if n > 0, n == 0 or n < 0
public class LC_50_Pow_x_n {

    // solution-1: iteration (Time Limit Exceeded when n is too large)
    // time: O(n)
    // space: O(1)
    public double myPow_1(double x, int n) {
        if (n == 0) return 1;

        int k = Math.abs(n);
        double res = 1.0;
        for (int i = 0; i < k; i++) {
            res = res * x;
        }

        if (n < 0) {
            return 1 / res;
        }

        return res;
    }

    // solution-2: brute force recursion
    // time: O(n) - Time Limit Exceeded when n is too large
    // space: O(n) - Stack Overflow when n is too large
    public double myPow(double x, int n) {
        // base case
        if (n == 0) return 1;

        // recurrence relation
        double res = 1;
        if (n > 0) {
            res = x * myPow(x, n - 1);
        } else {
            res = (1 / x) * myPow(x, n + 1);
        }

        return res;
    }

    // solution-2.1: another way of brute force recursion
    // time: O(n) - Time Limit Exceeded when n is too large
    // space: O(n) - Stack Overflow when n is too large
    public double myPow_2(double x, int n) {
        /// handle positive/negative of n in advance
        if (n >= 0) {
            return pow_2(x, n);
        } else {
            return pow_2(1 / x, -n);
        }
    }

    // this helper method will only handle positive n
    private double pow_2(double x, int n) {
        // base case
        if (n == 0) return 1;

        // recurrence relations
        return pow_2(x, n-1) * x;
    }



    /***  solutions blow are able to pass submission *****/

    // solution-3: recursion + binary exponentiation
    // binary: 2^x = n. so, x = log(n)
    // time: O(logn)
    // space: O(logn)
    public double myPow_3(double x, int n) {
        // handle positive/negative of n in advance
        if (n >= 0) {
            return pow(x, n);
        } else {
            return pow(1/x, -n);
        }
    }

    // this helper method will only handle positive n
    private double pow(double x, int n) {
        // base case
        if (n == 0) return 1;

        // recurrence relations
        if (n % 2 == 0) {
            double y = pow(x, n/2);
            return y * y;
        } else {
            // double y = pow(x, (n-1)/2); // this will cause stack overflow!!! But don't know why
            double y = pow(x, n/2); // 5/2 == 2
            return y * y * x;
        }
    }

    // solution-4: iteration + binary exponentiation (binary search, not recursion to iteration) - this solution is hard to understand
    // n must be converted from int to long, as n's range is 2^(-31) <= n <= n(31) - 1, when n is the negative bound, -n will overflow
    // see more notes
    // time: O(logN)
    // space: O(1)
    public double myPow_4(double x, int n) {
        long nn = (long) n;

        if (nn == 0) {
            return 1;
        }

        if (nn < 0) {
            nn = nn * (-1); // as n's range is 2^(-31) <= n <= n(31) - 1, when n is the negative bound, -n will overflow
            x = 1 / x;
        }

        // now n > 0
        double res = 1.0;

        while (nn > 0) {
            if (nn % 2 == 1) {
                res = res * x;
                nn--;
            }

            x = x * x;
            nn = nn / 2;
        }

        return res;
    }
}
