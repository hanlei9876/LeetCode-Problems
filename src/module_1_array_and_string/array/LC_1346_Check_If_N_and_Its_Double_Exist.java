package module_1_array_and_string.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC_1346_Check_If_N_and_Its_Double_Exist {

    // solution-1: brute force
    // iterate each pair in array
    // time: worst case: O(N-1 + N-2 +...+ 1) = O((N^2 - N)/2) = O(N^2)
    // space: O(1)
    public boolean checkIfExist(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (2 * arr[i] == arr[j] || arr[i] == 2 * arr[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    // solution-2: use HashSet to reduce time complexity
    // for HashSet, add(), remove(), contains() cost constant time O(1)
    //
    // Set<Integer> set = new HashSet<Integer>();
    // System.out.println(set.contains(5)); // false
    //
    // time: O(N)
    // space: worst case: O(N)
    public static boolean checkIfExist_2(int[] arr) {
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i< arr.length; i++) {

            if (set.contains(arr[i]*2)) {
                return true;
            } else if ( (arr[i]%2 == 0) && set.contains(arr[i]/2) ) {
                return true;
            } else {
                set.add(arr[i]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>();
        System.out.println(set.contains(5)); // false

        // Note: 7/2 == 3, but 3*2 != 7
        int[] a = {3, 1, 7, 11};
        checkIfExist_2(a);
    }

    // solution-3: binary search - in sorted array, for each element, search in the array itself
    //  - sort array >> for each element, binary search for its double within array (double's index != element's index)
    //  - goal: search if element's double exists in array
    // edge case: [0, 0]
    // time: O(NlogN + NlogN) >> O(NlogN)
    // space: O(logN)
    public static boolean checkIfExist_3(int[] arr) {
        Arrays.sort(arr);

        // edge case: arr[i] == 0 >> 0*2 == 0. so need to return its index to distinguish
        for (int i = 0; i < arr.length - 1; i++) {
            int doub = arr[i] * 2;
            int j = binarySearch(arr, i, doub);

            if (j != -1 && j != i) {
                return true;
            }
        }

        return false;
    }

    private static int binarySearch(int[] arr, int i, int target) {
        int L = 0;
        int R = arr.length - 1;

        while (L <= R) {
            int mid = L + (R - L) / 2;

            if (mid != i && target == arr[mid]) {
                return mid;
            } else if (mid == i) {
                L = mid + 1;
            } else if (target < arr[mid]) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return -1;
    }
}
