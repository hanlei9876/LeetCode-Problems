package module_4_binary_search;

import Helper_Classes.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// constraints: The number of nodes in the tree is in the range [1, 10^4]
public class LC_270_Closest_Binary_Search_Tree_Value {
}

// solution 1: DFS-inorder to copy values from tree to list + iterate list
// time: O(dfs) + O(N) >> O(N) + O(N)
// space: O(dfs) >> O(h): average O(logN), worst O(N)
class LC_270_Closest_Binary_Search_Tree_Value_1 {

    List<Integer> list = new ArrayList<>();

    // The closest value is found if the target value is in-between between two in-order array elements nums[i-1] < target <= nums[i]
    // Then the closest value is one of these elements.
    public int closestValue(TreeNode root, double target) {
        dfs(root);

        long prev = Long.MIN_VALUE; // we can assure Long.MIN_VALUE will never be returned
        int i = 0;
        while (i < list.size()) {
            int curr = list.get(i);

            if (Math.abs(prev - target) == Math.abs(curr - target)) {
                return (int) prev;
            } else if (Math.abs(prev - target) < Math.abs(curr - target)) {
                return (int) prev;
            }

            prev = curr;
            i++;
        }

        return (int) prev;
    }

    // DFS - Inorder
    private void dfs(TreeNode node) {
        // base case
        if (node == null)
            return;

        // recursion relation
        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
    }

    public static void main(String[] args) {
        int a = Integer.MIN_VALUE;
        System.out.println(a - 5); // 2147483643
    }
}

// solution 2: optimize solution 1 using iterative DFS
// time: O(k) - kth element is target
// space: O(H) - H is tree height
// Note: this approach is ideal if k is far more away from last element of array
class LC_270_Closest_Binary_Search_Tree_Value_2 {

    public int closestValue(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<>();

        long prev = Long.MIN_VALUE;

        TreeNode currNode = root;
        while (currNode != null || !stack.isEmpty()) {
            while (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }

            currNode = stack.pop();

            // handle the current node - start
            int curr = currNode.val;

            if (Math.abs(prev - target) <=  Math.abs(curr - target)) {
                return (int) prev;
            }

            prev = curr;
            // handle the current node - end

            currNode = currNode.right;
        }

        return (int) prev;
    }

}

// solution 3: binary search by traversing BST from root to leaf
// time: O(H) - H is tree height
// space: O(1)
class LC_270_Closest_Binary_Search_Tree_Value_3 {

    public int closestValue(TreeNode root, double target) {
        long prev = Long.MIN_VALUE;
        TreeNode currNode = root;

        while (currNode != null) {
            int curr = currNode.val;

            // handle edge case
            if (curr == target) return curr;

            if (Math.abs(prev - target) > Math.abs(curr - target)) {
                prev = curr;
            } else if (Math.abs(prev - target) == Math.abs(curr - target)) {
                if (prev > curr) {
                    prev = curr;
                }
            }

            // Otherwise, do nothing. We simply move to next node
            if (curr < target) {
                currNode = currNode.right;
            } else {
                currNode = currNode.left;
            }
        }

        return (int) prev;
    }
}
