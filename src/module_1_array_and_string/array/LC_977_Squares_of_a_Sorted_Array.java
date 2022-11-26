package module_1_array_and_string.array;

import java.util.Arrays;

// because we are asked to return an array, so it makes sense to create a new array as result,
// instead of modify the original input array.
// if we should modify the original array, there is no need to return anything for the method.
public class LC_977_Squares_of_a_Sorted_Array {

    // Solution-1: square each element. then sort the entire squared array
    // time: O(NlogN) + O(N) = O(NlogN + N) = O(NlogN)
    // space: O(logN)
    public static int[] sortedSquares(int[] nums) {
        // step-1: square each element of the input array
        // time: O(N)
        // space: O(1) if I don't take the output array into account
        int[] results = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            results[i] = nums[i] * nums[i];
        }

        // step-2: sort the new squared array
        // time: O(nlog(N))
        // Space: O(logN)
        Arrays.sort(results); //  a variant of quicksort algorithm

        return results;
    }

    // Solution-2: two pointer technique to speed up the time complexity
    // Key: the largest number in result array always come from either the left-most or the right-most number from input array
    // time: O(N)
    // space: 1 if result array is not taken into account
    public static int[] sortedSquares_2(int[] nums) {
        int[] results = new int[nums.length];

        int L = 0;
        int R = nums.length - 1;
        int I = nums.length - 1; // initial index of the resulting array

        while (L != R) { // while (L < R){}
            if (Math.abs(nums[L]) < Math.abs(nums[R])) {
                results[I] = nums[R] * nums[R];
                R--;
                I--;
            } else {
                results[I] = nums[L] * nums[L];
                L++;
                I--;
            }
        }
        results[I] = nums[L] * nums[L]; // when L == R

        return results;
    }


    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        int[] results = sortedSquares(nums);

        for (int ele : results)
            System.out.println(ele);

        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                index = i;
                break;
            }
        }
        System.out.println(index);

        System.out.println(Math.abs(index));
    }
}
