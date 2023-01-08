package module_1_array_and_string.array;

//constraints: 1 <= nums.length <= 100
public class LC_31_Next_Permutation {

    // solution: special algorithm
    // edge case (nums.length == 1) is included
    // time: O(N)
    // space: O(1)
    public void nextPermutation(int[] nums) {
        // step-1: find the first two adjacent elements nums[i-1] & nums[i], where nums[i-1] < nums[i]
        int i = nums.length - 1;
        while (nums.length > 1 && i - 1 >= 0) {
            if (nums[i - 1] < nums[i]) {
                break;
            }

            i--;
        }

        // step-2: within the sub-array to nums[i-1]'s right, find the smallest element nums[j], where nums[j] > nums[i-1]. Then swap nums[j] & nums[i-1]
        if (i >= 1) { // there exists: nums[i-1] < nums[i]
            for (int j = nums.length -1; j >= i; j--) {
                if (nums[j] > nums[i - 1]) {
                    int temp = nums[i - 1];
                    nums[i - 1] =  nums[j];
                    nums[j] = temp;

                    break;
                }
            }
        }

        // step-3: reverse the sub-array to nums[i-1]'s right
        int l = i;
        int r = nums.length - 1;
        while (l < r) {
            int temp = nums[l];
            nums[l] =  nums[r];
            nums[r] = temp;

            l++;
            r--;
        }
    }
}
