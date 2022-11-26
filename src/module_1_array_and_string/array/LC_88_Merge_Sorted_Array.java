package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_88_Merge_Sorted_Array {

    // approach 1: brute force
    // time: O(N) + O( (M+N)log(M+N) ) = O( (M+N)log(M+N) )
    // space: O(M)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // step 1: copy nums2 to the rest of nums1
        // time: O(N)
        // space: O(1)
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }

        // step 2: sort nums1
        // time: O( (M+N)log(M+N) )
        // space: O(M + N)
        Arrays.sort(nums1);
    }

    // approach 2: (optimized brute force) three pointers starting from the beginning
    // time: O(M) + O(M + N) = O(2M + N) = O(2M) + O(N) = O(M) + O(N) = O(M + N)
    // space: O(M)
    public void merge_2(int[] nums1, int m, int[] nums2, int n) {
        // step 1: create a new array to hold all elements from m1
        int[] nums1Copy = new int[m];

        for (int i = 0; i < m; i++) {
            nums1Copy[i] = nums1[i];
        }

        // step 2: use three pointers to copy elements
        int p1 = 0;
        int p2 = 0;
        int p = 0;

        while (p < m + n) {
            if (p1 < m && p2 < n) {
                if (nums1Copy[p1] < nums2[p2]) {
                    nums1[p] = nums1Copy[p1];
                    p1++;
                } else {
                    nums1[p] = nums2[p2];
                    p2++;
                }
            } else if (p1 >= m) {
                nums1[p] = nums2[p2];
                p2++;
            } else { // p2 > n
                nums1[p] = nums1Copy[p1];
                p1++;
            }

            p++;
        }
    }

    // approach 3: three pointers starting from the end
    // time: O(M + N)
    // space: O(1)
    public void merge_3(int[] nums1, int m, int[] nums2, int n) {
        // prepare three pointers
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        // only p1 or p2 could potentially be out of boundaries, p will never go beyond boundary
        while (p >= 0) {
            // either p1 or p2 could go beyond boundary
            if (p1 >= 0 && p2 >= 0) {
                if (nums1[p1] >= nums2[p2]) {
                    nums1[p] = nums1[p1];
                    p1--;
                } else {
                    nums1[p] = nums2[p2];
                    p2--;
                }
            } else if (p1 < 0) {
                nums1[p] = nums2[p2];
                p2--;
            } else { // p2 < 0
                nums1[p] = nums1[p1];
                p1--;
            }

            p--;
        }
    }

    public static void main(String[] args) {

    }
}
