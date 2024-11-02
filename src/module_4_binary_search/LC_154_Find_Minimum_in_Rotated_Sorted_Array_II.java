package module_4_binary_search;

public class LC_154_Find_Minimum_in_Rotated_Sorted_Array_II {

    // possible cases
    // [1, 3, 5, 7] - no rotation
    // [2, 2, 2, 2] - no rotation - all duplicates
    // [3, 5, 5, 7, 1] - rotated - nums[0] != nums[n-1]
    // [5, 7, 1, 3, 5] - rotated - nums[0] == nums[n-1]


    // solution 1: brute force - liner search
    // iterate through array to find nums[i] > nums[i + 1]
    // time: O(N)
    // space: O(1)
    public int findMin(int[] nums) {
        int n = nums.length;

        // edge case
        if (n == 1)
            return nums[0];

        int i = 0;
        while (i < n - 1) {
            if (nums[i] > nums[i + 1]) {
                return nums[i + 1];
            }

            i++;
        }

        return nums[0];
    }

    // solution 2: binary search
    public int findMin_2(int[] nums) {
        return 0;
    }
}
