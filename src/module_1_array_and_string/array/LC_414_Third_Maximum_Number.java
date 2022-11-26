package module_1_array_and_string.array;

import java.util.Arrays;

// edge case: [1], [3, 5]
public class LC_414_Third_Maximum_Number {

    // solution-1: intuitive approach (this solution below is NOT brute force)
    // step-1: sort the array
    // step-2: start from right, use a counter
    // time: O(nlogn + n) => O(nlogn)
    // space: O(1)
    // Note: The built-in sort methods do use some additional space, you can tell this during the interview,
    // but, the interviewer does not expect us to go into much detail about it, and it will be fine if we state the above space complexity analysis.
    public int thirdMax_1(int[] nums) {
        // can I change this input array in place? - assume yes
        Arrays.sort(nums);

        int len = nums.length;
        int max = nums[len - 1]; // after sorting, right-most element is always max

        // handle edge case: len < 3
        if (len < 3)
            return max;

        // now, len is always >= 3
        int count3rd = 2; // this is to count the 3rd max. when count3rd == 0, it's 3rd max
        int i = len - 2;
        while (i >= 0) {
            // as there are duplicates, we have to consider only distinct numbers
            if (nums[i] < nums[i + 1]) {
                count3rd--;
            }

            if (count3rd == 0) {
                break;
            }

            i--;
        }

        return count3rd == 0 ? nums[i] : max;
    }

    // when we need to find kth max value
    public int thirdMax_2(int[] nums, int k) {
        if (nums.length == 1)
            return nums[0];

        // can I change this input array in place? - assume yes
        Arrays.sort(nums);

        int len = nums.length;
        int max = nums[len - 1]; // after sorting, right-most element is always max

        if (len < k)
            return max;

        int countkth = k - 1;

        // below, we need to ensure len >= 2. Otherwise nums[i] will be out of bounds
        int i = len - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1])
                countkth--;

            if (countkth == 0)
                break;

            i--;
        }

        return countkth == 0 ? nums[i] : max;
    }

    // solution-3: 3-pointers
    // time: O(n)
    // space: O(1)
    public int thirdMax_2(int[] nums) {
        Integer[] max = new Integer[3]; // initial values are null

        max[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // handle duplicated elements
            if (nums[i] == max[0] || (max[1] != null && nums[i] == max[1]) || (max[2] != null && nums[i] == max[2])) {
                continue;
            }

            if (nums[i] > max[0]) {
                max[2] = max[1];
                max[1] = max[0];
                max[0] = nums[i];
            } else if (max[1] == null) {
                max[1] = nums[i];
            } else if (nums[i] > max[1]) {
                max[2] = max[1];
                max[1] = nums[i];
            } else if (max[2] == null) {
                max[2] = nums[i];
            } else if (nums[i] > max[2]) {
                max[2] = nums[i];
            }
            // else denotes (max[2] > num[i]) >> we simply do nothing and skip this element
        }

        return max[2] == null ? max[0] : max[2];
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[3];
        a[0] = 1;
        if (a[0] == 1) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }

}
