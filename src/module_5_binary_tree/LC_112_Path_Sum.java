package module_5_binary_tree;


public class LC_112_Path_Sum {}



// solution 1: DFS-preorder, top-down (determine answer when reaching leaf node)
// time: O(N)
// space: O(H)
//   - average case: O(logN)
//   - worst case: O(N) - left-skewed tree or right skewed tree
class LC_112_Path_Sum_v1 {

    int targetSum;
    boolean hasPath;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        this.hasPath = false;

        helper(root, 0);

        return hasPath;
    }

    private void helper(TreeNode node, int sum) {
        // base case
        if (node == null) {
            return;
        }

        // recursion relation
        sum += node.val;

        if (node.left == null && node.right == null && sum == targetSum) {
            hasPath = true;
        }

        helper(node.left, sum);
        helper(node.right, sum);
    }
}
