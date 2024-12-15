package module_4_binary_search;

// Constraints:
//   1 <= nums.length <= 10^5
//   0 <= nums[i] <= 10^5
public class LC_540_Single_Element_in_a_Sorted_Array {

    // Observation 1: from constraints, we can see: nums.length >= 1, but nums.length != 2
    // Observation 2: the nums.length is always odd
    // Observation 3: target is always sitting on even index

    // edge cases:
    // [1]
    // [1, 3, 3]
    // [1, 1, 5]
    //
    // ordinary cases:
    // [1, 1, 3, 5, 5]
    // [1, 1, 3, 3, 5]


    // solution 1: brute force (linear search)
    // time: O(N/2) >> O(N)
    // space: O(1)
    public int singleNonDuplicate_1(int[] nums) {
        int i = 0;
        while (i < nums.length - 1) { // ensures nums[i+1] always inbound
            if (nums[i] != nums[i + 1]) {
                break;
            }

            i = i + 2;
        }

        return nums[i];
    }

    // solution 2: binary search
    //   - the subarray containing target has odd-numbered elements
    //   - the subarray NOT containing target has even-numbered elements
    //
    // time: O(logN)
    // space: O(1)
    public int singleNonDuplicate_2(int[] nums) {
        int L = 0;
        int R = nums.length - 1;

        // binary search - variant of template 2
        // 2 points:
        //  - nums.length == 1 or >= 3
        //  - every time the flow go into the while loop, the length between [L, R] is always odd
        // these 2 points will ensure both mid-1 & mid+1 are within bound
        while (L < R) {
            int mid = L + (R - L) / 2;

            // step 1 - check if mid is target or not
            if (nums[mid - 1] != nums[mid] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            // step-2: since mid is not target, identify the twin pair containing mid - (mid-1, mid) or (mid, mid+1)
            if (nums[mid - 1] == nums[mid]) {
                // ignoring the twin pair, check if left part is odd, or right part is odd
                if ((mid - 1 - L) % 2 == 1) {
                    R = mid - 2;
                } else {
                    L = mid + 1;
                }
            } else { // twin pair - (mid, mid+1)
                // ignoring the twin pair, check if left part is odd, or right part is odd
                if ((mid - L) % 2 == 1) {
                    R = mid - 1;
                } else {
                    L = mid + 2;
                }
            }
        }

        // now L == R is the answer
        return nums[L];
    }

    // solution 3: binary search on even indexes
    // for each (even) mid,
    //    if nums[mid] == nums[mid+1], target on right half
    //    if nums[mid] != nums[mid+1], target on left half or mid is target
    //
    // time: O(log(N/2)) >> O(logN)
    // space: O(1)
    public int singleNonDuplicate_3(int[] nums) {
        int L = 0;
        int R = nums.length - 1; // R is even due to odd length of nums

        while (L < R) {
            int mid = L + (R - L) / 2;

            if (mid % 2 == 1)
                mid--;

            /*if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }*/

            if (nums[mid] == nums[mid + 1]) {
                L = mid + 2;
            } else {
                R = mid; // mid could potentailly be the target
            }
        }

        return nums[L];
    }

    // Another interesting observation is that the 2 binary search solutions will still work even if the array isn't fully sorted.
    // As long as pairs are always grouped together in the array (for example, [10, 10, 4, 4, 7, 11, 11, 12, 12, 2, 2]), it doesn't matter what order they're in.
    // Binary search worked for this problem because we knew the subarray with the single number is always odd-lengthed, not because the array was fully sorted numerically.
    // We commonly call this an invariant (不变性), something that is always true (i.e. "The array containing the single element is always odd-lengthed").
}
