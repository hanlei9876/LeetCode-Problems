package module_4_binary_search;

// constraints:
//   0 <= m <= 1000
//   0 <= n <= 1000
//   1 <= m + n <= 2000
public class LC_4_Median_of_Two_Sorted_Arrays {

    // this is for solution 1
    int p1 = 0, p2 = 0;

    // solution 1: Linear search - merge sort
    // time: O((M+ N)/2) >> O(M + N)
    // space: O(1)
    public double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // keep calling getMinAndMove(), to move all the ways to point to the target median element, then
        //  - if m+n is odd, call getMinAndMove() once to get result
        //  - if m+n is even, call getMinAndMove() twice to calculate result
        if ((m + n) % 2 == 1) {
            for (int i = 0; i < (m + n) / 2; i++) {
                getMinAndMove(nums1, nums2);
            }

            return getMinAndMove(nums1, nums2);
        } else {
            for (int i = 0; i < (m + n) / 2 - 1; i++) {
                getMinAndMove(nums1, nums2);
            }

            int leftValue = getMinAndMove(nums1, nums2);
            int rightValue = getMinAndMove(nums1, nums2);

            return (double) (leftValue + rightValue) / 2;
        }
    }

    private int getMinAndMove(int[] nums1, int[] nums2) {
        /*
        the only purpose of this method is to:
          1) first, compare which pointer has smaller value
          2) second, return the current value
          3) then, move pointer 1 step forward
        * */

        if (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] <= nums2[p2]) {
                return nums1[p1++];
            } else {
                return nums2[p2++];
            }
        } else if (p1 < nums1.length) {
            return nums1[p1++];
        } else if (p2 < nums2.length) {
            return nums2[p2++];
        } else {
            // in this problem, we will never reach this scenario, so just return anything
            return -1;
        }
    }

    // skip solution 2

    // solution 3: find split element in shorter array using binary search
    // time: O(logmin(m, n))
    // space: O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // always to binary-search the shorter array, so to get O(logmin(M, N)) time
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // from below, we can make sure nums1.length <= nums2.length

        int m = nums1.length;
        int n = nums2.length;

        // create search space for nums1
        int L = 0;
        int R = m;
        while (L <= R) { // when L == R == split1, it could possibly point to index = m, which meaning entire nums1 is on the left side of merged array
            // in order to split nums1 & nums2, we do the following to
            //   - assume split1 is first element of right half of nums1
            //   - split2 will be the first element of right half of nums2
            // the median of merged array is not included in the right halves of nums1 & nums2
            // this is always right, no matter m + n is odd or even:
            int split1 = L + (R - L) / 2;
            int split2 = (m + n + 1) / 2 - split1; // challenging to understand

            // calculate the 4 indexes to validate if this split is solution: maxLeft1, minRight1, maxLeft2, maxRight2
            int maxLeft1 = (split1 == 0) ? Integer.MIN_VALUE : nums1[split1 - 1];
            int minRight1 = (split1 == m) ? Integer.MAX_VALUE : nums1[split1];
            int maxLeft2 = (split2 == 0) ? Integer.MIN_VALUE : nums2[split2 - 1];
            int minRight2 = (split2 == n) ? Integer.MAX_VALUE : nums2[split2];

            // validate if this partition is valid
            /*
                There are only 3 possible relationships between them:
                    (1) maxLeftA <= minRightB && maxLeftB <= minRightA ⇒ we find the solution
                    (2) maxLeftA <= minRightB && maxLeftB > minRightA ⇒ not solution
                    (3) maxLeftA >= minRightB && maxLeftB > minRightA ⇒ not solution

                There will never be the case below (we can verify from case 2, 3):
                        maxLeftA > minRightB && maxLeftB > minRightA
            * */
            if (maxLeft1 > minRight2) {
                R = split1 - 1;
                continue;
            }

            if (minRight1 < maxLeft2) {
                L = split1 + 1;
                continue;
            }

            // now, we come to (1) maxLeftA <= minRightB && maxLeftB <= minRightA ⇒ we find the solution
            if ((m + n) % 2 == 1) {
                return Math.max(maxLeft1, maxLeft2);
            } else {
                int val_L = Math.max(maxLeft1, maxLeft2);
                int val_R = Math.min(minRight1, minRight2);

                return (val_L + val_R) / 2.0;
            }
        }

        return 0.0; // we will never reach here
    }
}
