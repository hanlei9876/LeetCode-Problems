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
    // space: (n) - Stack Overflow when n is too large
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
    // space: (n) - Stack Overflow when n is too large
    public double myPow_2(double x, int n) {
        // base case
        if (n == 0) return 1;

        // recurrence relation
        if (n < 0)
            return 1.0 / myPow_2(x, -n); // return 1 / myPow_2(x, -n); this is working as well, as Java compiler will cast int to double

        return x * myPow_2(x, n - 1);
    }

    /***  solutions blow are able to pass submission *****/

    // solution-3: recursion + binary exponentiation
    // binary: 2^x = n. so, x log(n)
    // time: O(logn)
    // space: O(logn)
    public double myPow_3(double x, int n) {
        // handle positive/negative of n in advance
        if (n >= 0) {
            return pow(x, n);
        } else {
            return 1.0 / pow(x, -n);
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
            double y = pow(x, n/2);
            return y * y * x;
        }
    }

    // solution-3: iteration + binary exponentiation (mimic recursion)
    // time: O()
    // space: O()
    public double myPow_4(double x, int n) {return 0.0;}
}
