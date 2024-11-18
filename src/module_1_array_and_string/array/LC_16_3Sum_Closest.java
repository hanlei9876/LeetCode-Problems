package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_16_3Sum_Closest {

    // there are several scenarios we need to discuss upfront:
    //  1) if the target 3sum (equals to target) exists in array - just return it
    //  2) if the target 3sum (equals to target) does NOT exist in array, according to problem description:
    //     - for the 3sum that's closest to target, there must be one triplet ONLY
    //     - for the 3sum that's second closest to target, there might be one or more triplets.
    //
    // therefore, the overall strategy:
    // no matter which solution, we need to always figure out to iterate each possible triplet, keep tracking minDistance + closeSum,
    // when the traverse over all triplets is done, we will certainly obtain the unique closest triplet

    // solution-1: brute force
    // goal: iterate each triplet
    // no-sort, find each triplet, maintain minAbs & triplet sum
    // time: O(N^3) - The total number of such triplets is C(N 3)
    // space: O(1)



    // solution-2: sort + two-pointer
    // goal: iterate each triplet
    // time: O(NlogN + N^2) >> O(N^2)
    // space: O(logN)
    public int threeSumClosest_2(int[] nums, int target) {
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

    // solution-3: sort + binary search
    // goal: fix two elements + binary search for 3rd element
    // time: O(NlogN + (N^2)logN) >> O((N^2)logN)
    // space: O(logN)
    public static int threeSumClosest_3(int[] nums, int target) {
        int closestSum = 0; // hold result
        int minDistance = Integer.MAX_VALUE;

        Arrays.sort(nums);

        // fix two elements
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {

                // use binary search to quickly iterate 3rd element
                int L = j + 1;
                int R = nums.length - 1;

                // use template 1 - to ensure each "3rd" element is iterated within while-loop body
                while (L <= R) {
                    int mid = L + (R - L) / 2;
                    int currSum = nums[i] + nums[j] + nums[mid];
                    int currDistance = Math.abs(currSum - target);

                    if (currDistance == 0) {
                        return currSum;
                    }

                    // track current 3sum
                    if (currDistance < minDistance) {
                        minDistance = currDistance;
                        closestSum = currSum;
                    }

                    // move pointers for next search
                    if (currSum < target) {
                        L = mid + 1;
                    } else {
                        R = mid - 1;
                    }
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        int res = threeSumClosest_3(new int[]{1,1,1,0}, -100);
        System.out.println(res);
    }
}
