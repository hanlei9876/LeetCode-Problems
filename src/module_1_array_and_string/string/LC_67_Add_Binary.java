package module_1_array_and_string.string;

import java.math.BigInteger;

public class LC_67_Add_Binary {

    // solution-1: intuitive solution
    // convert both strings to integer >> calculate sum >> convert sum integer back to string
    // time: O(M + N + log(n))
    // space: O(1)

    /** Drawback:
     * In Java this approach is limited by the length of the input strings a and b.
     * Once the string is long enough, the result of conversion into integers will not fit into Integer, Long or BigInteger:
     *  (1) 33-bit string doesn't fit into Integer.
     *  (2) 65-bit string doesn't fit into Long.
     *  (3) 500000001-bit doesn't fit into BigInteger. But in our problem, considering the range limitation for string's length, BigInteger is available
     * To fix the issue, we need to use the following approaches
     *  */
    public String addBinary(String a, String b) {
        // return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
        int a1 = Integer.parseInt(a, 2); // time: O(M)
        int a2 = Integer.parseInt(b, 2); // time: O(N)
        int a3 = a1 + a2;

        // time: O(log(n)), with The maximum value for n is 2^31 - 1, which is a constant in worst case.
        // So, it is not meaningful to discuss the big O for this function below. Because big O is always for infinity.
        return Integer.toBinaryString(a3);
    }

    // time: ??? - we need to look up for all these functions
    // space: O(1)
    public String addBinary_2(String a, String b) {
        BigInteger a1 = new BigInteger(a, 2); // slow in time
        BigInteger a2 = new BigInteger(b, 2); // slow in time
        BigInteger a3 = a1.add(a2);

        return a3.toString(2);
    }

    // solution-2: bit-by-bit computation
    // time: O(MAX(m, n)). m and n are the lengths of the input strings a and b, respectively.
    // space: O(MAX(m, n)), which the String Builder
    public String addBinary_3(String a, String b) {
        StringBuilder sb = new StringBuilder(); // hold the overall result

        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int bitSum = 0;

            bitSum += carry;
            if (i >= 0)
                bitSum += a.charAt(i) - '0'; // calculation with ASCII code
            if (j >= 0)
                bitSum += b.charAt(j) - '0';

            sb.append(bitSum % 2);
            carry = bitSum / 2;

            i--;
            j--;
        }

        // when running out of the two strings, we need to consider carry
        // if carray == 0, we don't have to attach o to the highest bit of result
        if (carry == 1)
            sb.append(carry);

        sb.reverse(); // Time: O(MAX(m, n)), Space: O(1)
        return sb.toString();
    }

    // solution-3: Bit Manipulation (from LeetCode official solution)
    // a + b = (a^b) + ((a & b) << 1)
    // time: O(M + N + MAX(M, N)) = O(M + N)
    // space: O(M + N)
    public String addBinary_4(String a, String b) {
        BigInteger x = new BigInteger(a, 2); // time: O(M)
        BigInteger y = new BigInteger(b, 2); // time: O(n)
        BigInteger zero = new BigInteger("0", 2);

        // time: O(MAX(M, N))
        while (y.compareTo(zero) != 0) {
            BigInteger sum = x.xor(y);
            BigInteger carry = x.and(y).shiftLeft(1);

            x = sum;
            y = carry;
        }

        return x.toString(2);
    }

    public static void main(String[] args) {
        String s1 = "11111111111111111111111111111111"; // 32 ones
        String s2 = "1111111111111111111111111111111"; // 31 ones

        // System.out.println(Integer.parseInt(s1, 2)); // throws NumberFormatException
        System.out.println(Integer.parseInt(s2, 2)); // 2147483647

        System.out.println(Integer.parseInt("-1100110", 2)); // -102

        char c = '1';
        System.out.println(1 + c); // 50. Because c will be converted by Java to ASCII code: 49

        /************************************************************************/
        /************************Bit Manipulation********************************/
        /************************************************************************/
        int a = 5; // 101
        long b = 3; // 011
        long result = a ^ b;
        System.out.println(result); // 6

        int result2 = a << 2;
        System.out.println(result2); // 20 (10100)

        int a1 = 0;
        int a2 = 1;
        System.out.println(a1 | a2); // 1

        int c1 = c & 0;
        System.out.println(c1); // 0

    }
}
