package module_1_array_and_string.array;

public class LC_485_MaxConsecutiveOnes {

    // time: O(n)
    // space: O(1)
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                n++;
            else {
                /*
                if (n > max)
                    max = n;
                */
                max = Math.max(n, max);

                // n cannot be placed inside the if-block above
                // n will be set to 0 anyway, as we need to re-count 1s after this 0
                n = 0;
            }
        }

        // when the loop is completed, we need to compare max and n once again,
        // because, there are two ways to stop count 1s:
        // (1) switch from 1 to 0 in the array
        // (2) the array is looped over while the last element is 1
        /*
        if (n > max)
            max = n;
        */
        max = Math.max(n, max);

        return max;
    }

    // solution-2: my own solution using sliding window
    // No need to actually keep the count variable. The number of 1s is the length of the sliding window.
    // time: O(N)
    // space: O(1)
    public static int findMaxConsecutiveOnes_2(int[] nums) {
        int longestOnes = 0;

        int L = 0;
        int R = 0;
        while (R < nums.length) {
            if (nums[R] == 0) {
                R++;
                L = R;
            } else {
                longestOnes = Math.max(longestOnes, R - L + 1);
                R++;
            }
        }

        return longestOnes;
    }

    // another solution from Discussion, using sliding window
    // No need to actually keep the count variable. The number of 1s is the length of the sliding window.
    //Solution beats 100% with memory beating > 90%.
    public static int findMaxConsecutiveOnes_3(int[] nums) {
        int n = nums.length;
        int max = 0, left = 0, right = 0;

        while (left < n && right < n) {
            while (left < n && nums[left] == 0)
                left++;
            right = left;
            while (right < n && nums[right] == 1)
                right++;
            max = Math.max(max, right - left);
            left = right;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,0,1,1,1};
        int result = findMaxConsecutiveOnes(nums);
        System.out.println(result);

    }
}
