package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// time: O(N * N!)
// number of solutions - N!:
//     - the total tree nodes < O(N*N!)
//     - for each solution, deep copy takes N. >> total copying time: O(N*N!)
// space: O(3N) - stack max height N, solution candidate list N, usedIndexSet N
public class LC_46_Permutations {

    int[] nums;
    List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        this.res = new ArrayList<>();

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

            solutionCandidate.add(nums[i]);
            usedIndexSet.add(i);

            backtrack(solutionCandidate, usedIndexSet);

            int len = solutionCandidate.size();
            solutionCandidate.remove(len - 1);
            usedIndexSet.remove(i);
        }
    }
}
