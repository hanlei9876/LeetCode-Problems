package module_3_recursion_I_and_II;

import java.util.Stack;

// constraints:
//  - The number of nodes in the tree is in the range [1, 10^4].
//  - Node.val is int
public class LC_98_Validate_Binary_Search_Tree {}


// solution-1: Divide & Conquer with valid range, Top-Down DFS - implement as recursion
// see note for the data structure and its key features used in this problem
// time: O(N) - we need to traverse each single node of the tree
// space: O(tree's height)
//             - O(logN) in average case
//             - O(N) in the worst case, where a tree only has left children or only has right children
class LC_98_Validate_Binary_Search_Tree_v1 {

    public boolean isValidBST(TreeNode root) {
        // here, we use "Integer == null" to denote positive infinity & negative infinity.
        // we can't achieve the denoting of infinity with int
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer low, Integer up) {
        // base case
        if (node == null) {
            return true; // null node must be a valid BST
        }

        // recurrence relation
        // 1 - meet criteria in this level
        if ((low != null && node.val <= low) || (up != null && node.val >= up)) {
            return false;
        }

        // 2 - levels below should also be met
        boolean left_Valid = validate(node.left, low, node.val);
        boolean right_valid = validate(node.right, node.val, up);

        return left_Valid && right_valid;
    }
}


// solution-2: Divide & Conquer with valid range, Top-Down DFS - implement as iteration
//
// time: O(N) - we need to traverse each single node of the tree
// space: O(tree's height) - see LC_144
//              - O(logN) in average case
//              - O(1) in the best case for left-skewed tree or right-skewed tree
//              - O(N/2) >> O(N) in the worst case, for a tree
class LC_98_Validate_Binary_Search_Tree_v2 {

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> lowerStack = new Stack<>();
        Stack<Integer> upperStack = new Stack<>();

        nodeStack.push(root);
        lowerStack.push(null);
        upperStack.push(null);

        while (!nodeStack.isEmpty()) { // we will never push a null node into the nodeStack
            TreeNode node = nodeStack.pop();
            Integer lower = lowerStack.pop();
            Integer upper = upperStack.pop();

            if (lower != null && node.val <= lower)
                return false;

            if (upper != null && node.val >= upper)
                return false;

            if (node.right != null) {
                nodeStack.push(node.right);
                lowerStack.push(node.val);
                upperStack.push(upper);
            }

            if (node.left != null) {
                nodeStack.push(node.left);
                lowerStack.push(lower);
                upperStack.push(node.val);
            }
        }

        return true;
    }
}



// solution-3: Inorder traverse tree >> if obtaining sorted list, then this tree is BST, Bottom-up Inorder DFS - implement as recursion
//
// time: O(N) - we need to traverse each single node of the tree
// space: O(tree's height) - see LC_144
//              - O(logN) in average case
//              - O(N) in the worst case for left-skewed tree or right-skewed tree
class LC_98_Validate_Binary_Search_Tree_v3 {

    Integer prev = null; // Integer to enable null value

    public boolean isValidBST(TreeNode root) {
        return inorder(root);
    }

    private boolean inorder(TreeNode node) {
        // base case
        if (node == null)
            return true;

        // recursion relation
        boolean isLeftBST = inorder(node.left);

        if (!isLeftBST)
            return false;

        if (prev != null && prev >= node.val)
            return false;

        prev = node.val;

        return inorder(node.right);
    }
}


// solution-4: Inorder traverse tree >> if obtaining sorted list, then this tree is BST, Bottom-up Inorder DFS - implement as iteration
//
// time: O(N) - we need to traverse each single node of the tree
// space: O(tree's height) - see LC_94
//              - O(logN) in average case
//              - O(N) in the worst case for left-skewed tree
//              - O(1) in the best case for right-skewed tree
class LC_98_Validate_Binary_Search_Tree_v4 {

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        Integer prev = null;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            // validating BST
            if (prev != null && prev >= curr.val) {
                return false;
            }
            prev = curr.val;

            curr =  curr.right;
        }

        return true;
    }
}