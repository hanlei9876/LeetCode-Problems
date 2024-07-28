package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC_144_Binary_Tree_Preorder_Traversal {
}

// Solution-1: recursion
// time: O(N)
// space: - O(h) = O(logN) in average case, h is tree height
//        - O(N) in worst case, where input is a skewed tree
//    where N is the total number of nodes in tree
class LC_144_Binary_Tree_Preorder_Traversal_recursion {

    List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        helper(root);
        return res;
    }

    private void helper(TreeNode node) {
        // base case
        if (node == null)
            return;

        // recursion relation
        res.add(node.val);
        helper(node.left);
        helper(node.right);
    }
}


// Solution-2: iteration - stack
// time: O(N) - visit each node exactly once
// space: O(h) >> O(logN) - the stack max size the height of tree in average case
//      - O(1) - left-skewed tree/right-skewed tree in best case, as the stack always store 1 node st most at any moment
//      - O(N/2) >> O(N) - in worst case, the tree is as below
/**
 *                       1
 *                     /  \
 *                    2    3
 *                  /  \
 *                 4    5
 *               / \
 *              6   7
 *            / \
 *           8   9
 *
 * where N is the total number of nodes in tree
 * */

class LC_144_Binary_Tree_Preorder_Traversal_iteration {

    // optimal solution
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            res.add(curr.val);

            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }

        return res;
    }

    public List<Integer> preorderTraversal_v2_less_optimal_in_space(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            if (curr != null) {
                res.add(curr.val);
                stack.push(curr.right);
                stack.push(curr.left);
            }
        }

        return res;
    }
}