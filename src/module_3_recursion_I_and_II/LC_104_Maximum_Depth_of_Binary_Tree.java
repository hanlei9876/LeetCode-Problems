package module_3_recursion_I_and_II;

public class LC_104_Maximum_Depth_of_Binary_Tree {

    // solution-1: maximum recursion (no optimization)
    // time: O(N) - N is the total number of nodes. must visit all the nodes
    // space:
    //      worst case O(N) - totally unbalanced tree. each node only has left child node
    //      best case O(logN) - fully balanced tree. determined by the tree height
    public int maxDepth(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }

        // recurrence relation
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        int maxDepth = Math.max(leftDepth, rightDepth);

        return maxDepth + 1;
    }

}
