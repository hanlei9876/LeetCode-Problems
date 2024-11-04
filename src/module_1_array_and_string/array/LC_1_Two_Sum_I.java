package module_1_array_and_string.array;

import java.util.HashMap;
import java.util.Map;

// Only one valid answer exists.
public class LC_1_Two_Sum_I {

    // solution-1: brute force - enumerate all possible pairs
    // time: O(N^2)
    // space: O(1)
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] ==  target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }

        return res;
    }

    // solution-2: use hashmap - two pass (from leetcode official solution)
    // note how this approach handles duplicate values, say [2, 4, 3, 4, 7] target = 8
    // time: O(2N) >> O(N)
    // space: O(N)
    public int[] twoSum_2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int compliment = target - nums[i];

            if (map.containsKey(compliment) && map.get(compliment) != i) {
                res[0] = i;
                res[1] = map.get(compliment);
                break;
            }
        }

        return res;
    }

    // solution-3: use HashMap - one pass
    // time: O(N)
    // space: O(N)
    public int[] twoSum_3(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // if (map.isEmpty() || !map.containsKey(target - nums[i])) // Each lookup in the table costs only O(1) time.
            if (!map.containsKey(nums[i])) {
                map.put(target - nums[i], i);
            } else {
                res[0] = map.get(nums[i]);
                res[1] = i;
                break;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();

        System.out.println("is the map empty? - " + map.isEmpty()); // true
        System.out.println(map.containsKey(5)); // false (No exception thrown)
    }

}
