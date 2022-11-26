package module_1_array_and_string.array;

// You must do this by modifying the input array in-place with O(1) extra memory.
public class LC_26_Remove_Duplicates_from_Sorted_Array {

    // assume if we are allowed to use an extra space, what is the solution?
    // the solutions would be using an extra array or hashmap. Below is the way with array
    // time: O(N)
    // space: O(N)
    public int removeDuplicates(int[] nums) {
        int[] results = new int[nums.length];

        int i = 0;
        int j = 0;
        while (i < nums.length) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                results[j] = nums[i];
                j++;
            }

            i++;
        }

        // copy the elements back to the input array
        for (int k = 0; k < j; k++) {
            nums[k] = results[k];
        }

        return j;
    }

    // two-pointer (fast-slow pointers):
    // fast pointer f: read each element, compare with its previous one, decide if the current should be removed
    // slow pointer s: write each unique element to current index, then move one step to right
    // edge case: nums.length == 1. such as: [1]
    // time: O(N)
    // space: O(1)
    public int removeDuplicates_2(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            if (fast == 0 || nums[fast - 1] != nums[fast]) { // now, fast is pointing to the new element, which should be reserved
                nums[slow] = nums[fast];
                slow++;
                fast++;
            } else {
                fast++;
            }
        }

        return slow;

    }

    // this is another solution provided by the official LeetCode learn module
    // time: O(N^2)
    // space: O(1)
    public int removeDuplicates_3(int[] nums) {

        // The initial length is simply the capacity.
        int length = nums.length;

        // Assume the last element is always unique.
        // Then for each element, delete it iff it is
        // the same as the one after it. Use our deletion
        // algorithm for deleting from any index.
        for (int i = length - 2; i >= 0; i--) {
            if (nums[i] == nums[i + 1]) {
                // Delete the element at index i, using our standard
                // deletion algorithm we learned.
                for (int j = i + 1; j < length; j++) {
                    nums[j - 1] = nums[j];
                }
                // Reduce the length by 1.
                length--;
            }
        }
        // Return the new length.
        return length;
    }

    public static void main(String[] args) {

    }
}
