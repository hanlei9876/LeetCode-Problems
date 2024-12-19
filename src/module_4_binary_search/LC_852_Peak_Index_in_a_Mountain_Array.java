package module_4_binary_search;


// constraints:
//  - 3 <= arr.length <= 105
//  - 0 <= arr[i] <= 106
//  - arr is guaranteed to be a mountain array
public class LC_852_Peak_Index_in_a_Mountain_Array {

    // solution 1: linear search
    // time: O(N)
    // space: O(1)
    public int peakIndexInMountainArray_1(int[] arr) {
        int i = 0;
        while (i < arr.length - 1) {
            if (arr[i] > arr[i + 1]) {
                break;
            }

            i++;
        }

        return i;
    }

    // solution 2: binary search
    // time: O(logN)
    // space: O(1)
    public int peakIndexInMountainArray_2(int[] arr) {
        int L = 0;
        int R = arr.length - 1;

        while (L < R) {
            int mid = L + (R - L) / 2;

            if (arr[mid] >= arr[mid + 1]) { // mid + 1 will not go beyond boundary
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        return L; // L==R
    }
}
