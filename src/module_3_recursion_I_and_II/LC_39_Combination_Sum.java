package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

// backtrack
// complexity analysis: see the note
// Let N be the number of candidates, T be the target value, and MMM be the minimal value among the candidates.
// time: O( N^(T/M + 1) )
// space: O(T/M) + O(T/M) = O(T/M)
public class LC_39_Combination_Sum {

    int[] candidates;
    int target;
    List<List<Integer>> res;

    int currSum; // we need to maintain it globally side by side with solutionCandidate

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        res = new ArrayList<>();

        currSum = 0;

        backtrack(new ArrayList<>(), 0);

        return this.res;
    }

    private void backtrack(List<Integer> solutionCandidate, int start) {
        // base case
        if (currSum == target) {
            res.add(new ArrayList<>(solutionCandidate)); // copy elements to new list
            return;
        } else if (currSum > target) {
            return;
        } // from below: currSum < target

        // recursion relation
        for (int i = start; i < candidates.length; i++) {
            solutionCandidate.add(candidates[i]);
            currSum = currSum + candidates[i];

            // here, it is "i", instead of "i+1". So to start from current element itself in next step
            backtrack(solutionCandidate, i);

            int len = solutionCandidate.size();
            solutionCandidate.remove(len - 1);
            currSum = currSum - candidates[i];
        }
    }
}
