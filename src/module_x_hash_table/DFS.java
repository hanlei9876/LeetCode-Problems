package module_x_hash_table;

import java.util.Stack;

public class DFS {

    public static void main(String[] args) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode node1 = new TreeNode(5);
        stack.push(node1);
        System.out.println(stack.peek()); // node reference

        stack.push(null);
        System.out.println(stack.peek()); // null

        System.out.println(stack.size()); // 2
        stack.pop();
        System.out.println(stack.size());
        stack.pop();
        System.out.println(stack.size());
        stack.pop();
    }
}
