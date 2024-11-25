package module_1_array_and_string.array;

// similar to LC-862
// LC-209 is a special case of LC-862
// this problem has array with ALL POSITIVE integers -  this will ensure sliding window's size is monotonic with its sum
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
        int minlen = 0; // Alternative: int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int sum  = 0;

            for (int j = i; j < nums.length; j++) {
                // add the new element to the window. now the window is available to check
                sum =  sum + nums[j];

                if (sum >= target) { // if window is valid
                    if (minlen == 0 || j - i + 1 < minlen) // Alternative: if (j - i + 1 < minlen)
                        minlen = j - i + 1;

                    break; // break the inner loop to save time
                }
            }
        }

        return minlen; // Alternative: return (minlen == Integer.MAX_VALUE) ? 0 : minlen;
    }

    // solution-2: sliding window
    // definition of "valid window": sum of the window >= target
    // time: O(2N) >> O(N)
    // space: O(1)
    public int minSubArrayLen_2(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE; // minimum length of valid window
        int currWindowSum = 0; // sum of the window

        int L = 0;
        int R = 0;
        while (R < nums.length) {
            // step-1: sync window with currWindowSum by adding the new element pointed by R in the windows
            currWindowSum = currWindowSum + nums[R];

            // step-2: now window synced with currWindowSum, it's available to check validity
            //     if the window's sum > target (window is valid), then we record the valid length and contract the window
            while (currWindowSum >= target) {
                // since window is valid, update result to maintain result to be always minimum
                if (R - L + 1 < minLen)
                    minLen = R - L + 1;

                currWindowSum = currWindowSum - nums[L]; // sync the sum with the window
                L++;
            }

            // step-3: since the window is not valid, move R to expand the window
            R++;
        }

        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }


    // solution-3: binary search + sliding window variant (fixed-length sliding window)
    // binary search goal: search in the scope of possible sizes of a sub-array, to find the minimum sub-array size that satisfies its sum >= target
    // in the binary search, the target sub-array size might /might not exist in the search scope
    // time: O(N*logN)
    // space: O(1)
    public int minSubArrayLen_3(int target, int[] nums) {
        // for input array, sub-array size scope is [1, nums.length] - this is binary search scope
        // note: the target sub-array size might exist, or might not exist
        int L = 1;
        int R = nums.length;

        while (L < R) {
            int mid = L + (R - L) / 2;

            int subArraySize = subArrayExist(nums, target, mid); // or use subArrayExist_(nums, target, mid)

            if (subArraySize != 0) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        // post-processing (where L == R):
        int subArraySize = subArrayExist(nums, target, L); // or use subArrayExist_(nums, target, mid)

        return (subArraySize == 0) ? 0 : subArraySize;
    }

    // sliding window with fixed length - iterate all the sub-arrays in subArraySize
    // return:
    //   - if qualified, return size of sub-array
    //   - if not found, return 0;
    private int subArrayExist(int[] nums, int target, int subArraySize) {
        // initialize the sub-array's sum
        int subArraySum = 0;

        for (int i = 0; i <= subArraySize - 1; i++) {
            subArraySum = subArraySum + nums[i];
        }

        // iterate all the sub-arrays in subArraySize
        int L = 0;
        int R = subArraySize - 1;
        while (R < nums.length) {
            if (subArraySum >= target) {
                return subArraySize;
            }

            if (R + 1 == nums.length) { // check if window already reached right boundary
                break;
            }

            // move window to right
            R++;
            subArraySum = subArraySum + nums[R];
            subArraySum = subArraySum - nums[L];
            L++;
        }

        return 0;
    }

    // (recommend) optimize sliding window - use one pointer (i) ONLY
    private int subArrayExist_(int[] nums, int target, int subArraySize) {
        int subArraySum = 0;
        int i = 0;

        while (i < nums.length) {
            subArraySum = subArraySum + nums[i]; // sync sum with new sub-array

            if (i < subArraySize - 1) { // the current sub-array's length < subArraySize
                i++;
                continue;
            }

            if (subArraySum >= target) { // validate the sub-array, whose size == subArraySize
                return subArraySize;
            }

            subArraySum = subArraySum - nums[i - subArraySize + 1]; // remove the first element of the sub-array

            i++;
        }

        return 0;
    }
}
