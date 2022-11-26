package module_1_array_and_string.array;

import java.util.LinkedList;
import java.util.Queue;

// reference to LC_487_Max_Consecutive_Ones_II
public class LC_1004_Max_Consecutive_Ones_III {
    // solution-1: brute force
    // find every single consecutive sequence and check if it is valid
    // time: O(n^2)
    // space: O(1)
    public int longestOnes_1(int[] nums, int k) {
        int max = 0;

        for (int L = 0; L < nums.length; L++) {
            int zeros = 0;

            for (int R = L; R < nums.length; R++) {
                if (nums[R] == 0)
                    zeros++;

                if (zeros <= k)
                    max = Math.max(max, R - L + 1);
            }
        }

        return max;
    }

    // solution-2: sliding window ( O(2N) )
    // use while-loop to contract the leftside of window
    // time: O(2N) -> O(N)
    // space: O(1)
    public int longestOnes_2(int[] nums, int k) {
        int max = 0;
        int zeros = 0; // the count of zeros within a window

        int L = 0;
        int R = 0;
        while (R < nums.length) {
            if (nums[R] == 0) {
                zeros++;
            }

            while (zeros > k) {
                if (nums[L] == 0) {
                    zeros--;
                }
                L++;
            }

            max = Math.max(max, R - L + 1);

            R++;
        }

        return max;
    }


    // solution-3a: sliding window (optimized)
    // we never shrink the window. the window will either expand or move to right step by step.
    // time: O(N)
    // space: O(1)
    public int longestOnes_3a(int[] nums, int k) {
        int max = 0;
        int zeros = 0; // recoding the count of 0s in the current window

        int L = 0;
        int R = 0;
        while (R < nums.length) {
            // form the window from L to R
            if (nums[R] == 0) {
                zeros++;
            }

            // now the window is available to validate
            if (zeros > k) { // if window is not valid, move the window to right
                if (nums[L] == 0) {
                    zeros--;
                }
                R++;
                L++;
            } else { // if window is valid
                max = Math.max(max, R - L + 1);
                R++;
            }
        }

        return max;
    }

    // solution-3b: sliding window (optimized)
    // simply the code of solution-3a
    public static int longestOnes_3b(int[] nums, int k) {
        int zeros = 0; // recoding the count of 0s in the current window

        int L = 0;
        int R = 0;
        while (R < nums.length) {
            // form the window from L to R
            if (nums[R] == 0) {
                zeros++;
            }

            // now the window is available to validate
            if (zeros > k) { // if window is not valid, move the window to right
                if (nums[L] == 0) {
                    zeros--;
                }
                L++;
            }

            R++;
        }

        // now, R is already out of boundary. So, not R - L - 1
        return R - L;
    }


    // solution-4: sliding window using queue
    // in case of stream as input, where left index is not able to access the element
    // time: O(2N) -> O(N)
    // space: O(k)
    public int longestOnes_4(int[] nums, int k) {
        int max = 0;

        Queue<Integer> queue = new LinkedList<>();

        int L = 0;
        int R = 0;
        while (R < nums.length) {
            if (nums[R] == 0)
                queue.offer(R);

            if (queue.size() > k) {
                L = queue.poll() + 1;
            }

            max = Math.max(max, R - L + 1);

            R++;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] a = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;

        longestOnes_3b(a, k);
    }
}
