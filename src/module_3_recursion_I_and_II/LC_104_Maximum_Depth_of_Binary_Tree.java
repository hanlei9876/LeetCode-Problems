package module_3_recursion_I_and_II;

import java.util.Stack;

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


// solution 2: DFS - Pre-Order - recursion
// time: O(N) - N is the total number of nodes. must visit all the nodes
// space:
//      worst case O(N) - totally unbalanced tree: left-skewed tree, where each node only has left child node
//      best case O(logN) - fully balanced tree. determined by the tree height
class LC_104_Maximum_Depth_of_Binary_Tree_v2_DFS_pre_order_recursion {

    int maxDepth = 0;

    public int maxDepth(TreeNode root) {
        helper(root, 0);
        return maxDepth;
    }

    private void helper(TreeNode node, int count) {
        // base case
        if (node == null) {
            maxDepth = (maxDepth < count) ? count : maxDepth;
            return;
        }

        // recursion relation
        count++;

        helper(node.left, count);
        helper(node.right, count);
    }
}


// solution 3: DFS - Pre-Order - iteration using 2 stacks
// time: O(N) - N is the total number of nodes. must visit all the nodes
// space:
//      worst case O(N/2) >> O(N) - totally unbalanced tree: left-skewed tree, where each node only has left child node
//      average case O(logN) - fully balanced tree. determined by the tree height
//      best case O(1) - right-skewed tree
class LC_104_Maximum_Depth_of_Binary_Tree_v2_DFS_pre_order_iteration {

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.push(root);
        depthStack.push(1);

        int maxDepth = 0;

        while (!nodeStack.isEmpty()) {
            TreeNode currNode = nodeStack.pop();
            int currDepth = depthStack.pop();

            maxDepth = Math.max(currDepth, maxDepth);


            if (currNode.right != null) {
                nodeStack.push(currNode.right);
                depthStack.push(currDepth + 1);
            }

            if (currNode.left != null) {
                nodeStack.push(currNode.left);
                depthStack.push(currDepth + 1);
            }
        }

        return maxDepth;
    }

    // test a++, ++a
    public static void main(String[] args) {
        int a = 1;

        int b = a++;
        System.out.println("a is: " + a); // 2
        System.out.println("b is: " + b); // 1

        int c = ++a;
        System.out.println("a is: " + a); // 3
        System.out.println("c is: " + c); // 3

        int d = 5;
        int e = d + 1;
        System.out.println("d is: " + d); // 5
        System.out.println("e is: " + e); // 6
    }
}

