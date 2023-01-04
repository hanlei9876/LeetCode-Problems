package module_1_array_and_string.array;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// constraints:
// 1 <= nums.length <= 10^5
public class LC_219_Contains_Duplicate_II {

    // solution-1 (Time Limit Exceeded): brute force
    // find each pair
    // time: O(N^2)
    // space: O(1)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j <  nums.length; j++) {
                if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }

        return false;
    }

    // solution-2: hash map
    // time: O(N)
    // space: O(N)
    public boolean containsNearbyDuplicate_2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && Math.abs(i - map.get(nums[i])) <= k) {
                return true;
            } else {
                map.put(nums[i], i);
            }
        }

        return false;
    }

    // solution-3: hash set + fixed-sized window (length = k)
    // time: O(N)
    // space: O(min(N, k))
    public boolean containsNearbyDuplicate_3(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }

            if (set.size() > k) {
                set.remove(nums[i - k - 1]);
            }
        }

        return false;
    }

}
