package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC_144_Binary_Tree_Preorder_Traversal {
}

// Solution-1: recursion
// time: O(N)
// space:
//        - O(h) = O(logN) in average case, h is tree height
//        - O(h) = O(N) in worst case, where input is a (left or right) skewed tree
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
// space:
//      - O(h) = O(logN) - the stack max size the height of tree in average case
//      - O(1) = left-skewed tree/right-skewed tree in best case, as the stack always store 1 node st most at any moment
//      - O(N/2) = O(N) - in worst case, the tree is as below
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

    // optimal solution (recommended) - never push null node to stack
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        // edge case
        if (root == null)
            return res;

        // initialize stack
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // focus on the stack only - handle one node per loop
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

    // not recommended
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


// solution 3: Morris traversal (primary pointer + runner pointer)
// time: O(2N) >> O(N), where N is number of nodes in tree
// space: O(1)
//
// To prove that the time complexity is O(n), the biggest problem lies in finding the time complexity of finding the predecessor nodes of all the nodes in the binary tree.
// Intuitively, the complexity is O(nlogn), because to find the predecessor node for a single node related to the height of the tree.
// But in fact, finding the predecessor nodes for all nodes only needs O(n) time.
// Because a binary Tree with n nodes has n−1 edges, the whole processing for each edges up to 2 times, one is to locate a node, and the other is to find the predecessor node.
// So the complexity is O(n)
// walk through the example in the note
class LC_144_Binary_Tree_Morris_Traversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        TreeNode curr = root; // initialize primary pointer

        while (curr != null) {
            if (curr.left == null) { // curr has NO left subtree
                res.add(curr.val);
                curr = curr.right;
            } else { // curr has left subtree
                // initialize predecessor (runner pointer)
                TreeNode pred = curr.left;

                // find the rightmost node in the left subtree, or the node that already points to curr
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                // validate predecessor
                if (pred.right == null) {
                    // no thread exist, meaning we have reached left-most node
                    res.add(curr.val);
                    pred.right = curr; // establish a temporary thread back to the current node
                    curr = curr.left;
                } else {
                    // the thread already exists, meaning we've already visited left subtree
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }

        return res;
    }
}