package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC_102_Binary_Tree_Level_Order_Traversal {}

// BFS -  recursion
// time: O(N) -each node will be visited
// space: O(N) - it is the height of the tree. But in the worst case, the tree is a skewed tree
//   where N is the total number of nodes in the tree
class LC_102_Binary_Tree_Level_Order_Traversal_recursion {

    List<List<Integer>> res =  new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        // check if the tree is null
        if (root == null) {
            return res;
        }

        helper(root, 0);
        return res;
    }

    // we only handle a tree that is not null
    private void helper(TreeNode node, int level) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }

        res.get(level).add(node.val);

        if (node.left != null) {
            helper(node.left, level + 1);
        }

        if (node.right != null) {
            helper(node.right, level + 1);
        }
    }
}



// BFS -  iteration
// time: O(N) -each node will be visited
// space: O(N)
//   - O( max number of nodes on a level)
//   - for a full and complete binary tree(worst case), the last level has all the leaf nodes and will have the max number of nodes.
//   - Number of leaf nodes is given by (N+1)/2, so ignoring the constants makes the space complexity O(N)

//   where N is the total number of nodes in the tree
class LC_102_Binary_Tree_Level_Order_Traversal_iteration{

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        // handle null-tree
        if (root == null) {
            return res;
        }

        // initialize queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelIndex = 0;
        while (!queue.isEmpty()) {
            // add list to hold all nodes in a level
            res.add(new ArrayList<>());

            // poll all nodes in the same level
            int levelLength =  queue.size();
            for (int i = 0; i < levelLength; i++) { // CANNOT: for (int i = 0; i < queue.size(); i++)
                TreeNode node = queue.poll();

                res.get(levelIndex).add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // level++
            levelIndex++;
        }

        return res;
    }
}
