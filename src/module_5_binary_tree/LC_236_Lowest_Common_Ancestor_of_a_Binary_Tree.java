package module_5_binary_tree;

public class LC_236_Lowest_Common_Ancestor_of_a_Binary_Tree {

    // key 1: overall strategy - DFS traverse tree, when finding target nodes, start to backtrack (bottom-up manner)
    //   When arrive one node, check 3 things:
    //       left subtree contains one of the target nodes?
    //       right subtree contain one of the target nodes?
    //       the current is one of the target nodes?
    //   If at least any 2 conditions are true, it means we are at the first common node - this is the LCP
    //
    // Key 2: due to we travel tree from bottom up, once we found LCP for the first time, we found LCP & will never ever find a second LCP
    //
    // Trick: map true/false to 1/0, so to easily check 3 booleans at once
}

// solution 1: recursion - DFS, bottom up
// time: O(N)
// space: O(H)
//   - average case: O(logN)
//   - worst case: O(N) - left-right skewed tree
class LC_236_Solution_1 {

    TreeNode lcp;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lcp = null;
        helper(root, p, q);
        return lcp;
    }

    private boolean helper (TreeNode node, TreeNode p, TreeNode q) {
        // base case
        if (node == null) {
            return false;
        }

        // recursion relation
        int left = helper(node.left, p, q) ? 1 : 0;
        int right = helper(node.right, p, q) ? 1 : 0;

        int mid = (node.val == p.val || node.val == q.val) ? 1 : 0;

        if (left + right + mid >= 2) {
            lcp = node;
        }

        return (left + right + mid >= 1);
    }
}