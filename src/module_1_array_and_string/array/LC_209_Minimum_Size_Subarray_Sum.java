package module_1_array_and_string.array;

public class LC_209_Minimum_Size_Subarray_Sum {

    // solution-0: pure brute force
    // use i-j nested loop to iterate each sub-array
    // for each sub-array,
    //  - we further nest a loop to calculate the sum of the sbu-array
    // with the sum calculated in last loop, compare the sum against target, if valid, update the minimum length
    // time: O(N^3)
    // space: O(1)

    // solution 1: brute force (optimized) - enumerate each possible sub-array (time limit exceeded)
    // use i-j loop to maintain a window
    // time: O(N^2)
    // space: O(1)
    public int minSubArrayLen(int target, int[] nums) {
        int minlen = 0;

        for (int i = 0; i <= nums.length; i++) {
            int sum  = 0;

            for (int j = i; j < nums.length; j++) {
                // add the new element to the window. now the window is available to check
                sum =  sum + nums[j];

                if (sum >= target) { // if window is valid
                    if (minlen == 0 || j - i + 1 < minlen)
                        minlen = j - i + 1;

                    break; // break the innner loop to save time
                }
            }
        }

        return minlen;
    }

    // solution-2: sliding window
    // definition of "valid window": sum of the window >= target
    public int minSubArrayLen_2(int target, int[] nums) {
        int minLen = 0; // minimum length of valid window
        int sum = 0; // sum of the window

        int L = 0;
        int R = 0;
        while (R < nums.length) {
            // step-1: add the new element pointed by R in the windows
            sum = sum + nums[R];

            // step-2: now our window has element inside, which is available to check validity
            //     if the window's sum > target (window is valid), then we record the valid length and contract the window
            while (sum >= target) {
                // since window is valid, update result to maintain result to be always minimum
                if (minLen == 0 || R - L + 1 < minLen)
                    minLen = R - L + 1;

                sum = sum - nums[L]; // maintain the sum of the window
                L++;
            }

            // step-3: since the window is not valid, move R to expand the window
            R++;
        }

        return minLen;
    }
}
