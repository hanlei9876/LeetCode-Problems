package module_1_array_and_string.array;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

public class LC_487_Max_Consecutive_Ones_II {

    // solution-1: brute force
    // find every single consecutive sequence and check if it is valid
    // time: O(n^2)
    // space: O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int longestOnes = 0;

        for (int L = 0; L < nums.length; L++) {
            int numZeros = 0;

            for (int R = L; R < nums.length; R++) {

                if (nums[R] == 0)
                    numZeros++;

                if (numZeros <= 1)
                    longestOnes = Math.max(longestOnes, (R - L + 1));
            }
        }

        return longestOnes;
    }

    // solution-2: sliding window
    // optimize time based on the brute force solution
    // time: O(2N) -> O(N) - in the worst case, such as all zeros [0, 0, 0],
    //          we might end up visiting every element of array twice, once by left pointer and once by right pointer.
    // space: O(1)
    // edge case: at least we have one element
    public int findMaxConsecutiveOnes_2(int[] nums) {
        int longestOnes = 0;
        int numZeros = 0;

        int L = 0;
        int R = 0;
        while (R < nums.length) {
            // step-1: add the new element pointed by R into the sliding window. That is: check if it is 0
            if (nums[R] == 0)
                numZeros++;

            // now, our sliding window has elements inside, available ot check
            // step-2: check if the sliding window is valid.
            //      If not, move left until window is valid
            while (numZeros == 2) {
                if (nums[L] == 0) {
                    numZeros--;
                }
                L++;
            }

            // now, the window is valid (it has at most one 0)
            // step-3: update the resulting longestOnes for this valid window
            longestOnes = Math.max(longestOnes, R - L + 1);

            // step-4: move R to create next window
            R++;
        }

        return longestOnes;
    }

    // solution-3: solution to the follow-up question (when the input is a stream)
    // A stream means the number of elements is infinite & we could use only one index i to access the element in each iteration: nums[i]
    // using queue to implement sliding window
    // time: O(N)
    // space: O(k)
    public int findMaxConsecutiveOnes_3(int[] nums) {
        final int k = 1; // here we can flip 1 zero
        int max = 0;

        Queue<Integer> zeroIdx = new LinkedList<>(); // for this ques the max length of queue would be 2

        int L = 0, R = 0;
        while(R < nums.length) { // read until you reach end of stream (let's assume the stream is an array)
            // step-1: add the new element pointed by R into the sliding window. That is: check if it is 0
            if(nums[R] == 0) {
                zeroIdx.offer(R); // capture the index of zero
            }

            // step-2: check if the sliding window is valid.
            //      If not, move left until window is valid
            if(zeroIdx.size() > k) { // as soon as we find zeros more than 'k' (in this case k = 1)
                L = zeroIdx.poll() + 1; // avoid to get L using nums[L]
            }

            // step-3: update the resulting longestOnes for this valid window
            max = Math.max(max, R - L + 1); // keep on searching max length at each time you see a new idx

            // step-4: move R to create next window
            R++;
        }

        return max;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // solution-4: another solution to the follow-up question (when the input is a stream) - (not recommended)
    // when k <= 1, using 3 int variables to implement sliding window
    public int findMaxConsecutiveOnes_4(int[] nums) {
        final int k = 1;

        int max = 0;

        // use the three variables to simulate a queue
        int zeroCount = 0;
        int prevZeroIdx = -1;
        int currZeroInx = -1;

        int L = 0, R = 0;
        while (R < nums.length) {
            if (nums[R] == 0) {
                prevZeroIdx = currZeroInx;
                currZeroInx = R;
                zeroCount++;
            }

            if (zeroCount > k) {
                L = prevZeroIdx + 1;
                prevZeroIdx = currZeroInx;
                zeroCount--;
            }

            max =  Math.max(max, R - L  + 1);

            R++;
        }

        return max;
    }

    // solution-5: simplify solution-4 (recommended)
    // when k <= 1, using an int variable to implement sliding window
    // we could also use an int variable to hold the index of 0.
    public int findMaxConsecutiveOnes_5(int[] nums) {
        final int k = 1;

        int max = 0;

        int queue = -1;

        int L = 0, R = 0;
        while (R < nums.length) {
            if (nums[R] == 0) {
                L = queue + 1;
                queue = R;
            }

            max = Math.max(max, R - L + 1);

            R++;
        }

        return max;
    }

    public static void main(String[] args) {
        // BigInteger a = new BigInteger();
    }
}
