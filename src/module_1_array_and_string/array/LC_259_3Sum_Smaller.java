package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_259_3Sum_Smaller {

    // solution-1: brute force - find every triplet
    // noo-sort, find every triplet, check if it is valid
    // time: O(N^3) - The total number of such triplets is C(N 3)
    // space: O(1)

    // solution-2: sort + two-pointer
    // time: O(NlogN + N^2) >> O(N^2)
    // space: O(1)
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3)
            return 0;

        int count = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum >= target) {
                    r--;
                } else {
                    /*
                    The code below is equivalent to:
                    If (sum < target),
                        (1) We first need to move r to the left and count all these valid pairs until l == r.
                        (2) Then, move r back to its original index.
                        (3) Next, move l one step to its right, for the next loop.
                    */
                    int validCounts = r - l;
                    count = count + validCounts;
                    l++;
                }
            }
        }

        return count;
    }
}
