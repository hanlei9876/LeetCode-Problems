package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_40_Combination_Sum_II {

    int[] candidates;
    int target;
    List<List<Integer>> res;

    int solutionCandidateSum;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        this.res = new ArrayList<>();
        this.solutionCandidateSum = 0;

        backtrack(new ArrayList<>(), 0);

        return res;
    }

    private void backtrack(List<Integer> solutionCandidateList, int start) {
        // base case
        if (solutionCandidateSum == target) {
            res.add(new ArrayList<>(solutionCandidateList));
            return;
        } else if (solutionCandidateSum > target) {
            return;
        }

        // recursion relation
        for (int i = start; i < candidates.length; i++) {
            solutionCandidateList.add(candidates[i]);
            solutionCandidateSum = solutionCandidateSum + candidates[i];

            backtrack(solutionCandidateList, i + 1);

            int len = solutionCandidateList.size();
            solutionCandidateList.remove(len - 1);
            solutionCandidateSum = solutionCandidateSum - candidates[i];
        }
    }

    /**
     * Key data structure to implement in Java:
     *   - Tuple (key : value)
     *   - Ordered set of tuples (v.s. HashMap (unordered set of tuples))
     *
     * Below is an example
     * */
    public static void main(String[] args) {

    }

    public static void exampleMethod() {
        // Example candidates array
        int[] candidates = {1, 2, 2, 3, 3, 3, 4};

        // Step 1: Count occurrences of each candidate
        Map<Integer, Integer> counter = new HashMap<>();
        for (int candidate : candidates) {
            if (counter.containsKey(candidate)) {
                counter.put(candidate, counter.get(candidate) + 1);
            } else {
                counter.put(candidate, 1);
            }
        }

        // Step 2: Convert the counter table to a list of (num, count) tuples
        List<int[]> counterList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            counterList.add(new int[] { key, value });
        }

        // print the counterList
        for (int[] tuple : counterList) {
            System.out.println("Number: " + tuple[0] + ", Count: " + tuple[1]);
        }
    }
}
