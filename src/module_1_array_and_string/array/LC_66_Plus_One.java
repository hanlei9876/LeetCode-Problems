package module_1_array_and_string.array;

// need to discuss with interviewer:
//      if we should use an additional array to hold result,
//      or we could partially return the modified input array
public class LC_66_Plus_One {

    // solution-1: Schoolbook Addition with Carry
    // use a separate array to hold result
    // time: O(2N) -> O(N)
    // space: O(N), note result array is counted
    public int[] plusOne(int[] digits) {
        // step-1: check if we need to create an array of length + 1
        int i = 0;
        while ( i < digits.length) {
            if (digits[i] != 9)
                break;

            i++;
        }

        if (i == digits.length) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;

            return res;
        }

        // step-2: now, the result would be in the same length as input array
        int[] res = new int[digits.length];

        // there are three types of operations for each digit in this order:
        // (1) set as 0
        // (2) plus 1
        // (3) copy that digit
        // for example:
        // [9, 2, 3, 9, 9]
        // [9, 2, 4, 0, 0]
        boolean copy = false;
        for (int j = digits.length-1; j >= 0; j--) {
            if (copy == true) {
                res[j] = digits[j];
                continue;
            }

            if (digits[j] != 9) {
                res[j] = digits[j] + 1;
                copy = true;
            } else {
                res[j] = 0;
            }
        }

        return res;
    }

    // solution-2: Schoolbook Addition with Carry
    // when possible, do not use a separate array to hold result
    // there is one and only one case we should create a separate array, which is all digits are 9
    // time: O(N)
    // space: 1 when in-place, O(N) when worst case of all 9s
    public int[] plusOne_2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }

        // when we are here, it means all the digits are 9
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}
