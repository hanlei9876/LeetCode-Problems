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

// Solution-2: iteration using stack
// When we do the DFS, we need to know where to backtrack to (that's why we normally keep a stack).
// time: O(N) - visit each node exactly once
// space: - O(h) = O(logN) in average case, h is tree height
//        - O(N) in worst case, where input is a skewed tree
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

