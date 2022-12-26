package module_1_array_and_string.array;

import java.util.HashMap;
import java.util.Map;

// prefix sum/cumulative sum
public class LC_560_Subarray_Sum_Equals_K {

    // solution 1: brute force
    // find each possible sub-array, calculate sum
    // enable sum to be cumulative during j's iteration
    // time: O(N^2)
    // space: O(1)
    public int subarraySum(int[] nums, int k) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0; // enable sum to be cumulative during j's iteration
            for (int j = i; j < nums.length; j ++) {
                sum = sum + nums[j];

                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    // solution 2: use hash map to hold all cumulative sum
    // time: O(N)
    // space: O(N)
    public int subarraySum_2(int[] nums, int k) {
        int count = 0;
        int sum = 0;

        Map<Integer, Integer> sumOccurMap = new HashMap<>();
        sumOccurMap.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];

            if (sumOccurMap.containsKey(sum - k)) {
                count = count + sumOccurMap.get(sum - k);
            }

            // store the sum in the map
            if (sumOccurMap.containsKey(sum)) {
                int value = sumOccurMap.get(sum);
                sumOccurMap.put(sum, value + 1);
            } else {
                sumOccurMap.put(sum, 1);
            }
        }

        return count;
    }


    public static void main(String[] args) {
        // create a cumulative sum array
        // nums =    [1, 2, 3, 4]
        //  sum = [0, 1, 3, 6, 10]
        int[] nums =  new int[]{1, 2, 3, 4};
        int[] sum = new int[nums.length + 1];

        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1]+ nums[i-1];
        }
    }



}
