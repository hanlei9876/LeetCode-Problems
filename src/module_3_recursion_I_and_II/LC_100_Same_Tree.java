package module_3_recursion_I_and_II;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC_100_Same_Tree {

    // solution 1: recursion
    // time: O(N)
    // space: O(N) - this is DFS, the worst case is completely unbalanced tree
    // where N is total amount of nodes in tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // base case
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        // recursion relation - now both p & q are not null
        if (p.val != q.val) {
            return false;
        }

        boolean isLeftSame = isSameTree(p.left, q.left);
        boolean isRightSame = isSameTree(p.right, q.right);
        return isLeftSame && isRightSame;
    }

    /*******************************************************************/
    // solution 2: recursion to iteration
    // iterative DFS (stack) vs BFS (queue)
    /*******************************************************************/

    // solution 2.1: iteration using stack - DFS
    // time: O(N) - each node is visited exactly once.
    // space:
    //      - worst case for stack height - O(N), where tree only has left nodes (see note)
    //      - perfectly balanced tree - O(logN)
    public boolean isSameTree_2(TreeNode p, TreeNode q) {
        /** base case - part 1/2 */
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        /*************************/

        // recursion relation - now: p != null && q != null
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        stack.push(q);

        while (!stack.empty()) {
            TreeNode pNode = stack.pop();
            TreeNode qNode = stack.pop();

            /** base case - part 2/2 */
            if (pNode == null && qNode == null)
                continue;
            if (pNode == null || qNode == null)
                return false;
            /*************************/

            if (pNode.val != qNode.val)
                return false;

            stack.push(qNode.right);
            stack.push(pNode.right);
            stack.push(qNode.left);
            stack.push(pNode.left);
        }

        return true;
    }

    // solution 2.2: iteration using queue - BFS
    // time: O(N) - each tree will be iterated
    // space: O(N) - worst case: fully balanced binary tree (see note)
    public boolean isSameTree_3(TreeNode p, TreeNode q) {
        /** base case - part 1/2 */
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        /*************************/

        // recursion relation - now: p != null && q != null
        Queue<TreeNode> queue = new LinkedList<>();
        // Queue<TreeNode> queue = new ArrayDeque<>();
        // queue.offer(null); - this will throw null pointer exception
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode pNode = queue.poll();
            TreeNode qNode = queue.poll();

            /** base case - part 1/2 */
            if (pNode == null && qNode == null)
                continue;
            if (pNode == null || qNode == null)
                return false;
            /*************************/

            if (pNode.val != qNode.val)
                return false;

            queue.offer(pNode.left);
            queue.offer(qNode.left);
            queue.offer(pNode.right);
            queue.offer(qNode.right);
        }

        return true;
    }


    // implementing Dequeue & ArrayDeque
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();

        System.out.println(deque); // []

        deque.addLast(10); // add at the last
        deque.addLast(20);
        deque.addLast(30);
        deque.addFirst(0);
        System.out.println(deque); // [0, 10, 20, 30]

        int first = deque.removeFirst();
        int last = deque.removeLast();
        System.out.println("First: " + first + ", Last: " + last);

        System.out.println(deque);


        Deque<Integer> deque2 = new ArrayDeque<>(2);
        deque2.addLast(10); // add at the last
        deque2.addLast(20);
        deque2.addLast(30);
        deque2.addFirst(0);
        System.out.println(deque2); // [0, 10, 20, 30]


        Stack<String> stack = new Stack<>();
        stack.push("abc");
        System.out.println(stack); // [abc]

        stack.push(null);
        System.out.println(stack); // [abc, null]
        String s = stack.pop();
        System.out.println("s is: " + s); // s is: null


        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue); // [1, 2, 3]
        queue.poll();
        System.out.println(queue); // [2, 3]
        queue.offer(4);
        System.out.println(queue); // [2, 3, 4]



        Queue<Integer> queue2 = new ArrayDeque<>();
        queue2.offer(3);
        System.out.println(queue2);
        queue2.offer(null); // throws NullPointerException
    }
}

