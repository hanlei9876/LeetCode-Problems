package module_4_binary_search;

// elements are unique
// array is an ascending array that is possibly rotated
public class LC_33_Search_in_Rotated_Sorted_Array {

    // solution 1: linear search
    // time: O(N)
    // space: O(1)

    // solution 2: find pivot using binary search (see note) + binary search
    // pivot is the smallest number
    // time: O(logN)
    // space: O(1)
    public int search_v1(int[] nums, int target) {
        // find pivot using binary search (template) - see LC-153 for template 2 of Binary Search
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        /*while (left <= right) {
            int mid = left + (right - left) / 2;

            // when (nums[mid] == nums[n - 1], we move right. This ensures left is pivot
            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }*/

        // use standard template 2 from LeetCode (see LC-153)
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // left & right will meet when out of loop

        int pivot = left;

        // binary search
        if (nums[n - 1] == target) {
            return n - 1;
        } else if (nums[n - 1] > target) {
            return binarySearch(pivot, n - 1, target, nums);
        } else {
            return binarySearch(0, pivot - 1, target, nums);
        }
    }

    private int binarySearch(int leftBoundary, int rightBoundary, int target, int[] nums) {
        int left = leftBoundary;
        int right = rightBoundary;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }




    // solution 2: find pivot using binary search + binary search on shifted array
    //   - index mapping between shifted & original array
    //   - "shift" here means shift to right. e.g. given index = 3, after 1-step shift, index = 4
    // time: O(logN)
    // space: O(1)
    public int search_v2(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int pivot = left;

        return binarySearchShifted(nums, target, pivot);
    }

    private int binarySearchShifted(int[] nums, int target, int pivot) {
        int n = nums.length;
        int shift = n - pivot;
        int left = (pivot + shift) % n;
        int right = (pivot - 1 + shift) % n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int originalMid = (mid - shift + n) % n;

            if (nums[originalMid] == target) {
                return originalMid;
            } else if (nums[originalMid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }




    // solution 4: one binary search
    // time: O(logN)
    // space: O(1)
    public int search_v3(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // check which half is monotonic interval (单调区间)
            // the edge case of "nums[left] == nums[mid]" is when left == right
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
