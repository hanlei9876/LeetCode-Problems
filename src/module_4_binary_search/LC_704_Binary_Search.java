package module_4_binary_search;

// nums.length >= 1
// nums is sorted
// nums has no duplicate values
public class LC_704_Binary_Search {
}


// solution 1: find the exact value
// time: O(logN)
// space: O(1)
class LC_704_Binary_Search_v1 {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return - 1;
    }
}


// solution 2: find the rightmost position to insert
// example: given [3, 7, 8, 8, 8, 10], target = 8
// the index (0-based) to insert is "left", which is index = 5
// time: O(logN)
// space: O(1)
class LC_704_Binary_Search_v2 {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // each index from left to right (inclusive) is possible to insert

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // check either left or right
        if (left > 0 && nums[left - 1] == target) {
            return left - 1;
        } else {
            return -1;
        }
    }
}


// solution 3: find the leftmost position to insert
// example: given [3, 7, 8, 8, 8, 10], target = 8
// the index (0-based) to insert is "left/right index", which is index = 2
// time: O(logN)
// space: O(1)
class LC_704_Binary_Search_v3 {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // each index from left to right (inclusive) is possible to insert

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // check either left or right
        if (left < nums.length && nums[left] == target) {
            return left;
        } else {
            return -1;
        }
    }
}