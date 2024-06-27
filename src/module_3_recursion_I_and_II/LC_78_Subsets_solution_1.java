package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

// solution-1: backtracking - this is based on the solution to LC-79 word search
// time: O(N * 2^N)
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

// solution 2: Dynamic Programming (cascading) - see note
// give nums.length >= 1
//
// time: O(N * 2^N)
//   - Subsets: 2^N, since each element could be absent or present.
//   - for-loop-2 and for-loop-3 always have the same number loops
//   - the result of for-loop-1 + for-loop-2 will produce O(2^N) sublists, each sublist has N length at max
//
// space: O(N * 2^N)
class LC_78_Subsets_solution_2 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) { // for-loop-1
            // we need to use an intermediate list, otherwise, will have dead loop
            // see the wrong case below
            List<List<Integer>> intermediate = new ArrayList<>();

            for (int j = 0; j < res.size(); j++) { // for-loop-2
                List<Integer> newList = new ArrayList<>(res.get(j)); // copy to new list
                newList.add(nums[i]);
                intermediate.add(newList);
            }

            for (List<Integer> list : intermediate) { // for-loop-3
                res.add(list);
            }
        }

        return res;
    }

    List<List<Integer>> subsets_wrong_code(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < res.size(); j++) {
                List<Integer> newList = new ArrayList<>(res.get(j)); // copy to new list
                newList.add(nums[i]);
                res.add(newList); // cause endless loop
            } // this is an infinite loop
        }
        return res;
    }
}
