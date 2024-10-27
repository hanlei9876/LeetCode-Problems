package module_4_binary_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC_658_Find_K_Closest_Elements {

    // solution 1: sort using Collections.sort() API
    // time: O(N) + O(NlogN) + O(klogk)
    // space: O(N) + O(logN) + O(logk) + O(k)
    //
    // We considered every single number from arr as a potential candidate for the final output.
    // However, when arr.length is very large, and k is very small, this is low efficient
    public List<Integer> findClosestElements_v1(int[] arr, int k, int x) {
        // convert from array to list to make use of Collection.sort()
        List<Integer> list = new ArrayList<>();

        for (int num : arr) {
            list.add(num);
        }

        /*
         * What if Math.abs(num1 - x) - Math.abs(num2 - x) == 0?
         *   - Collections.sort() is to maintain their relative order from the original list (a.k.a. Stable Sorting).
         *
         * */
        Collections.sort(list, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));

        List<Integer> res = list.subList(0, k);

        Collections.sort(res);

        return res;
    }


    // solution 2: binary search to find the closest element (a template) + sliding window
    // time: O(logN + k + k)
    // space: O(k) for res
    public List<Integer> findClosestElements_v2(int[] arr, int k, int x) {
        // step 1: (template) find the element that is closest to x
        int L = 0;
        int R = arr.length - 1;

        while (L < R) {
            int mid = L + (R - L) / 2;

            if (arr[mid] == x) {
                R = mid;
            } else if (arr[mid] > x) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }
        // L == R is the target index

        /*

        The template produces results according to 2 scenarios:
        (1) the target value x exists in the array
        The template will always find the exact element. For example,
           [1, 3, 6, 6, 6, 10, 15]  x = 3    ==>    result index is L == R == 1
           [1, 3, 6, 6, 6, 10, 15]  x = 6    ==>    result index is L == R == 2

        (2) the target element does NOT exist in the array
        The template will always find the element right to the target value, which is NOT the closest element to x.
        For example,
           [1, 3, 6, 6, 6, 10, 15]  x = 7    ==>    result index is L == R == 5 (arr[5] is 10)
           [1, 3, 6, 6, 6, 10, 15]  x = 8    ==>    result index is L == R == 5 (arr[5] is 10)

        */

        // step 2: sliding window starting at L (R)
        /*

        The initial window MUST include the closest element to x.
        To achieve this, we cannot initialize low & hgh as below,
        as it might not include the closest element to x when target element is NOT in the array
           int low = L;
           int high = L + 1;

        */
        int high = L; // high is never out of bound
        int low = L - 1; // low is possible to be out of bound

        while (high - low - 1 < k) {
            if (low == -1) {
                high++;
                continue;
            } else if (high == arr.length) {
                low--;
                continue;
            }

            if (Math.abs(x - arr[low]) <= Math.abs(x - arr[high])) {
                low--;
            } else if (Math.abs(x - arr[low]) > Math.abs(x - arr[high])) {
                high++;
            }
        }

        // copy the elements to result
        List<Integer> res = new ArrayList<>();
        for (int i = low + 1; i < high; i++) {
            res.add(arr[i]);
        }

        return res;
    }


    // solution 3: one binary search to left bound of k space (template)
    // time: O(log(N - k) + k)
    // space: O(k) for res
    public List<Integer> findClosestElements_v3(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            // we need to consider arr[mid] & arr[mid + k] - ONLY one of them could possibly be in a final answer (left bound)
            int mid = left + (right - left) / 2;

            // here, we should NOT compare: Math.abs(x - arr[mid]) vs Math.abs(x - arr[mid + k])
            // Instead, we should consider which of arr[mid] & arr[mid + k] is closer to k
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // left == right is the left bound of k space
        List<Integer> res = new ArrayList<>();

        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }

        return res;
    }
}

class My_Experiments_on_Binary_Search {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            list.add(num);
        }

        System.out.println(list);


        Collections.sort(list, (num1, num2) -> Math.abs(num1 - 3) - Math.abs(num2 - 3));

        System.out.println(list);



        // new experiment
        int[] arr = new int[]{1, 3, 6, 6, 6, 10, 15};
        int x = 8;

        binarySearch_1(arr, x);

        System.out.println("xxxx");

        binarySearch_2(arr, x);

        System.out.println("xxxx");

        binarySearch_3(arr, x);

        System.out.println("xxxx");
        findClosestElements_fromleetcode(arr, 1, x);



        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        int[] arr1 = new int[]{1, 6, 6, 6, 8, 10, 15};
        binarySearch_1(arr1, 6);

        binarySearch_2(arr1, 6);
    }

    // this is the template to find the element that is closest to x
    static int binarySearch_1(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] == x) {
                right = mid;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println("from BS_1");
        System.out.println(left + "   " + right);
        System.out.println(arr[left]);

        return left;
    }

    static int binarySearch_2(int[] arr, int x) {
        System.out.println(1);
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == x) {
                System.out.println("mid is: " + mid);
                return mid; // Exact match found
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1; // Adjust right index to search the left half
            }
        }

        System.out.println("from BS_2");
        System.out.println(left + "   " + right);
        System.out.println(arr[left]);

        return left;
    }

    static int binarySearch_3(int[] arr, int x) {
        int left = 0;
        int right = arr.length;
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;

            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left + "   " + right);
        System.out.println(arr[left]);

        return left;
    }

    public static List<Integer> findClosestElements_fromleetcode(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();

        // Base case
        if (arr.length == k) {
            for (int i = 0; i < k; i++) {
                result.add(arr[i]);
            }

            return result;
        }

        // Binary search to find the closest element
        int left = 0;
        int right = arr.length;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Initialize our sliding window's bounds
        left -= 1;
        right = left + 1;

        // While the window size is less than k
        while (right - left - 1 < k) {
            // Be careful to not go out of bounds
            if (left == -1) {
                right += 1;
                continue;
            }

            // Expand the window towards the side with the closer number
            // Be careful to not go out of bounds with the pointers
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left -= 1;
            } else {
                right += 1;
            }
        }

        // Build and return the window
        for (int i = left + 1; i < right; i++) {
            System.out.println("i = " + i + ",  value=  " + arr[i]);
            result.add(arr[i]);
        }

        return result;
    }
}
