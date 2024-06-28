package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_90_Subsets_II {
}

// solution-1: backtrack - sort array >> iterate each element
// time: O(N * 2^N)
//    - total amount of nodes in recursion tree
//    - deep copy of each element/node as result
//    - sort O(NlogN) is ignored
// space: O(N) = O(N + N + logN)
//    - stack max height
//    - max length of solution candidate
//    - sort: in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(logn)
class LC_90_Subsets_II_solution_1 {

    int[] nums;
    int N;
    List<List<Integer>> res;
    int k;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.nums = nums;
        this.N = nums.length;
        res = new ArrayList<>();

        Arrays.sort(this.nums);

        for (int i = 0; i <= N; i++) {
            k = i;
            backtrack(new ArrayList<>(), 0);
        }

        return res;
    }

    private void backtrack(List<Integer> solutionCandidate, int start) {
        // base case
        if (solutionCandidate.size() == k) {
            res.add(new ArrayList<>(solutionCandidate));
            return;
        }

        // recursion relation
        for (int i = start; i < N; i ++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            if (k - solutionCandidate.size() > N - i) { // optimize by pruning tree
                continue;
            }

            solutionCandidate.add(nums[i]);

            backtrack(solutionCandidate, i + 1);

            int len = solutionCandidate.size();
            solutionCandidate.remove(len - 1);
        }
    }
}

// solution-2: backtrack - use count table
// time: O(N * 2^N)
//    - total amount of nodes in recursion tree
//    - deep copy of each element/node as result
//    - O(2N) = creating count table
// space: O(N) = O(N + N + N + N)
//    - stack max height
//    - max length of solution candidate
//    - map size
//    - countTable
class LC_90_Subsets_II_solution_2 {

    int[] nums;
    int k;
    List<int[]> countTable;
    List<List<Integer>> res;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.nums = nums;
        res = new ArrayList<>();
        countTable = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                int count = map.get(nums[i]);
                map.put(nums[i], count + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] tuple = new int[2];
            tuple[0] = entry.getKey();
            tuple[1] = entry.getValue();
            countTable.add(tuple);
        }

        for (int i = 0; i <= nums.length; i++) {
            k = i;
            backtrack(new ArrayList<>(), 0);
        }

        return res;
    }

    private void backtrack(List<Integer> solutionCandidate, int start) {
        // base case
        if (solutionCandidate.size() == k) {
            res.add(new ArrayList<>(solutionCandidate));
            return;
        }

        // recursion relation
        for (int i = start; i < countTable.size(); i++) {
            int value = countTable.get(i)[0];
            int count = countTable.get(i)[1];

            if (count == 0) {
                continue;
            }

            solutionCandidate.add(value);
            countTable.get(i)[1] = count - 1;

            backtrack(solutionCandidate, i);

            int len = solutionCandidate.size();
            solutionCandidate.remove(len - 1);
            countTable.get(i)[1] = count;
        }
    }
}

// solution-3: DP - cascading
class LC_90_Subsets_II_solution_3 {

// time: O(N * 2^N) = O(NlogN + 2N * 2^N)
//    - sort O(NlogN)
//    - total amount of subsets: 2^N
//    - 2 times of deep copy for a subset: max O(2N)
// space: O(N) = O(N + logN)
//    - intermediate list: max O(N)
//    - sort O(logN)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        int lastIterationCount = 1;

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> intermediate = new ArrayList<>();

            int startIndex = (i > 0 && nums[i] == nums[i - 1]) ? (res.size() - lastIterationCount) : 0;

            for (int j = startIndex; j < res.size(); j++) {
                List<Integer> newList = new ArrayList<>(res.get(j)); // deep copy: max O(N)
                newList.add(nums[i]);
                intermediate.add(newList);
            }

            for (List<Integer> list : intermediate) { // deep copy: max O(N)
                res.add(list);
            }

            lastIterationCount = intermediate.size();
        }

        return res;
    }

    // time: O(N * 2^N) = O(NlogN + 2N * 2^N)
    //    - sort O(NlogN)
    //    - total amount of subsets: 2^N
    //    - 2 times of deep copy for a subset: max O(2N)
    // space: O(logN)
    //    - sort O(logN)
    public List<List<Integer>> subsetsWithDup_optimize(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        int lastIterationCount = 1;

        for (int i = 0; i < nums.length; i++) {

            int startIndex = (i > 0 && nums[i] == nums[i - 1]) ? (res.size() - lastIterationCount) : 0;

            int lastResSize = res.size();
            for (int j = startIndex; j < lastResSize; j++) {
                List<Integer> newList = new ArrayList<>(res.get(j)); // deep copy: max O(N)
                newList.add(nums[i]);
                res.add(newList);
            }

            lastIterationCount = res.size() - lastResSize;
        }

        return res;
    }
}