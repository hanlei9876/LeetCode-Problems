package module_5_binary_tree;


import java.util.LinkedList;
import java.util.Queue;

public class LC_101_Symmetric_Tree {}


// solution 1: recursion (bottom up)
// time: O(N/2) >> O(N) - for each layer (except root node layer), visit half nodes exactly. so it's totally N/2
// space: O(H)
//    - average: O(logN)
//    - worst: O(N/2) >> O(N) - see the case below:
//      1
//    /  \
//   2    2
//  /      \
// 3        3
class LC_101_Symmetric_Tree_v1_recursion {

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // base case
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        // recursion relation
        return (left.val == right.val)
                &&
                isMirror(left.left, right.right)
                &&
                isMirror(left.right, right.left);
    }
}


// solution 2: iteration - use queue to mimic recursion (BFS), this is top-down approach
//
// time: O(N/2) >> O(N) - in each level, loop n count == half amount of nodes at this level
// space: O(level with max number of nodes) >> O(N/2) >> O(N) - queue's max size
//   - In a full binary tree with N nodes, number of leaves is (N + 1) / 2
class LC_101_Symmetric_Tree_v2_iteration {

    public boolean isSymmetric(TreeNode root) {
        // initialize queue (Queue interface can take in Null node)
        // root will never be null, as described in problem
        Queue<TreeNode> queue= new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null) {
                return false;
            }
            if (node1.val != node2.val) {
                return false;
            }

            queue.add(node1.left);
            queue.add(node2.right);
            queue.add(node1.right);
            queue.add(node2.left);
        }

        return true;
    }
}
