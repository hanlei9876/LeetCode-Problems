package module_4_binary_search;

import java.util.Arrays;

// must check the notes of this problem. There are a few key points
public class LC_1838_Frequency_of_the_Most_Frequent_Element {

    // solution 1: sort + greedy + sliding window
    // window's right bound nums[j] is target candidate
    // window's left bound nums[i] is the largest valid range within k operations
    // note: risk of int overflow
    // time: O(NlogN) + O(2N) >> O(NlogN)
    // space: O(logN)
    public int maxFrequency_1(int[] nums, int k) {
        Arrays.sort(nums);

        // sliding window
        int maxFrequency = 0; // range [1, nums.length]
        long windowSum = 0; // int might overflow

        int i = 0;
        int j = 0;
        for (; j < nums.length; j++) {
            // sync windowSum to current window
            windowSum = windowSum + nums[j];

            // validate current window
            //
            // This intermediate product of "(j - i + 1) * nums[j]" could lead to int overflow, as both (j - i + 1) & nums[j] are int. Then, the product must be int that is risky of int overlflow.
            // To mitigate the overflow of "(j - i + 1) * nums[j]", we can cast either (j - i + 1) or nums[j] to a long before multiplication
            while ((j - i + 1) * (long) nums[j] - windowSum > k) {
                // when window is not valid, shrink window by moving i to right
                windowSum =  windowSum - nums[i];
                i++;
            }

            // now window is valid
            maxFrequency = Math.max(maxFrequency, (j - i + 1));
        }

        return maxFrequency;
    }


    // solution 2: sort + greedy + improved sliding window (time optimized)
    // since we only look for the maximum window size as result, we can modify the window:
    //   - the window will only grow, and never shrink when invalid
    //   - if window is invalid, stay current window size and move to right by 1 step
    // note: risk of int overflow
    //
    // time: O(NlogN) + O(2N) >> O(NlogN)
    // space: O(logN)
    public int maxFrequency_2(int[] nums, int k) {
        Arrays.sort(nums);

        // sliding window
        int maxFrequency = 0; // range [1, nums.length]
        long windowSum = 0; // int might overflow

        int i = 0;
        int j = 0;
        for (; j < nums.length; j++) {
            // sync windowSum to current window
            windowSum = windowSum + nums[j];

            // validate current window
            if ((j - i + 1) * (long) nums[j] - windowSum > k) { // This intermediate product of "(j - i + 1) * nums[j]" could lead to int overflow
                // when window is not valid, keep window size & move window to right
                windowSum =  windowSum - nums[i];
                i++;
            }

            // now window is valid
            maxFrequency = Math.max(maxFrequency, (j - i + 1));
        }

        return maxFrequency;
    }


    // solution 3: sort + prefix sum + binary search (non-optimal solution)
    // this solution is based on solution 1:
    //   - iterate over each element (window's right bound j) in nums as target candidate
    //   - for each j, perform binary search on prefix sum to find window's left bound j
    //
    // note: risk of int overflow
    //
    // time: O(NlogN) + O(N) + O(NlogN) >> O(NlogN)
    // space: O(logN) + O(N) >> O(N)
    public int maxFrequency_3(int[] nums, int k) {
        Arrays.sort(nums);

        // create prefix sum array & fill it
        long[] prefixSums = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                prefixSums[i] = nums[i];
            } else {
                prefixSums[i] = prefixSums[i - 1] + nums[i];
            }
        }

        // iterate over each element (window's right bound j) in nums as target candidate
        int maxFrequency = 0;

        for (int i = 0; i < nums.length; i++) {
            int searchResult = binarySearch(nums, prefixSums, k, i);
            maxFrequency = Math.max(maxFrequency, searchResult);
        }

        return maxFrequency;
    }

    private int binarySearch(int[] nums, long[] prefixSums, int k, int targetIndex) {
        int L = 0;
        int R = targetIndex;

        while (L < R) {
            int mid = L + (R - L) / 2;
            long currSum = prefixSums[targetIndex] - prefixSums[mid] + nums[mid];

            if ((targetIndex - mid + 1) * (long) nums[targetIndex] - currSum <= k) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        return targetIndex - L + 1; // max window size
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 92};
        LC_1838_Frequency_of_the_Most_Frequent_Element solution =  new LC_1838_Frequency_of_the_Most_Frequent_Element();

        System.out.println(solution.maxFrequency_1(nums, 1));
    }
}
