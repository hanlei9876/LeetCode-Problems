package module_4_binary_search;


// All the integers of nums are unique
// 1 <= n <= 5000
public class LC_153_Find_Minimum_in_Rotated_Sorted_Array {

    // this is the foundation for LC-33
    // Goal: use binary search to find the smallest element compared to nums[n-1]
    // time: O(logN)
    // space: O(1)
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        // use template 2
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // similar to template 1, this template will search each single element in array.
        // after search on all elements is done, we come to left==right.

        // NOTE: the case below will cause dead loop
        /*
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid;
        }
        }*/

        // left & right will meet when out of loop
        return nums[left];
    }
}
