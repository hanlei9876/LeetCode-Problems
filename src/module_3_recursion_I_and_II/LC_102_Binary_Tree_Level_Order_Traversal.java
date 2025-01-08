package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC_102_Binary_Tree_Level_Order_Traversal {}

// BFS -  recursion
// time: O(N) - each node will be visited
// space: O(h)
//    - O(logN) in average case
//    - O(N) in the worst case, the tree is a (left or right) skewed tree
// where N is the total number of nodes in the tree, h is the height of the tree
class LC_102_Binary_Tree_Level_Order_Traversal_recursion {

    List<List<Integer>> res =  new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        helper(root, 0);
        return res;
    }

    // we only handle a tree that is not null
    private void helper(TreeNode node, int level) {
        // base case
        if (node == null) {
            return;
        }

        // recursion relation
        if (level == res.size()) { // check: level matches list.size()
            res.add(new ArrayList());
        }

        // process current node
        res.get(level).add(node.val);

        // handle next level
        helper(node.left, level + 1);
        helper(node.right, level + 1);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        System.out.println(list); // [3, 4]

        list.add(null);
        System.out.println(list); // [3, 4, null]

        list.add(6);
        System.out.println(list); // [3, 4, null, 6]
    }
}



// BFS -  iteration
// time: O(N) -each node will be visited once exactly
// space: O( max number of nodes on a level) >> O(N)
//   - for a full and complete binary tree (worst case), the last level has all the leaf nodes and will have the max number of nodes.
//   - Number of leaf nodes is given by (N+1)/2, so ignoring the constants makes the space complexity O(N)
// where N is the total number of nodes in the tree
class LC_102_Binary_Tree_Level_Order_Traversal_iteration{

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        // handle edge case
        if (root == null) {
            return res;
        }

        // initialize queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int levelIndex = 0;
        while (!queue.isEmpty()) { // each single iteration handles all nodes at one single level
            // add list to hold all nodes in a level
            res.add(new ArrayList<>());

            // poll all nodes in the same level
            int levelLength = queue.size(); // we must declare how many nodes to poll from queue, as the queue's size is dynamically changing during the for loop
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
