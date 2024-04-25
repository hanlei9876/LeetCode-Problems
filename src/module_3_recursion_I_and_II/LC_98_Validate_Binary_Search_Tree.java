package module_3_recursion_I_and_II;

// constraints:
//  - The number of nodes in the tree is in the range [1, 10^4].
//  - Node.val is int
public class LC_98_Validate_Binary_Search_Tree {

    // solution-1: Divide & Conquer with valida range - Top-down DFS
    // time: O(tree's height) >> O(N) in the worst case, where a tree only has left children or only has right children
    // space: O(tree's height) >> O(N) in the worst case, where a tree only has left children or only has right children
    public boolean isValidBST(TreeNode root) {
        // here, we use "Integer == null" to denote positive infinity & negative infinity
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer low, Integer up) {
        // base case
        if (node == null) {
            return true; // null node must be a valid BST
        }

        if ((low != null && node.val <= low) || (up != null && node.val >= up)) {
            return false;
        }

        // recurrence relation
        boolean left_Valid = validate(node.left, low, node.val);
        boolean right_valid = validate(node.right, node.val, up);

        return left_Valid && right_valid;
    }
}