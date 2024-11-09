package module_4_binary_search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 1 <= nums.length
public class LC_287_Find_the_Duplicate_Number {

    // solution 1: sort
    // time: O(NlogN + N) >> O(NlogN)
    // space: O(logN)
    public int findDuplicate_1(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }

        return -1; // we will never reach here
    }

    // solution 2: set
    // time: O(N)
    // space: O(N)
    public int findDuplicate_2(int[] nums) {
        Set<Integer> set =  new HashSet<>();

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                return num;
            }
        }

        return -1; // we will never reach here
    }


    // (Pigeonhole principle) Given value range [1, n], pick elements from there to fit in array where length == n + 1
    //
    // According to this feature, we can treat each element in array as a (valid) index of this array.
    //
    // For example, given array's length == 4, we have available elements in range [1, 2, 3],
    // the array [x, x, x, x]
    //    index   0  1  2  3
    // Since all numbers are in the range [1,3], they will be mapped to indices [1 ~ n], and hence no number will be mapped to index 0.
    //
    // With this feature, we can derive 2 approaches to solve the problem:
    //   1) negative marking - see solution 3
    //   2) treat array as HashMap - see solution 4.1 & 4.2


    // solution 3: negative marking (modify array in the middle)
    // prerequisites to use negative marking
    //   - each value in array must be as a valid index of the array, in this case: 1 ~ n as values in array (length n)
    //   - each value must be non-negative. Otherwise, we can't determine if a negative value is from original array or modified by use before
    //
    // time: O(N) + O(N) >> O(N)
    // space: O(1)
    public int findDuplicate_3(int[] nums) {
        int res = -1;

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);

            if (nums[val] < 0) {
                res = val;
                break;
            }

            nums[val] = nums[val] * (-1);
        }

        // change negative elements back to positive
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = nums[i] * (-1);
            }
        }

        return res;
    }


    // solution 4.1: treat array as HashMap - recursion (modify array) - see note for example graph
    // Goal: to set each position i to meet i == nums[i]
    // Therefore, the number of calls depends on the number of positions where i != nums[1]
    // for example, given:
    //   array [2, 3, 1, 2]   ====>   [0, 1, 2, 3]
    //   index  0  1  2  3             0  1  2  3
    // Note: 0 here is a dummy value
    // for each position, i != nums[1]. So, the approach will eventually access each element. So, time: O(N)
    // time: O(N)
    // space: O(N)
    public int findDuplicate_4_1(int[] nums) {
        return helper(nums, 0);
    }

    private int helper(int[] nums, int index) {
        // base case
        if (index == nums[index]) {
            return index;
        }

        // recursion relation
        int nextIndex = nums[index];
        nums[index] = index;
        return helper(nums, nextIndex);
    }


    // solution 4.2 (cyclic sort): treat array as HashMap - iteration - see note for example graph
    // Goal: to always stay at index 0, and to map the number at index 0 to its equivalent index
    // for example, given:
    //   array [1, 3, 4, 2, 2]   ====> [2, 1, 2, 3, 4]
    //   index  0  1  2  3  4           0  1  2  3  4
    //
    // time: O(N) - worst case: at each index from 1 ~ n-1, index != nums[index]
    // space: O(1)
    public int findDuplicate_4_2(int[] nums) {

        while (nums[0] != nums[nums[0]]) {
            int idx = nums[0];

            // swap values in index 0 & nums[0]

            int val = nums[0];
            nums[0] = nums[idx];
            nums[idx] = val;
        }

        return nums[0];
    }


    // solution 5: binary search
    //
    // background:
    //  1) an array that has n distinct numbers in the range [1,n]. For example: [1,2,3,4,5],
    //     if we pick the number 4, (==) there's exactly 4 numbers that are less than or equal to 4
    //  2) However, when we have duplicates in the array, this count will exceed the number at some point. For example: in [4,3,4,5,2,4,1],
    //     if we pick the number 4, which is the duplicate, (<) has 6 numbers that are less than or equal to it
    //     similarly, for number 5
    //
    // Given an array length == n+1, then each element is in range [1 ~ n]. For each element, there are 3 cases:
    //    case-1: element in the array, no duplicate for it
    //    case-2: element not in array
    //    case-3: element in the array, it has duplicate
    //
    // Example 1 (no missing value): array [1,  2,  3,  3,  4], range 1 ~ 4
    //     value vs count of elements
    //       1   =    1
    //       2   =    2
    //       3   <    4
    //       4   <    5
    //
    // Example 2: (value 2 is missing): array [1,  3,  3,  3,  4], range 1 ~ 4
    //     value vs count of elements
    //       1   =    1
    //       2   >    0
    //       3   <    4
    //       4   <    5
    //
    // goal: find the smallest number that satisfies this property - the count of elements in array <= the number itself
    //
    // time: O(NlogN)
    // space: O(1)
    public int findDuplicate_5(int[] nums) {
        // define initial search range
        int L = 1;
        int R = nums.length - 1;

        while (L < R) {
            int mid = L + (R - L) / 2;

            // count how many elements in array are <= mid
            int count = 0;
            for (int num : nums) {
                if (num <= mid)
                    count++;
            }

            // check count vs mid - there 3 cases (see the 2 examples above):
            //    1) mid == count
            //    2) mid > count
            //    3) mid < count
            if (mid >= count) {
                L = mid + 1;
            } else {
                // mid < count
                R = mid;
            }
        }

        // L == R
        return L;

    }
}
