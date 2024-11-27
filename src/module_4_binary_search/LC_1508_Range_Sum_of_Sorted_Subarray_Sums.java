package module_4_binary_search;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LC_1508_Range_Sum_of_Sorted_Subarray_Sums {

    private static final int MOD = 1000000007;


    // solution 1: brute force
    // time: O(N^2) + O( (N^2)log(N^2) ) + O(N^2) >> O((N^2)logN)
    // space: O(N^2) + O(log(N^2)) >> O(N^2)
    public int rangeSum_1(int[] nums, int n, int left, int right) {
        // obtain array of all subarrays'sum
        List<Integer> sumList = new ArrayList<>(); // according to problem constraint, largest sum is still within int

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;

            for (int j = i; j < nums.length; j++) {
                sum += nums[j];

                sumList.add(sum);
            }
        }

        // sumList's length = N*(N+1)/2

        // sort the array
        Collections.sort(sumList); // sort ArrayList, instead of array

        // calculate the sum between left & right
        long rangeSum = 0L;
        for (int i = left - 1; i <= right - 1; i++) {
            rangeSum = rangeSum + sumList.get(i);
        }

        // we cannot return: (int) rangeSum % MOD - because (int) has higher priority than % in Java
        return (int) (rangeSum % MOD);
    }
}


// Solution 3: prefix sum + binary search + sliding window (MUST SEE my note)
// time: O(N) + O(NlogM) >> O(NlogM) - N is nums size, M is sum of all values in nums
// space: O(1)
class solution_3 {

    private static final int MOD = 1000000007;

    // left & right are 1-based
    public int rangeSum(int[] nums, int n, int left, int right) {
        long leftPrefixSum = getPrefixSum(nums, left - 1);
        long rightPrefixSum = getPrefixSum(nums, right);

        long rangeSum = rightPrefixSum - leftPrefixSum;

        return (int) (rangeSum % MOD);
    }

    // k is 1-based
    // use binary search to find smallest subarray-sum mid, so that the count of subarrays >= k (each subarray's sum <= mid)
    private long getPrefixSum(int[] nums, int k) {
        // edge case
        if (k == 0) {
            return 0;
        }

        // calculate search range of any subarray's sum
        int smallestSubarraySum = Integer.MAX_VALUE;
        int largestSubarraySum = 0;
        for (int i = 0; i < nums.length; i++) {
            largestSubarraySum += nums[i];
            smallestSubarraySum = (nums[i] < smallestSubarraySum) ? nums[i] : smallestSubarraySum;
        }

        // binary search
        int L = smallestSubarraySum;
        int R = largestSubarraySum;

        while (L < R) {
            int mid = L + (R - L) / 2;

            Map.Entry<Integer, Long> count_sum = getCountAndSum(nums, mid, k);
            int count = count_sum.getKey();

            if (count >= k) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        // L == R, now obtain the target count & sum
        Map.Entry<Integer, Long> count_sum = getCountAndSum(nums, L, k);

        // since count >= k due to duplicate values (subarray sum), we need to trim the sum to get answer
        int count = count_sum.getKey();
        long sum = count_sum.getValue();
        long resultSum = sum - (count - k) * L;

        return resultSum;
    }

    // obtain 2 things using sliding window:
    //   1) all valid subarrays' total count
    //   2) total sum of every valid subarray's sum
    //
    // return them as Map.Entry<(int) count, (long) sum>
    //
    // the key points for implementing this function is how to expand & shrink window
    private Map.Entry<Integer, Long> getCountAndSum(int[] nums, int subarraySumUpperBoundary, int k) {
        int validSubarraysTotalCount = 0;
        long validSubarraySumsTotalSum = 0; // int is risky of overflow

        int currWindowSum = 0;
        int currWindowAllSubarraysSum = 0;


        for (int i = 0, j = 0; j < nums.length; j++) {
            // expand window - sync parameters of current window
            currWindowSum += nums[j];
            currWindowAllSubarraysSum = currWindowAllSubarraysSum + nums[j] * (j - i + 1);

            // validate current window
            while (currWindowSum > subarraySumUpperBoundary) {
                // shrink window - sync parameters of current window
                currWindowAllSubarraysSum -= currWindowSum;
                currWindowSum -= nums[i];

                i++;
            }

            // now window is valid
            validSubarraysTotalCount += (j - i + 1);
            validSubarraySumsTotalSum += currWindowAllSubarraysSum;
        }

        // Map.Entry<Integer, Long>() is interface
        return new AbstractMap.SimpleEntry<Integer, Long>(validSubarraysTotalCount, validSubarraySumsTotalSum);
    }
}
