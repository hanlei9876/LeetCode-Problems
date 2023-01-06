package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_16_3Sum_Closest {

    // solution-1: brute force
    // no-sort, find each triplet, maintain minAbs & triplet sum
    // time: O(N^3) - The total number of such triplets is C(N 3)
    // space: O(1)



    // solution-2: sort + two-pointer
    // time: O(NlogN + N^2) >> O(N^2)
    // space: O(1)
    public int threeSumClosest(int[] nums, int target) {
        int closetSum = 0;
        int minAbs = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return sum;
                }

                int abs = Math.abs(sum - target);
                if (abs < minAbs) {
                    closetSum = sum;
                    minAbs = abs;
                }

                if (sum > target) {
                    r--;
                } else {
                    l++;
                }
            }
        }

        return closetSum;
    }
}
