package module_4_binary_search;

// 0 <= nums.length <= 10^5
// nums is a non-decreasing array (there might be duplicates)
public class LC_34_Find_First_and_Last_Position_of_Element_in_Sorted_Array {

    // solution 1: linear search
    // time: O(N)
    // space: O(1)
    public int[] searchRange_v1(int[] nums, int target) {
        int n = nums.length;
        int start = -1;
        int end = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                start = i;

                for (int j = i; j < n; i++) {
                    if (j == n - 1 || nums[j] != nums[j + 1]) {
                        end = j;
                        break;
                    }
                }

                break;
            }
        }

        return new int[]{start, end};
    }

    // solution 2: 2 binary searches to find left & right boundaries respectively
    // time: O(2logN) >> O(logN)
    // space: O(1)
    public int[] searchRange_v2(int[] nums, int target) {
        // find left-most, if left-most doesn't exist, it indicates target doesn't exist >> return [-1, -1]
        int leftBoundary = binarySearchLeftBoundary(nums, target);

        if (leftBoundary == -1) {
            return new int[]{-1, -1};
        }

        // if left-most exists, then find right-most
        int rightBoundary = binarySearchRightBoundary(nums, target);

        return new int[]{leftBoundary, rightBoundary};
    }

    int binarySearchLeftBoundary(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // nums[mid] == target
                if (mid == 0 || nums[mid] != nums[mid - 1]) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    int binarySearchRightBoundary(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // nums[mid] == target
                if (mid == n - 1 || nums[mid] != nums[mid + 1]) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }
}
