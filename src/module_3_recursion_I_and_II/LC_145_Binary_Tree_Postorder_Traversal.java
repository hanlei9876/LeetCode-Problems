package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.LinkedList;

public class LC_145_Binary_Tree_Postorder_Traversal {
}

// Solution-1: recursion
// time: O(N)
// space: O(h)
//     - O(logN) in average case
//     - O(N) in worst case, where input is a (left or right) skewed tree
//   where N is the total number of nodes in tree, h is tree height
class LC_145_Binary_Tree_Postorder_Traversal_recursion_v1 {

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


// Solution-2: iteration - stack + stack (learn this one, this one needs to be optimized, see solution 3)
// time: O(N) + O(N) >> O(N)
// space: O(N/2) + O(N) >> O(N)
//  - stack 1 holds O(N/2) nodes in worst case, see the worst case below:
//          1
//         / \
//        2   3
//           / \
//          4   5
//             / \
//            6   7
//  - stack 2 will hold all O(N) nodes of tree
// where N is the total number of nodes in tree
class LC_145_Binary_Tree_Postorder_Traversal_iteration_v2 {

    // solution-2.1 - using 2 stacks
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        Stack<TreeNode> stack1 = new Stack<>(); // buffer stack - store nodes - push as child nodes, pop as parent node
        Stack<TreeNode> stack2 = new Stack<>(); // result stack - store final sequence
        stack1.push(root);

        // loop-1: each node in tree will appear in stack 1 exactly once
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
        // space: O(N)
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }

        return res;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(null);

        System.out.println(stack.isEmpty()); // false
    }
}


// Solution-3: iteration - stack + two pointers (curr, prev) - recommended
// time: O(2N) ~ O(3N) >> O(N)
//   we need to calculate number of loops:
//     - each leaf node will be looped twice (1 for push to stack, 1 for pop & handle)
//     - each middle parent nodes will be visited 3 times
// space: O(h) - stack will always store all left nodes from root to leaf
//   - average O(logN)
//   - worst case: O(N) - left-skewed tree
class LC_145_Binary_Tree_Postorder_Traversal_iteration_v3 {

    public List<Integer> postorderTraversal_v1(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        // initialize
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null; // prev labels the node has been handled already

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                // push left nodes to stack for future access
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.peek(); // not pop()

                // always check curr's right child
                if (curr.right == null || curr.right == prev) {
                    // process current node
                    res.add(curr.val);
                    stack.pop();
                    prev = curr;
                    curr = null;
                } else {
                    curr = curr.right;
                }
            }
        }

        return res;
    }
}


// Solution-4: iteration - stack + (deque) doubly linked list - One way to implement a deque is a doubly-linked list, also known as a “head-tail linked list”
// directly return doubly linked list as result
// time: O(N)
// space: O(h)
//   - O(logN) - average
//   - O(N/2) >> O(N) - worst case is right skewed tree
//   - O(1) - best case is left skewed tree
class LC_145_Binary_Tree_Postorder_Traversal_iteration_v4 {

    public List<Integer> postorderTraversal(TreeNode root) {
        // create doubly linked list to hold result
        LinkedList<Integer> res = new LinkedList<>(); // doubly linked list

        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            res.addFirst(curr.val);

            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // doubly linked list
        LinkedList<Integer> list = new LinkedList<>();

        list.addFirst(1);
        list.addFirst(2);
        list.addLast(4);

        System.out.println(list); // [2, 1, 4]
    }
}