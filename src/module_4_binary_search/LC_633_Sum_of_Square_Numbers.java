package module_4_binary_search;

// similar to LC-69
public class LC_633_Sum_of_Square_Numbers {

    // key point 1:
    // given c >= 0, if a^2 + b^2 = c, then 0 <= a, b <= sqrt(c)
    // key point 2:
    // we actually only need to consider a>= 0 & b >=0

    // solution 1: brute force - enumerate all possible values (time limit exceeded)
    // for a^2 + b^2 = c, 0 <= a, b <= sqrt(c). So, [0, sqrt(c)] can be the search range for a, b
    // we cannot search a, b in [0, c/2]. Because when c = 1, a or b == c
    // time: O(c)
    // space: O(1)
    public boolean judgeSquareSum_1(int c) {
        for (int a = 0; a * a <= c; a++) { // search range [0, sqrt(c)]
            for (int b = 0; b * b <= c; b++) {

                if (a * a + b * b == c) { // no overflow here
                    return true;
                }
            }
        }

        return false;
    }

    // solution 2: improved brute force (time limit exceeded)
    //   - enumerate all possible values for a
    //   - for each a, check if (c - a*a) is a prefect square
    //
    // How to check if (c - a*a) is a prefect square?
    //   - The square of nth positive integer can be represented as a sum of first n odd positive integers.
    //   - n^2 = 1 + 3 + 5 + ... + (2⋅n−1)
    //
    // time: O(c)
    // space: O(1)
    public boolean judgeSquareSum_2(int c) {
        for (int a = 0; a * a <= c; a++) { // search range [0, sqrt(c)]
            int num = c - a * a;

            int i = 1;
            int sum = 0;
            while (sum < num) {
                sum += i;
                i = i + 2;
            }

            if (sum == num) {
                return true;
            }
        }

        return false;
    }

    // solution 3: improved brute force (time limit exceeded)
    //   - enumerate all possible values for a
    //   - for each a, check if (c - a*a) is a prefect square
    //
    // How to check if (c - a*a) is a prefect square?
    //   - use Math.sqrt(x) with O(logx) time
    //
    // time: O(sqrt(c) * log(c-a*a)) >> O(sqrt(c) * logc)
    // space: O(1)
    public boolean judgeSquareSum_3(int c) {
        for (int a = 0; a * a <= c; a++) { // search range [0, sqrt(c)]
            int num = c - a * a;
            double sqrtB = Math.sqrt(num);

            // check if sqrtB is an int
            if (sqrtB == (int) sqrtB) {
                return true;
            }
        }

        return false;
    }

    // solution 4: binary search
    //   - enumerate all possible values for a
    //   - for each a, check if (c - a*a) is a prefect square
    //
    // How to check if (c - a*a) is a prefect square?
    //   - use binary search (refer to LC-69)
    //
    // time: O(sqrt(c) * logc)
    // space: O(1)
    public boolean judgeSquareSum_4(int c) {
        for (long a = 0; a * a <= c; a++) { // search range [0, sqrt(c)]
            int num = c - (int) (a * a); // // before doing a*a, we must ensure a is long. Otherwise, there is overflow

            boolean isPerfect = isPerfectSqrt(num);

            if (isPerfect) {
                return true;
            }
        }

        return false;
    }

    // binary search when x >= 2
    //   - goal: search for number mid, if mid*mid == x
    //   - search scope: [1, x/2]
    private boolean isPerfectSqrt(int x) {
        if (x < 2) {
            return true;
        }

        int L = 1;
        int R = x / 2;
        while (L <= R) {
            long mid = (long) (L + (R - L) / 2);
            long sqrt = mid * mid; // before doing mid*mid, we must ensure mid is long. Otherwise, there is overflow

            if (sqrt == x) {
                return true;
            } else if (sqrt < x) {
                L = (int) mid + 1;
            } else {
                R = (int) mid - 1;
            }
        }

        return false;
    }

    // experiments on math
    public static void main(String[] args) {
        int c = 5;
        System.out.println(Math.sqrt(5)); // 2.23606797749979

        int a = (int) Math.sqrt(5);
        System.out.println(a); // 2

        double x = 2.9686;
        int x1 = (int) x;
        System.out.println(x1);  // 2

        int z1 = 2;
        double z2 = 2.0;
        System.out.println(z1 == z2); // true

        LC_633_Sum_of_Square_Numbers solution = new LC_633_Sum_of_Square_Numbers();
        System.out.println(solution.judgeSquareSum_4(2147482647));
    }
}
