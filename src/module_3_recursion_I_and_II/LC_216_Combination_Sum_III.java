package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

// do LC-77 - Combinations before doing this problem
public class LC_216_Combination_Sum_III {

    int target;
    int k;
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        this.target = n;
        this.res = new ArrayList<>();

        backtrack(new ArrayList<>(), 0, 1);

        return res;
    }

    // time: O(k * C(k, N)) + O(k * C(k, N)) = O(k * C(k, N)), where N is length of candidates [1, 2,..., 9]
    //      - 1st O() is total amount of nodes within recursion tree
    //      - 2nd O() is upper limit of doing the copy of results from solution candidate list to res
    // space: O(k) + O(k) = O(k) - recursion stack height,
    private void backtrack(List<Integer> solutionCandidateList, int sum, int start) {
        // base case
        if (solutionCandidateList.size() == k) {
            if (sum == target) {
                res.add(new ArrayList<>(solutionCandidateList)); // copy elements to the res
            }
            return;
        }

        // recursion relation
        for (int i = start; i <= 9; i++) {
            solutionCandidateList.add(i);
            sum = sum + i;

            backtrack(solutionCandidateList, sum, i + 1);

            int len = solutionCandidateList.size();
            solutionCandidateList.remove(len - 1);
            sum = sum - i;
        }
    }

    // optimize - prerequisite of optimization is that the given candidate list must be sorted, such as [1 ~ 9] here
    private void backtrack_optimized(List<Integer> solutionCandidateList, int sum, int start) {
        // base case
        if (solutionCandidateList.size() == k) {
            if (sum == target) {
                res.add(new ArrayList<>(solutionCandidateList)); // copy elements to the res
            }
            return;
        }

        // recursion relation
        for (int i = start; i <= 9; i++) {
            /* for optimnization - start (see note) */
            int neededAmount = target - sum;
            int neededCount = k - solutionCandidateList.size();

            if (i > neededAmount || 9 - i + 1 < neededCount) {
                continue;
            }
            /* for optimnization - end */

            solutionCandidateList.add(i);
            sum = sum + i;

            backtrack(solutionCandidateList, sum, i + 1);

            int len = solutionCandidateList.size();
            solutionCandidateList.remove(len - 1);
            sum = sum - i;
        }
    }
}
