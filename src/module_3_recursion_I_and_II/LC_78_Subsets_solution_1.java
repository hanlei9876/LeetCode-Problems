package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

// backtracking - this is based on the solution to LC-79 word search
// time: O(N * 2N)
//   - Subsets: 2^N, since each element could be absent or present.
//   - for each subset, at maximum, it will take N nodes (of recursion tree) to build, and will take N time to copy to results
// space: O(N) = O(N + N) - max height of recursion tree + max length of solutionCandidate
public class LC_78_Subsets_solution_1 {

    int[] nums;
    int k;
    int n;
    List<List<Integer>> res;

    // implement C(n, k) first
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        n = nums.length;
        res = new ArrayList<>();

        for (k = 0; k <= n; k++) {
            backtrack(new ArrayList<>(), 0);
        }

        return res;
    }

    private void backtrack(List<Integer> solutionCandidate, int start) {
        // base case
        if (solutionCandidate.size() == k) { // here solutionCandidate != null
            res.add(new ArrayList<>(solutionCandidate));
            return;
        }

        // recursion relation
        for (int i = start; i < n; i++) {
            // optimize - prune recursion tree
            int required = k - solutionCandidate.size();
            int remaining = n - i;
            if (required > remaining) {
                break;
            }

            solutionCandidate.add(nums[i]);

            backtrack(solutionCandidate, i + 1);

            solutionCandidate.remove(solutionCandidate.size() - 1);
        }
    }
}
