package module_1_array_and_string.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC_217_Contains_Duplicate {

    // solution 1: brute force
    // find each possible pair, and check if the same
    // time: O( N(N-1)/2 ) = O(N^2)
    // space: O(1)

    // solution 2: sort
    // time: O(NlogN + N) = O(NlogN)
    // space: O(1)
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i])
                return true;
        }

        return false;
    }

    // solution 3: use hash set
    // time: O(N)
    // space: O(N)
    public boolean containsDuplicate_2(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int e : nums) {
            if (!set.contains(e)) {
                set.add(e);
            } else {
                return true;
            }
        }

        return false;
    }
}
