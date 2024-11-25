package module_4_binary_search;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// similar to LC-209
// this problem has array with POSITIVE + NEGATIVE integers
//
// Due to the fact that there are negative numbers in the array - sliding window's size is NOT monotonic with its sum
//   - sliding window in LC-209 is not applicable
//   - binary search + fixed sliding window in LC-209 is not applicable
// See LeetCode's official solution
//
// when seeing subarray's sum - always think about prefix sum (cumulative sum)
public class LC_862_Shortest_Subarray_with_Sum_at_Least_K {

    // solution 1: brute force (time limit exceeded)
    // enumerate each possible sub-array & calculate the sum (see solution 2 in LC-209)
    // time: O(N^2)
    // space: O(1)
    public int shortestSubarray_1(int[] nums, int k) {
        int minSubArraySize = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int currSum = 0;

            for (int j = i; j < nums.length; j++) {
                currSum = currSum + nums[j];

                if (currSum >= k) {
                    int currSize = j - i + 1;
                    minSubArraySize = (currSize < minSubArraySize) ? currSize : minSubArraySize;

                    break; // optimize time
                }
            }
        }

        return (minSubArraySize == Integer.MAX_VALUE) ? -1 : minSubArraySize;
    }
}