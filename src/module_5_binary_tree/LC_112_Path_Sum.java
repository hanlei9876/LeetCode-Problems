package module_5_binary_tree;


import java.util.Stack;

public class LC_112_Path_Sum {

    // What is a valid root-to-leaf path? - A leaf node is a node without left or right children.
    //
    // based on the definition, there are a few edge cases:
    // 1 - the tree below should return FALSE
    //          1             target = 1
    //        /  \
    //       2    x
    //
    // 2 - given tree with 1 node (root = 1),  target = 1 >> return true
    //
    // 3 - given root = null, targetSum = 0 >> return false
    //
    // In summary, a valid path root -> leaf must meet:
    //   - end up with a leaf node (this leaf node can root)
    //   - path sum == target sum
}


// solution 1: DFS, pre-order, top-down (determine answer when reaching leaf node)
// time: O(N)
// space: O(H)
//   - average case: O(logN)
//   - worst case: O(N) - left-skewed tree or right skewed tree
class LC_112_Path_Sum_v1 {

    int targetSum;
    boolean hasPath;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        this.hasPath = false;

        helper(root, 0);

        return hasPath;
    }

    private void helper(TreeNode node, int sum) {
        // base case
        if (node == null) {
            return;
        }

        // recursion relation
        sum += node.val;

        // explicitly validate if current node is a leaf node
        if (node.left == null && node.right == null && sum == targetSum) {
            hasPath = true;
        }

        helper(node.left, sum);
        helper(node.right, sum);
    }
}


// solution 2: DFS, post-order, bottom-up (determine answer based on the answers from child nodes)
// time: O(N)
// space: O(H)
//   - average case: O(logN)
//   - worst case: O(N) - left-skewed tree or right skewed tree
class LC_112_Path_Sum_v2 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // base case
        if (root == null) {
            return false;
        }

        // explicitly validate if current node is a leaf node
        if (root.left == null && root.right == null) {
            if (targetSum - root.val == 0) {
                return true;
            } else {
                return false;
            }
        }

        // recursion relation
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }
}


class LC_112_Path_Sum_v2_wrong_solution {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false; // this is to handle edge case

        if (root.left == null || root.right == null)
            return false;

        return helper(root, targetSum);
    }

    boolean helper(TreeNode root, int targetSum) {
        // base case
        if (root == null) {
            if (targetSum == 0)
                return true;
            else
                return false;
        }

        // recursion relation
        return helper(root.left, targetSum - root.val) ||
                helper(root.right, targetSum - root.val);
    }
}

// solution 3: iteration using 2 stacks
// to mimic solution-2 (DFS, post-order, bottom-up)
// time: O(N)
// space: O(stack height)
//   - average case: O(logN)
//   - worst case: O(N/2) >> O(N)
//   - best case: O(1) - left-skewed tree or right skewed tree
class LC_112_Path_Sum_v3 {

    // the reason why using 2 stacks is that we need to persist a pair (node, remaining sum)
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(targetSum - root.val);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int sum = sumStack.pop();

            // explicitly validate if current node is a leaf node
            if (node.left == null && node.right == null && sum == 0) {
                return true;
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                sumStack.push(sum - node.right.val);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
                sumStack.push(sum - node.left.val);
            }
        }

        return false;
    }
}