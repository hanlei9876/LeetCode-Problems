package module_4_binary_search;

public class LC_400_Nth_Digit {
    // key:
    // the ranges are as below:
    //                  1 ~ 9:                       9 numbers of 1 digit
    //                 10 ~ 99:                     90 numbers of 2 digits
    //                100 ~ 999:                   900 numbers of 3 digits
    // firstNumberOfRange                 numberAmount            k



    // solution 1: linear search  for k
    // n is 1-indexed
    //  - step 1: linear search to find index k of range the n's number falls in (k is 1-indexed)
    //  - step 2: find index of the n's number within the range
    //  - step 3: find index of digit within the n's number - answer
    //
    // time: O(k) >> O(logn) - base is 10
    // space: O(1)
    public int findNthDigit_1(int n) {
        // step 1: find k using linear search
        int k = 1;
        int numberAmount = 9;
        int firstNumberOfRange = 1;

        while (n - numberAmount * k > 0) {
            n = n - numberAmount * k;

            k++;
            numberAmount = numberAmount * 10;
            firstNumberOfRange = firstNumberOfRange * 10;
        }

        // step 2: find target number where n falls in
        int targetNumber = firstNumberOfRange + (n - 1) / k; // cannot be n

        // step 3: find the target digit within the target number, which is the answer
        int digitIndex = (n - 1) % k; // 0-indexed

        // For Integer.toString(N), time: O(logN), space: O(logN), where both bases = 10.
        // Given n is int, targetNumber is very small, so time & space can be ignored here
        String s = Integer.toString(targetNumber); // String s = String.valueOf(targetNumber);
        char c = s.charAt(digitIndex);

        return c - '0'; // convert a char number to int.  '0' == 48
    }

/********************************  Solution binary search is NOT recommended  *****************************************/

    // solution 2: binary search for k
    // search range: [1, n] , where k is far smaller than n
    // key: Math.pow(base, exponent) time complexity is O(log(exponent))
    public int findNthDigit_2(int n) {
        /*// determine max(k)
        int i = 1;
        int totalDigitCount = 0;
        while (totalDigitCount < n) {
            totalDigitCount = totalDigitCount + i * 9 * (int) Math.pow(10, i - 1);
            i++;
        }
        System.out.println("i is: " + i);*/

        long cumuDigitCount = 0;
        int k = - 1;

        int L = 1;
        int R = n;

        while (L <= R) {
            int mid = L + (R - L) / 2;
            long digitCountLeftBound = countDigits(mid - 1) + 1;
            long digitCountRightBound = (digitCountLeftBound - 1) + mid * 9 * (long) Math.pow(10, mid - 1);

            if (digitCountLeftBound <= n && n <= digitCountRightBound) {
                k = mid;
                cumuDigitCount = digitCountLeftBound - 1;
                break;
            } else if (n < digitCountLeftBound) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        // k is found - n's number falls in kth range (k is 1-indexed)
        int firstNumberOfCurrentRange = (int) Math.pow(10, k - 1);
        n = n - (int) cumuDigitCount;

        int targetNumber = firstNumberOfCurrentRange + (n - 1) / k;
        int digitIndex = (n - 1) % k;

        String s = String.valueOf(targetNumber);
        char c = s.charAt(digitIndex);

        return c - '0';
    }

    private long countDigits(int k) {
        long digitSum = 0;

        for (int i = 1; i <= k; i++) {
            long currRangeDigitCount = i * 9 * (long) Math.pow(10, i - 1); // long currRangeDigitCount overflow
            digitSum = digitSum + currRangeDigitCount;
        }

        return digitSum;
    }


    // given int x, return the count of digits
    public static void main(String[] args) {
        int x = 7612;

        int count = 0;
        while (x != 0) {
            x = x / 10;
            count++;
        }

        System.out.println(count);


        LC_400_Nth_Digit solution = new LC_400_Nth_Digit();
        System.out.println(solution.findNthDigit_2(1000000000));
    }
}
