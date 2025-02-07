package module_5_binary_tree;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


// Overall interview strategy for the 3 solutions
//   when you quickly solve this using recursive approach, interviewer may get a hint you have seen it before.
//   so naturally the interviewer may want to increase the difficulty level, may ask for iterative approach (solution 2) first,
//   then may push for iterative approach without parent pointer (solution 3) to see what direction you are thinking,
//   you might not fail interview just because of unable to implement 3rd solution
public class LC_236_Lowest_Common_Ancestor_of_a_Binary_Tree {

    // key 1: overall strategy - DFS traverse tree, when finding target nodes, start to backtrack (bottom-up manner)
    // As long as we go from bottom up, the first common ancestor must be LCP
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


// solution 2: backtrack - iteration using parent pointer - stack, hashmap, hashset
//   stage 1 - DFS with stack to traverse each tree node. For each node, put each parent pointer (child <- parent) in hashmap
//   stage 2 - backtrack against hashmap while using hashset to find LCA
// time: O(N) + O(H) + O(H) >> O(N)
// space: O(H) + O(N) + O(H) >> O(N)
class LC_236_Solution_2 {

    // space analysis
    //   stack: O(H) - DFS
    //   hashmap: O(N) - each node is traversed and stored in here
    //   hashset: O(H) - q & all its precedents are stored in here

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // stage 1
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();
        stack.push(root);
        map.put(root, null);

        // time: O(N) - each node is traversed
        while (!map.containsKey(p) || !map.containsKey(q)) {
            TreeNode node = stack.pop();

            // it doesn't matter which child is pushed to stack first, they are equivalent
            if (node.right != null) {
                stack.push(node.right);
                map.put(node.right, node);
            }
            if (node.left != null) {
                stack.push(node.left);
                map.put(node.left, node);
            }
        }

        // stage 2
        Set<TreeNode> set = new HashSet<>();
        TreeNode pPointer = p;
        TreeNode qPointer = q;

        // time: O(H)
        while (!set.contains(pPointer)) {
            set.add(pPointer);
            pPointer = map.get(pPointer);
        }

        // time: O(H)
        while (!set.contains(qPointer)) {
            qPointer = map.get(qPointer);
        }

        return qPointer;
    }
}


// solution 3: backtrack (use iteration to simulate backtracking) - iteration using stack with no pointer (see slides of LeeCode official solution)
// We always have a pointer to the possible LCA. The moment we find both the nodes, we return the pointer as the answer
//
// each loop visit a node & handle it >> every node is visited 3 times: 0>1, 1>2, 2>pop
// time: O(N)
// space: O(H)
//    - average O(logN)
//    - worst O(N)
class LC_236_Solution_3 {

    // Stack is used to store <node, state>, state describes the node. We encode 3 possible state as below:
    //   - state = 0 means 0 child nodes have been visited
    //   - state = 1 means left child nodes have been visited
    //   - state = 2 means both left & right child nodes have been visited

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<Map.Entry<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new AbstractMap.SimpleEntry<>(root, 0));

        TreeNode LCA = null;

        boolean oneFound = false;

        // each loop will handle one visited node
        // for each node, we check its state & take actions accordingly - stack will pop node ONLY WHEN state == 2

        while (!stack.isEmpty()) {
            // peek top entry on stack, retrieve node & state
            Map.Entry<TreeNode, Integer> entry = stack.peek();
            TreeNode node = entry.getKey();
            int state = entry.getValue();

            if (state == 0) {
                // step 1: check if the current parent_node is either p or q as soon as we initially landed to the node
                if (node == p || node == q) {
                    if (oneFound) {
                        return LCA; // If one_node_found was set already, this means we have found both the nodes
                    } else {
                        oneFound = true; // Otherwise, set one_node_found to True, to mark one of p and q is found
                        LCA = node; // Save the current top element of stack as the LCA
                    }
                }

                // step 2: change node state by adding from 0 to 1
                entry.setValue(1);

                // step 3: push left child to stack
                if (node.left != null) {
                    stack.push(new AbstractMap.SimpleEntry<>(node.left, 0));
                }
            } else if (state == 1) {
                // step 1: change node state by adding from 1 to 2
                entry.setValue(2);

                // step 2: push right child to stack
                if (node.right != null) {
                    stack.push(new AbstractMap.SimpleEntry<>(node.right, 0));
                }
            } else {
                // step 1: pop node
                TreeNode poppedNode = stack.pop().getKey();

                // step 2: if this popped node is LCA, then pass on LCA tag to next top node on stack
                if (LCA == poppedNode) {
                    LCA = stack.peek().getKey();
                }
            }
        }

        return null; // we will never get to here
    }
}