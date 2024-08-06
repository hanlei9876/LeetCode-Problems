package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// v1: recursion - no memorization
// notice this solution is different from backtracking
//   - backtracking will try every possible combination that is potentially a solution
//   - this solution will only form all combinations that are solutions, it won't form a combination that is not solution
public class LC_95_Unique_Binary_Search_Trees_II {
    public List<TreeNode> generateTrees(int n) {
        return allPossibleTrees(1, n);
    }

    private List<TreeNode> allPossibleTrees(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        // base case
        if(start > end) {
            res.add(null);
            return res;
        }

        // recursion relation
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubTree = allPossibleTrees(start, i - 1);
            List<TreeNode> rightSubTree = allPossibleTrees(i + 1, end);

            for (TreeNode leftTreeRoot : leftSubTree) {
                for (TreeNode rightTreeRoot : rightSubTree) {
                    TreeNode root = new TreeNode(i, leftTreeRoot, rightTreeRoot);
                    res.add(root);
                }
            }
        }

        return res;
    }
}

// v2: recursion - with memorization to optimize space
// complexity:
// Time complexity: O(C(n)), where C is Catalan number.
// Space complexity: O(C(n)), where C is Catalan number.
class LC_95_Unique_Binary_Search_Trees_II_v2 {

    Map<Pair<Integer, Integer>, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> generateTrees(int n) {
        memo = new HashMap<>();
        return allPossibleBST(1, n);
    }

    private List<TreeNode> allPossibleBST(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        // base case
        if (start > end) {
            res.add(null);
            return res;
        }

        // recursion relation
        Pair<Integer, Integer> pair = new Pair<>(start, end); // memorization - part 1
        if (memo.containsKey(pair)) {
            return memo.get(pair);
        }

        // Iterate through all values from start to end to construct left and right subtree recursively.
        for (int i = start; i <= end; ++i) {
            List<TreeNode> leftSubTrees = allPossibleBST(start, i - 1);
            List<TreeNode> rightSubTrees = allPossibleBST(i + 1, end);

            // Loop through all left and right subtrees and connect them to ith root.
            for (TreeNode left : leftSubTrees) {
                for (TreeNode right : rightSubTrees) {
                    TreeNode root = new TreeNode(i, left, right);
                    res.add(root);
                }
            }
        }

        memo.put(pair, res); // memorization - part 2

        return res;
    }

}
