package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_47_Permutations_II {

    int[] nums;
    List<List<Integer>> res;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = nums;
        res = new ArrayList<>();

        backtrack(new ArrayList<>(), new HashSet<>());

        return res;
    }

    private void backtrack(List<Integer> solutionCandidate, Set<Integer> usedIndexSet) {
        // base case

        // recursion relation
        for (int i = 0; i < nums.length; i++) {
            if (usedIndexSet.contains(i)) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !usedIndexSet.contains(nums[i - 1])) {
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
