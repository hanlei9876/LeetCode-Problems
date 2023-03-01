package module_2_linked_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// constriants: length >= 1
public class LC_234_Palindrome_Linked_List {

    // solution 1: copy nodes to ArrayList, so to check symmetry from both ends using two pointers.
    // time: O(1.5N) >> O(N)
    // space: O(N)
    public boolean isPalindrome(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p);
            p = p.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (list.get(i).val != list.get(j).val) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    // solution 2: use stack to store 1st half, compare them with the second half
    // time: O(N)
    // space: O(N/2) >> O(N)
    // edge cases: length == 1, 2
    public boolean isPalindrome_2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        // find the middle node/last node of first half, while store the first half in stack
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            stack.push(slow);

            slow = slow.next;
            fast = fast.next.next;
        }
        stack.push(slow);

        // if list length is odd, the middle node will not be compared, so will be popped out
        if (fast.next == null) { // this means there are odd number of nodes
            stack.pop();
        }

        // compare stack with the second half
        ListNode p = slow.next;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            if (node.val != p.val) {
                return false;
            }
            p = p.next;
        }

        return true;
    }

    // solution 3: find last node of 1st half, reverse second half. check. reverse second half back
    // time: O(N)
    // space: O(N)
    public boolean isPalindrome_3(ListNode head) {
        // find middle node/the last node of the first half
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse 2nd half
        slow.next = reverseLinkedList(slow.next);

        // compare the two halves
        boolean isPalindrome = true;

        ListNode p1 = head;
        ListNode p2 = slow.next;
        while (p2 != null) {
            if (p1.val != p2.val) {
                isPalindrome = false;
            }

            p1 = p1.next;
            p2 = p2.next;
        }

        // reverse 2nd half back
        slow.next = reverseLinkedList(slow.next);

        return isPalindrome;
    }

    // helper method for solution 3, return head
    private ListNode reverseLinkedList(ListNode head) {
        ListNode curr = head;

        while (curr != null && curr.next != null) {
            ListNode post = curr.next;
            curr.next = post.next;
            post.next = head;
            head = post;
        }

        return head;
    }

}
