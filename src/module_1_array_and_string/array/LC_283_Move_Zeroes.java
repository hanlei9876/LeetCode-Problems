package module_1_array_and_string.array;

public class LC_283_Move_Zeroes {

    // two-pointers: fast-slow
    // time: O(N)
    // space: O(1)
    // edge cases: [1], [0]
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;

        // step-1: move non-zero elements to the left with the same relative order
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] =  nums[fast];
                slow++;
            }
            fast++;
        }
        // at this moment, slow is the count of non-zero elements, pointing to the first remain element

        // step-2: replace all the remain elements with zeros
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }



    public static void main(String[] args) {

    }
}
