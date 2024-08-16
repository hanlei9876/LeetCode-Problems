package module_3_recursion_I_and_II;

import java.util.Stack;

// constraints:
//   - 1 <= heights.length <= 10^5
//   - 0 <= heights[i] <= 10^4
public class LC_84_Largest_Rectangle_in_Histogram {}


// solution 1: brute force (time limit exceeded)
// - enumerate every possible pair, always maintain the minimum value during each outer iteration
// time: O(N^2) << N + (N-1) + (n-2) +...+ 1
// space: O(1) - no space used
class LC_84_Largest_Rectangle_in_Histogram_v1 {

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];

            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                int currArea = (j - i + 1) * minHeight;
                maxArea = Math.max(maxArea, currArea);
            }
        }

        return maxArea;
    }
}

// solution 2: divide & conquer (time limit exceeded)
// time: O(N^2) = O(total number of nodes in tree) * O(time spent on each node)
//     - O(time spent on each node) = O(N) in worst case
//     - O(total number of nodes in tree) is always N. Each node is a function execution, in which one element is identified as minimum
//
// If the numbers in the array are sorted, we don't gain the advantage of divide and conquer.
//
// space: O(tree_height) = O(logN) ~ O(N)
//
//
// average case: balanced binary tree
//     - O(tree_height) = O(logN)
//
// worst case: skewed binary tree when array is actually sorted
//     - O(tree_height) = O(N)
//
// where N is the length of input array
class LC_84_Largest_Rectangle_in_Histogram_v2 {

    public int largestRectangleArea(int[] heights) {
        int maxArea = helper(heights, 0, heights.length - 1);
        return maxArea;
    }

    private int helper(int[] array, int start, int end) {
        // base case
        if (start > end)
            return 0;
        if (start == end)
            return array[start];

        // recursion relation
        // iterate through the array from start to end, to find the minimum
        int minHeight = array[start];
        int minIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (minHeight > array[i]) {
                minHeight = array[i];
                minIndex = i;
            }
        }

        int currMaxArea = minHeight * (end - start + 1);

        int leftArea = helper(array, start, minIndex -1);
        int rightArea = helper(array, minIndex + 1, end);

        if (leftArea > rightArea) {
            return Math.max(currMaxArea, leftArea);
        } else {
            return Math.max(currMaxArea, rightArea);
        }
    }
}


// solution 3: monotonic stack (单调栈) - see note for explanation
// a data structure that maintains elements in either a monotonically increasing or decreasing order.
// time: O(2N) >> O(N)
//   - think from each element's perspective, each element will be pushed to stack exactly one, and will be popped from stack exactly once
// space: O(N) - stack's max height
class LC_84_Largest_Rectangle_in_Histogram_v3 {

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < len; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                int currHeight = heights[stack.pop()];
                int currWidth = i - 1 - stack.peek();
                maxArea = Math.max(maxArea, currHeight * currWidth);
            }

            stack.push(i);
        }

        while (stack.peek() != -1) {
            int currHeight = heights[stack.pop()];
            int currWidth = len - 1 - stack.peek();
            maxArea = Math.max(maxArea, currHeight * currWidth);
        }

        return maxArea;
    }
}

