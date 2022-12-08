package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_561_Array_Partition {

    // Approach 1: sort first, then, pair the adjacent ones
    // time: O(NlogN + N/2) = O(NlogN)
    // space: O(log(N))
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int sum = 0;
        for (int i = 0; i < nums.length; i+=2) {
            sum = sum + nums[i];
        }

        return sum;
    }
}
