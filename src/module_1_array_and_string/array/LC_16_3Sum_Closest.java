package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_16_3Sum_Closest {

    // overall strategy:
    // no matter which solution, we need to always figure out to iterate each possible triplet, so to find the closest one

    // solution-1: brute force
    // goal: iterate each triplet
    // no-sort, find each triplet, maintain minAbs & triplet sum
    // time: O(N^3) - The total number of such triplets is C(N 3)
    // space: O(1)



    // solution-2: sort + two-pointer
    // goal: iterate each triplet
    // time: O(NlogN + N^2) >> O(N^2)
    // space: O(logN)
    public int threeSumClosest(int[] nums, int target) {
        int closetSum = 0; // hold result
        int minDistance = Integer.MAX_VALUE;

        Arrays.sort(nums);

        // iterate each triplet by:
        //  - fixing one element
        //  - then, using two-pointer on sorted array
        // so, we can find the triplet whose sum is closet to target
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int currSum = nums[i] + nums[l] + nums[r];
                int currDistance = Math.abs(currSum - target);

                if (currSum == target) {
                    return currSum;
                }

                if (currDistance < minDistance) {
                    closetSum = currSum;
                    minDistance = currDistance;
                }

                // determine how to move pointers next
                if (currSum > target) {
                    r--;
                } else {
                    l++;
                }
            }
        }

        return closetSum;
    }
}
