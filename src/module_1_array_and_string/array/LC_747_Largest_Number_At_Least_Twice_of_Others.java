package module_1_array_and_string.array;

public class LC_747_Largest_Number_At_Least_Twice_of_Others {

    // time: O(N)
    // space: O(1)
    public int dominantIndex(int[] nums) {
        int max = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }

        int res = index;

        for (int i = 0; i < nums.length; i++) {
            if (i == index)
                continue;

            if (max < 2*nums[i]) {
                res = -1;
                break;
            }
        }

        return res;
    }

    // more graceful
    public int dominantIndex_2(int[] nums) {
        int max = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != index && max < 2*nums[i]) {
                return -1;
            }
        }

        return max;
    }
}
