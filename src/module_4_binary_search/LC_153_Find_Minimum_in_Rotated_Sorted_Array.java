package module_4_binary_search;


// All the integers of nums are unique
// 1 <= n <= 5000
public class LC_153_Find_Minimum_in_Rotated_Sorted_Array {

    // this is the foundation for LC-33
    // time: O(logN)
    // space: O(1)
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // left & right will meet when out of loop
        return nums[left];
    }
}
