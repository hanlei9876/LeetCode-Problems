package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC_145_Binary_Tree_Postorder_Traversal {
}

// Solution-1: recursion
// time: O(N)
// space: - O(h) = O(logN) in average case, h is tree height
//        - O(N) in worst case, where input is a skewed tree
//    where N is the total number of nodes in tree
class LC_145_Binary_Tree_Postorder_Traversal_recursion {

    List<Integer> res = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        helper(root);
        return res;
    }

    private void helper(TreeNode node) {
        // base case
        if (node == null)
            return;

        // recursion relation
        helper(node.left);
        helper(node.right);
        res.add(node.val);
    }
}


// Solution-2: iteration - stack
// time: O(N)
// space: O(N) in worst case, where input is a skewed tree
//    where N is the total number of nodes in tree
class LC_145_Binary_Tree_Postorder_Traversal_iteration {

    // solution-2.1 - using 2 stacks
    public List<Integer> postorderTraversal_v1(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        Stack<TreeNode> stack1 = new Stack<>(); // buffer stack - store nodes - push as child nodes, pop as parent node
        Stack<TreeNode> stack2 = new Stack<>(); // result stack - store final sequence
        stack1.push(root);

        // loop-1: this loop is going the same pattern as pre-order traversal using iteration (see my solution to it in LC-144)
        // time: O(N) - each node is visited exactly once
        // space:O(h)
        //   - average: O(logN)
        //   - best case: O(1) -  for skewed tree
        //   - worst case: O(2N) >> O(N) see the tree in my solution to it in LC-144
        while(!stack1.isEmpty()) {
            TreeNode curr = stack1.pop();

            // all child nodes are stored in stack1 buffer first,
            // then popped out as parent node
            if (curr.left != null) {
                stack1.push(curr.left);
            }
            if (curr.right != null) {
                stack1.push(curr.right);
            }

            // push parent node to stack2, as the result sequence
            stack2.push(curr);
        }

        // loop-2: move the result sequence from stack2 to the ArrayList for return
        // time: O(N)
        // spcae: O(N)
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }

        return res;
    }
}