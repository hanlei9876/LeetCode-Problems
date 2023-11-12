package module_3_recursion_I_and_II;

public class LC_700_Search_in_a_Binary_Search_Tree {

    // solution-1: iteration
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode p = root;
        while (val != p.val) {
            if (val < p.val) {
                if (p.left != null) {
                    p = p.left;
                } else {
                    return null;
                }
            } else {
                if (p.right != null) {
                    p = p.right;
                } else {
                    return null;
                }
            }
        }

        return p;
    }

    // solution-2: recursion
    // for the complexity, given the BST having N nodes,
    // time: O(logN) in the average case (balanced BST), O(N) in the worst case
    // space: O(logN)  in the average case (balanced BST), O(N) in the worst case
    public TreeNode searchBST_2(TreeNode root, int val) {
        // base case: found the node or there is no node to search
        if (root == null || root.val == val) {
            return root;
        }

        // recurrence relations
        TreeNode node;
        if (val < root.val) {
            node = searchBST_2(root.left, val);
        } else {
            node = searchBST_2(root.right, val);
        }

        return node;
    }
}




class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }
