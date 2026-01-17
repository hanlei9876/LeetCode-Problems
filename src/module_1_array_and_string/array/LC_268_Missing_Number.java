package module_1_array_and_string.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// This represents a family of "finding missing numbers" problem. Usually, there are a number of ways to solve it as bloew:
// 1 - brute force
// 2 - sort + one pass to compare index with value
// 3 - set
// 4 - sum, find sums' difference
// 5 - XOR
public class LC_268_Missing_Number {

    // solution 1: brute force
    // for each number in range [0, n], iterate through array to find if it is in the array
    // time: O(N^2) | space: O(1)
    public int missingNumber_1(int[] nums) {
        for (int num = 0; num <= nums.length; num++) {
            int i = 0;
            while (i < nums.length) {
                if (num == nums[i]) {
                    break;
                }
                i++;
            }

            if (i == nums.length) {
                return num;
            }
        }

        return -1;
    }

    // solution 2: sort + one pass to compare index with value
    // time: O(NlogN) | space: O(1)
    public int missingNumber_2(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i < nums[i]) {
                return i;
            }
        }

        return nums.length;
    }

    // solution 3: hash set
    // time: O(N) | space: O(N)
    public int missingNumber_3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return -1;
    }

    // solution 4: compare sum
    // time: O(2N) >> O(N) | space: O(1)
    public int missingNumber_4(int[] nums) {
        long arraySum = 0;
        for (int num : nums) {
            arraySum += num;
        }

        long rangeSum = 0;
        for (int i = 0; i <= nums.length; i++) {
            rangeSum += i;
        }

        return (int) (rangeSum - arraySum);
    }

    // solution 5: Bit manipulation - XOR
    // time: O(N) | space: O(1)
    public int missingNumber_5(int[] nums) {
        int missingNumber = nums.length;

        for (int i = 0; i < nums.length; i++) {
            missingNumber ^= (i ^ nums[i]);
        }

        return missingNumber;
    }

    public static void main(String[] args) {
        // XOR operations
        System.out.println(3 ^ 2); // 1
    }
}
