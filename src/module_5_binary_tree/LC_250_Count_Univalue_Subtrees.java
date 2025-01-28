package module_5_binary_tree;

import java.util.AbstractMap;
import java.util.Map;

public class LC_250_Count_Univalue_Subtrees {

    // What exactly is a uni-value subtree? - https://leetcode.com/problems/count-univalue-subtrees/editorial/comments/1949534
    //  - This is confusing but think about it as an opportunity to practice asking questions to interviewers
    //    This is your cue to create some quick sample test cases and check your understanding.
    //
    //    This is what you would need to do in an interview setting as well as well by asking the interviewer clarifying questions.
    //    If the question isn't presented 100% clearly you're responsible for asking questions until you're clear,
    //    and whether the ambiguity is on purpose or just lazy question writing won't matter in that setting.
    //
    //  - We can stand at one node and look down to see all its children.
    //    If this node's value == all it's child nodes' value,
    //    then the subtree with this node as root is a uni-value subtree

    // If we look at it from implementation perspective,
    // given a node (Node-A) in the tree, the subtree with Node-A as root is a univalue subtree
    //              <==>
    //   (1) node-A's left & right child are both univalue subtree
    //                &&
    //   (2) Node-A.val == left child.val == right child.val
    //
    // Under this definition, the edges cases are:
    //    - Node-A is a leaf node: we determine Node-A is a univalue subtree and we count by 1
    //    - Node-A is null: we determine Node-A is a univalue subtree but we don't count it
}

// solution 1: recursion (dfs, postorder) - use global variable - bottom up
// time: O(N)
// space: O(H)
//   - average case: O(logN)
//   - worst case: O(N) - left-right skewed tree
class Solution_1 {

    int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        helper(root);
        return count;
    }

    private boolean helper(TreeNode node) {
        // base case
        if (node == null) {
            return true;
        }

        // recursion relation
        boolean isLeftUni = helper(node.left);
        boolean isRightUni = helper(node.right);

        if (!isLeftUni || !isRightUni) {
            return false;
        }
        // else

        // for node, node.left have 3 conditions totally:
        //    case 1: true    case 2: true    case 3: false
        //      5                5               5
        //     /                /               /
        //    x                5               4
        if (node.left != null && node.val != node.left.val) {
            return false;
        }
        if (node.right != null && node.val != node.right.val) {
            return false;
        }

        count++;
        return true;
    }
}

// solution 2: refactor solution 1 to use local variable - bottom up
// The non-constant global variables are evil because their value can be changed by any function.
// Using global variables reduces the modularity and flexibility of the program.
// It is always suggested not to use global variables and instead use local variables in the program.
// time: O(N)
// space: O(H)
//   - average case: O(logN)
//   - worst case: O(N) - left-right skewed tree
class Solution_2 {

    public int countUnivalSubtrees(TreeNode root) {
        return helper(root).getValue();
    }

    private Map.Entry<Boolean, Integer> helper(TreeNode node) {
        // base case
        if (node == null) {
            return new AbstractMap.SimpleEntry<>(true, 0);
        }

        // recursion relation
        Map.Entry<Boolean, Integer> left = helper(node.left);
        Map.Entry<Boolean, Integer> right = helper(node.right);

        if (!left.getKey() || !right.getKey()) {
            return new AbstractMap.SimpleEntry<>(false, left.getValue() + right.getValue());
        }
        // else

        if (node.left != null && node.val != node.left.val) {
            return new AbstractMap.SimpleEntry<>(false, left.getValue() + right.getValue());
        }
        if (node.right != null && node.val != node.right.val) {
            return new AbstractMap.SimpleEntry<>(false, left.getValue() + right.getValue());
        }

        int count = left.getValue() + right.getValue() + 1;
        return new AbstractMap.SimpleEntry<>(true, count);
    }
}