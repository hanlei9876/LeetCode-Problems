package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

public class LC_77_Combinations {

    int n;
    int k;
    List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        res = new ArrayList<>();
        List<Integer> candidateList = new ArrayList<>();

        backtrack(candidateList, 0);

        return res;
    }

    // time: O(k * Cnk + k * Cnk) = O(k * Cnk) - see my notes
    // space: O(2k) = O(k) - stack height = k, candidateList’s max length = k
    private void backtrack(List<Integer> candidateList, int start) {
        // base case
        if(candidateList != null && candidateList.size() == k) {
            res.add(new ArrayList<>(candidateList));
            return;
        }

        // recursion relation
        for (int i = start + 1; i <= n; i++) {
            candidateList.add(i);
            backtrack(candidateList, i);

            // remove last element of this array
            int len = candidateList.size();
            candidateList.remove(len - 1);
        }
    }

    // avoid go through the nodes that don't have enough remaining nodes to form a combination
    // see notes
    private void backtrack_optimize(List<Integer> candidateList, int start) {
        // base case
        if(candidateList != null && candidateList.size() == k) {
            res.add(new ArrayList<>(candidateList));
            return;
        }

        // recursion relation
        for (int i = start + 1; i <= n; i++) {
            /* for optimnization - start (see note) */
            int neededNum = k - candidateList.size();
            int availableNum = n - i + 1;
            if (neededNum > availableNum) {
                continue;
            }
            /* for optimnization - end */

            candidateList.add(i);
            backtrack(candidateList, i);

            // remove last element of this array
            int len = candidateList.size();
            candidateList.remove(len - 1);
        }
    }

}
