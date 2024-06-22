package module_3_recursion_I_and_II;

import java.util.*;

// N is the length of input array
// time: O(2^N)
// space: O(N) + O(N) = O(N)
// see note
public class LC_40_Combination_Sum_II_solution_1_sort_array {

    int[] sortedCandidates;

    List<List<Integer>> res;

    int target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.sortedCandidates = candidates;
        this.res = new ArrayList<>();
        this.target = target;

        backtrack_v1(new ArrayList<>(), 0, 0);

        return res;
    }

    // version 1: set early stop (trimming) inside the for-loop
    // recommended, as it's faster when executing in LeetCode
    private void backtrack_v1(List<Integer> solutionCandidate, int currSum, int start) {
        // base case - find a solution, then return
        if (currSum == target) {
            res.add(new ArrayList<Integer>(solutionCandidate));
            return;
        }

        // recursion relation
        for (int i = start; i < sortedCandidates.length; i++) {
            // skip duplicate elements
            if(start < i && sortedCandidates[i - 1] == sortedCandidates[i]) {
                continue;
            }

            // since the array is sorted, if potential sum > target, then stop further exploration
            if (currSum + sortedCandidates[i] > target) {
                break;
            }

            // add element to solution candidate
            solutionCandidate.add(sortedCandidates[i]);
            currSum += sortedCandidates[i];

            backtrack_v1(solutionCandidate, currSum, i + 1);

            int length = solutionCandidate.size();
            solutionCandidate.remove(length - 1);
            currSum -= sortedCandidates[i];
        }
    }

    // version 2: set early stop (trimming) in base case
    // slower than v1, as this will create one extra backtrack() function to run base case
    private void backtrack_v2(List<Integer> solutionCandidate, int currSum, int start) {
        // base case - find a solution, then return
        if (currSum == target) {
            res.add(new ArrayList<Integer>(solutionCandidate));
            return;
        }

        // recursion relation
        for (int i = start; i < sortedCandidates.length; i++) {
            // skip duplicate elements
            if(start < i && sortedCandidates[i - 1] == sortedCandidates[i]) {
                continue;
            }

            // since the array is sorted, if potential sum > target, then stop further exploration
            if (currSum + sortedCandidates[i] > target) {
                break;
            }

            // add element to solution candidate
            solutionCandidate.add(sortedCandidates[i]);
            currSum += sortedCandidates[i];

            backtrack_v2(solutionCandidate, currSum, i + 1);

            int length = solutionCandidate.size();
            solutionCandidate.remove(length - 1);
            currSum -= sortedCandidates[i];
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
        // given an example array
        int[] candidates = {1, 2, 2, 3, 3, 3, 4};

        // Step 1: Count occurrences of each candidate using hashmap
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < candidates.length; i++) {
            if (!map.containsKey(candidates[i])) {
                map.put(candidates[i], 1);
            } else {
                int originalFrequency = map.get(candidates[i]);
                map.put(candidates[i], originalFrequency + 1);
            }
        }

        // Step 2: convert the hashmap to an ordered set of tuples (not sorted, but in sequence)
        List<int[]> orderedTupleSet = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] tuple = new int[2];
            tuple[0] = entry.getKey();
            tuple[1] = entry.getValue();

            orderedTupleSet.add(tuple);
        }

        // print the list of tuples
        for (int[] tuple : orderedTupleSet) {
            System.out.println("Number: " + tuple[0] + ", Frequency: " + tuple[1]);
        }
    }
}


class LC_40_Combination_Sum_II_solution_2_orderedTupleSet {

    List<int[]> orderedTupleSet;
    List<List<Integer>> res;
    int target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        /* convert array to orderedTupleSet - start */
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < candidates.length; i++) {
            if (!map.containsKey(candidates[i])) {
                map.put(candidates[i], 1);
            } else {
                map.put(candidates[i], map.get(candidates[i]) + 1);
            }
        }

        this.orderedTupleSet = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] tuple = new int[2];
            tuple[0] = entry.getKey();
            tuple[1] = entry.getValue();

            this.orderedTupleSet.add(tuple);
        }
        /* convert array to orderedTupleSet - end */

        this.res = new ArrayList<>();
        this.target = target;

        backtrack(new ArrayList<>(), 0, 0);

        return this.res;
    }

    private void backtrack(List<Integer> candidateList, int currSum, int start) {
        // base case
        if (currSum == target) {
            res.add(new ArrayList<>(candidateList));
            return;
        } else if (currSum > target) {
            return;
        }

        // recursion relation
        for (int i = start; i < orderedTupleSet.size(); i++) {
            int value = orderedTupleSet.get(i)[0];
            int frequency = orderedTupleSet.get(i)[1];

            if (frequency <= 0) {
                continue;
            }

            candidateList.add(value);
            currSum += value;
            int[] updatedTuple = new int[]{value, frequency - 1};
            orderedTupleSet.set(i, updatedTuple);

            backtrack(candidateList, currSum, i);

            int length = candidateList.size();
            candidateList.remove(length - 1);
            currSum -= value;
            int[] reversedTuple = new int[] {value, frequency};
            orderedTupleSet.set(i, reversedTuple);
        }
    }
}

