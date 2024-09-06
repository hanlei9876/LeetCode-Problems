package module_4_binary_search;

// x >= 0, x is int
public class LC_69_Sqrt_x {

    // solution 1: binary search
    // prerequisite: we need to narrow down the initial search space: L=2, R=x/2 (instead of R=x)
    // x:        2  3  4  5  6  7  8  9  10 ... 15 ... 100
    // sqrt(x):  1  1  2  2  2  2  2  3  3  ... 3  ... 10
    // x/2 :     1  1  2  2  3  3  4  4  5  ... 7  ... 50
    //
    // time: O(logx)
    // space: O(1)
    public static int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int left = 2;
        int right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2; // don't use (left + right)/2 to avoid int overflow
            long num = (long) mid * mid; // don't use int, to avoid int overflow

            if (num == x) {
                return mid;
            } else if (num < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        int result = mySqrt(8);
        System.out.println(result);

        int x = Integer.MAX_VALUE - 1;
        long y = (long) x * x;
        System.out.println(x); // 2147483646
        System.out.println(y); // 4611686009837453316
    }
}
