package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

public class LC_94_Binary_Tree_Inorder_Traversal {
}

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
