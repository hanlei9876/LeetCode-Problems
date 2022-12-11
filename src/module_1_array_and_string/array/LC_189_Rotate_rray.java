package module_1_array_and_string.array;

public class LC_189_Rotate_rray {

    // solution 1: brute force
    // time: O(N * k)
    // space: O(1)
    public void rotate(int[] nums, int k) {
        int length = nums.length; // length >= 1

        for (int i = 0; i < k; i++) {
            int temp = nums[length - 1];

            for (int j = length - 2; j >= 0; j--) {
                nums[j+ 1] = nums[j];
            }

            nums[0] = temp;
        }
    }

    // solution 2: use extra array
    // formula: i's new index = (i + k) % len
    // time: O(N)
    // space: O(N)
    public void rotate_2(int[] nums, int k) {
        int[] temp = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            temp[(i + k) % nums.length] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }

    // solution-3: use Reverse to realize Rotation
    // when we rotate the array k times (k < len), k elements from the back end of the array come to the front, and
    // the rest of the elements from the front shift backwards.
    public void rotate_3(int[] nums, int k) {
        int step = k % nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, step - 1);
        reverse(nums, step, nums.length - 1);
    }

    // helper method for solution 3
    void reverse(int[] nums, int L, int R) {
        int i = L;
        int j = R;
        while (i < j) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;

            i++;
            j--;
        }
    }
}
