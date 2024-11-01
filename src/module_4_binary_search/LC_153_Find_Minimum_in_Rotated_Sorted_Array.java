package module_4_binary_search;


// All the integers of nums are unique
// 1 <= n <= 5000
public class LC_153_Find_Minimum_in_Rotated_Sorted_Array {

    // this problem is the foundation for LC-33

    // solution 1
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
        // Now, we can analyze:
        //   - all elements in [0, left) > nums[n-1]
        //   - all elements in [right, n-1] <= nums[n-1]
        // therefore, we can conclude element at left (right) must be the smallest element compared to nums[n-1]

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

    // solution 2
    // Goal: use binary search to find the largest element in array
    // time: O(logN)
    // space: O(1)
    public int findMin_2(int[] nums) {
        int n = nums.length;

        // edge case 1: length == 1
        if (n == 1) return nums[0];

        // edge case 2: when n >= 2, check if array is rotated
        if (nums[0] < nums[n - 1]) return nums[0];

        // start binary search - use template 1
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // mid is the searched position. We can guarantee that mid will never be n - 1,
            // as the array must be rotated and mid, as largest element in array, will never be the last one
            // therefore, nums[mid + 1] will always be valid - never out of boundary

            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            // nums[mid] < nums[mid + 1] (no duplicates in array)

            // now check which half mid is located ==> just compare nums[mid] vs nums[n - 1] (or nums[mid] vs nums[0])
            // e.g. given [7, 8, 9, 0, 1, 2, 3, 4, 5], we now check if mid is in [7, 8, 9] or [ 0, 1, 2, 3]
            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else { // nums[mid] < nums[n - 1]
                right = mid - 1;
            }
        }

        return Integer.MAX_VALUE; // we will never reach here, just return whatever
    }
}
