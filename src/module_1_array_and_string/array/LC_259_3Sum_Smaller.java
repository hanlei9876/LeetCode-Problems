package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_259_3Sum_Smaller {

    // Unlike LC-15 3Sum, we cannot use a hash set approach, as we are not looking for a fixed value

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

    // solution-3: sort + binary search
    // sort array >> fix two elements >> binary search for 3rd element
    //
    // How to count all qualified triplets?
    // Answer: change binary search goal
    //   from: iterate through each triplet & count qualified ones (as it's not possible)
    //   to: search for largest value that is < target. Then calculate the count [j+1, mid]
    //
    // time: O(NlogN) + O((N^2)logN)
    // space: O(logN)
    public static int threeSumSmaller_3(int[] nums, int target) {
        int totalCount = 0;

        Arrays.sort(nums);

        // fix two elements
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {

                // use binary search to search for the largest value that is < target
                int L = j + 1;
                int R = nums.length - 1;

                while (L < R) {
                    // we cannot use "mid = (L + R) / 2", because this will cause infinite loop of while-loop.
                    // The reason is due to the terminating condition when there are two elements left.
                    // If we chose the lower middle element and the condition nums[mid]<target evaluates to true, then the loop would never terminate.
                    // Choosing the upper middle element will guarantee termination.
                    // for example:
                    //      [ 1, 3 ]
                    //        L  R
                    // if (M == L), when (currSum < target), then L = Mid will cause dead loop
                    int mid = (L + R + 1) / 2;
                    int currSum = nums[i] + nums[j] + nums[mid];

                    if (currSum >= target) {
                        R = mid - 1;
                    } else {
                        L = mid; // if currSum < target: mid could potentially be the answer
                    }
                }

                // Post-processing is required: check if L == R is valid
                // if we don't check here, result is wrong. see example {-2,0,1,3}, target=2
                if (nums[i] + nums[j] + nums[L] < target) {
                    totalCount = totalCount + (L - j);
                }

            }
        }

        return totalCount;
    }

    public static void main(String[] args) {
        int res = threeSumSmaller_3(new int[]{-2,0,1,3}, 2);
        System.out.println(res);
    }
}
