package module_3_recursion_I_and_II;

import java.util.*;

// the key to the solution is we should ensure NO Duplicates at each level of recursion tree
// so, similar to LC-40 (Combination Sum II), we could use either (sort >> skip duplicates in array) or (count table)
public class LC_47_Permutations_II {}

// solution 1: we use sort >> skip duplicates in array
// time: O(N * N! + NlogN + N * N!) = O(N * N!)
// space: O(N + N + N) = O(N)
class LC_47_Permutations_II_solution_1 {

    int[] nums;
    List<List<Integer>> res;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        res = new ArrayList<>();

        backtrack(new ArrayList<>(), new HashSet<>());

        return res;
    }

    private void backtrack(List<Integer> solutionCandidate, Set<Integer> usedIndexSet) {
        // base case
        if (solutionCandidate.size() == nums.length) {
            res.add(new ArrayList<>(solutionCandidate));
            return;
        }

        // recursion relation
        for (int i = 0; i < nums.length; i++) {
            if (usedIndexSet.contains(i)) {
                continue;
            }

            // key to the solution. see note
            if (i > 0 && nums[i] == nums[i - 1] && !usedIndexSet.contains(i - 1)) {
                continue;
            }

            solutionCandidate.add(nums[i]);
            usedIndexSet.add(i);

            backtrack(solutionCandidate, usedIndexSet);

            int len = solutionCandidate.size();
            solutionCandidate.remove(len - 1);
            usedIndexSet.remove(i);
        }
    }
}

// solution 2: count table
// different from LC-40 (Combination Sum II), we don't need to use Ordered Tuple Set.
// Because in each recursion function, loop always start from 0. So, no need to track i for next recursion call
// time & space are the same as solution 1
class LC_47_Permutations_II_solution_2 {

    Map<Integer, Integer> map;
    int[] nums;
    List<List<Integer>> res;

    public List<List<Integer>> permuteUnique(int[] nums) {
        map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        this.nums = nums;
        this.res = new ArrayList<>();

        backtrack(new ArrayList<>());

        return res;
    }

    private void backtrack(List<Integer> solutionCandidate) {
        // base case
        if (solutionCandidate.size() == nums.length) {
            res.add(new ArrayList<>(solutionCandidate));
        }

        // recursion relation
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (value == 0) {
                continue;
            }

            solutionCandidate.add(key);
            entry.setValue(value - 1);

            backtrack(solutionCandidate);

            int len = solutionCandidate.size();
            solutionCandidate.remove(len - 1);
            entry.setValue(value);
        }

    }
}