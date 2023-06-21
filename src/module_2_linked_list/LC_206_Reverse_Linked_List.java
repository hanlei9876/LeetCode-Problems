package module_2_linked_list;

import java.util.List;

// constraints: The number of nodes in the list is the range [0, 5000]
public class LC_206_Reverse_Linked_List {

    // solution 1: iteration (without using sentinel node)
    // two edge cases are all covered by the code below
    // time: O(N)
    // space: O(1)
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;

        // where there is only one node in the list, no reverse is done
        // we are actually maintaining two pointers
        while (curr != null && curr.next != null) {
            ListNode post = curr.next;
            curr.next = post.next;
            post.next = head;
            head = post;
        }

        return head;
    }

    // solution 2: recursion
    // time: O(N)
    // space: O(N)
    public ListNode reverseList_2(ListNode head) {
        // base case: reverse(3 -> null), reverse(null)
        if (head == null || head.next == null) {
            return head;
        }

        // recurrence relation: below have order restriction. we must call the recursive function first
        ListNode subListHead = reverseList_2(head.next);
        head.next.next = head;
        head.next = null;
        head = subListHead; // could skip this line and return subListHead directly

        return head;
    }
}
