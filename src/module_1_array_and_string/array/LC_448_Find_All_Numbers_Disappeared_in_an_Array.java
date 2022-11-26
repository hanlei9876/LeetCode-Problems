package module_1_array_and_string.array;

import java.util.*;

public class LC_448_Find_All_Numbers_Disappeared_in_an_Array {

    // solution-1: use array as dictionary (intuitive approach)
    // time: O(2N) -> O(N)
    // space: O(N). (ArrayList is not counted)
    // Even when ArrayList is counted, in the worst case ArrayList's size == N-1. So, totally space is O(2N + N-1) = O(N)
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int[] dic = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            dic[num]++;
        }

        for (int i = 1; i < dic.length; i++) {
            if (dic[i] == 0)
                res.add(i);
        }

        return res;
    }

    // solution-2: use array as dictionary (intuitive approach)
    // time: O(N)
    // space: O(2N) -> O(N). (ArrayList is not counted)
    public List<Integer> findDisappearedNumbers_2(int[] nums) {
        List<Integer> res = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            }
            /*else {
                int value = map.get(nums[i]);
                value++;
                map.put(nums[i], value);
            }*/
        }

        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)) {
                res.add(i);
            }
        }

        return res;
    }

    // solution-3: (see official solution) in-place operation, mark element as negative to denote this number of this index is visited
    // time: O(2N) -> O(N)
    // space: O(1). (ArrayList is not counted)
    public List<Integer> findDisappearedNumbers_3(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]);
            int j = abs - 1; // treat absolute value of nums[i] as index

            if (nums[j] > 0) {
                nums[j] = nums[j] * (-1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
    }
}
