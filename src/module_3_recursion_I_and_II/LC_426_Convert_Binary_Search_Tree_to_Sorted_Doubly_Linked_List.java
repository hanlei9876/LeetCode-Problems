package module_3_recursion_I_and_II;

import java.util.Stack;

// the returned linked list is: bidirectional, circular, sorted
public class LC_426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List {
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


// solution 1: DFS - Inorder Traversal using recursion
// time: O(N) - all nodes are visited exactly once
// space: O(H)
//       - O(logN) for best case where BST is balanced
//       - O(N) for left-skewed or right-skewed tree
class LC_426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List_v1 {

    Node head = null;
    Node prev = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        dfs(root);

        // close the DLL
        prev.right = head;
        head.left = prev;

        return head;
    }

    private void dfs(Node node) {
        // base case
        if (node == null) {
            return;
        }

        // recursion relation
        dfs(node.left);

        // handle current node - argument "node" is acting as the curr pointer
        if (prev == null) {
            head = node;
        } else {
            node.left = prev;
            prev.right = node;
        }
        prev = node;

        dfs(node.right);
    }
}


// solution 2: DFS - Inorder Traversal using recursion + stack
// time: O(N) - all nodes are visited exactly once
// space: O(H)
//      - O(logN) in average case, h is tree height
//      - O(N) in worst case, where input is a left-skewed tree
//      - O(1) in the best case for right-skewed tree
class LC_426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List_v2 {

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Stack<Node> stack = new Stack<>();
        Node curr =root;

        Node prev = null;
        Node head = null;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            // handle current node - curr
            if (prev == null) {
                head = curr;
            } else {
                curr.left = prev;
                prev.right = curr;
            }
            prev = curr;

            curr = curr.right;
        }

        // close the DLL
        prev.right = head;
        head.left = prev;

        return head;
    }
}


