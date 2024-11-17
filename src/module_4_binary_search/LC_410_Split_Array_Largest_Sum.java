package module_4_binary_search;


// constraints:
//  1 <= nums.length <= 1000
//  0 <= nums[i] <= 10^6
//  1 <= k
public class LC_410_Split_Array_Largest_Sum {


    // solution 3 - binary search (see graph in the note)
    // search goal: minimum largest-sum of each k-split solution
    // search scope: [maxValue, array's sum]
    //
    // time: O(NlogM) - N is array size, M is array sum
    // space: O(1)
    public int splitArray_3(int[] nums, int k) {
        // iterate through array to find binary search scope - [maxValue, array's sum]
        int maxVal = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (maxVal < nums[i]) {
                maxVal = nums[i];
            }

            sum = sum + nums[i];
        }

        // binary search - this is a variant of template-2
        // we can 100% confirm the target must exit in the range
        int L = maxVal;
        int R = sum;
        while (L < R) {
            int mid =  L + (R - L) / 2;

            int subArrayCount = getCountOfSubArrays(nums, mid);

            if (subArrayCount > k) {
                L = mid + 1;
            } else if (subArrayCount < k) {
                R = mid - 1;
            } else {
                // when subArrayCount == k, mid could be, or NOT be the final solution
                R = mid;
            }
        }

        return L; // L == R is the answer
    }

    // we are counting the number of splitted sub-arrays, where each sub-array's sum <= mid
    private int getCountOfSubArrays(int[] nums, int subArraySumCap) {
        int splitSubArrayCount = 0;
        int currSum = 0;

        for (int i = 0; i < nums.length; i++) {
            currSum = currSum + nums[i];

            // if (currSum <= subArraySumCap), then we should move i forward
            if (currSum > subArraySumCap) {
                // we now found a qualified split sub-array// we now found a qualified split sub-array, ending at i-1
                splitSubArrayCount++;
                currSum = nums[i]; // refresh currSum
            }
        }

        splitSubArrayCount++;

        return splitSubArrayCount;
    }
}
