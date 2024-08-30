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

