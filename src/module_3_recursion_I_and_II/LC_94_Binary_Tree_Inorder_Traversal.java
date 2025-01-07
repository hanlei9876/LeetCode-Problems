package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC_94_Binary_Tree_Inorder_Traversal {
}

// Solution-1: recursion
// time: O(N)
// space: - O(h) = O(logN) in average case, h is tree height
//        - O(N) in worst case, where input is a skewed tree
//    where N is the total number of nodes in tree
class LC_94_Binary_Tree_Inorder_Traversal_recursion {

    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        helper(root);
        return res;
    }

    private void helper(TreeNode node) {
        // base case
        if (node == null)
            return;

        // recursion relation
        helper(node.left);
        res.add(node.val);
        helper(node.right);
    }
}

// Solution-2: iteration using stack + pointer (curr)
// When we do the DFS, we need to know where to backtrack to (that's why we normally keep a stack).
// time: O(N) - visit each node exactly once
// space: - O(h) = O(logN) in average case, h is tree height
//        - O(N) in worst case, where input is a left-skewed tree
//        - O(1) in the best case for right-skewed tree
//    where N is the total number of nodes in tree
class LC_94_Binary_Tree_Inorder_Traversal_iteration {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res =  new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }

        return res;
    }
}


// solution-3: Morris traversal (see explanation in LC-144)
// time: O(2N) >> O(N), where N is number of nodes in tree (see explanation in LC-144)
// space: O(1)
class LC_94_Binary_Tree_Morris_Traversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) { // curr has No left subtree
                res.add(curr.val);
                curr = curr.right;
            } else { // curr has left subtree
                TreeNode pred = curr.left;

                // find the rightmost node in the left subtree, or the node that already points to curr
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    // no thread exist, meaning we have reached left-most node
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    res.add(curr.val);
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }

        return res;
    }
}

