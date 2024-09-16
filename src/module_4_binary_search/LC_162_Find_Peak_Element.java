package module_4_binary_search;

// constraints:
//     nums[i] != nums[i + 1] for all valid i
//     1 <= nums.length <= 1000
public class LC_162_Find_Peak_Element {

    // solution 1: linear search
    // key pattern: we only check if nums[i] > nums[i+1] (see note for this pattern)
    //
    // time: O(N)
    // space: O(1)
    public int findPeakElement_v1(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return n - 1;
    }


    // solution 2: binary search (iteration)
    // time: O(logN)
    // space: O(1)
    public int findPeakElement_v2(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // note: mid + 1 will never go out of boundary
            if (nums[mid] > nums[mid + 1]) {
                // mid could be peak, so need to include mid in next search space
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


    // solution 3: binary search (recursion)
    // time: O(logN)
    // space: O(logN)
    //   - logN is the number of spaces of recusion stack
    public int findPeakElement_v3(int[] nums) {
        return recursion(0, nums.length - 1, nums);
    }

    private int recursion(int left, int right, int[] nums) {
        // base case
        if (left == right) {
            return left;
        }

        // recursion relation
        int resultIndex;
        int mid = left + (right - left) / 2;

        if (nums[mid] > nums[mid + 1]) {
            resultIndex = recursion(left, mid, nums);
        } else {
            resultIndex = recursion(mid + 1, right, nums);
        }

        return resultIndex;
    }
}
