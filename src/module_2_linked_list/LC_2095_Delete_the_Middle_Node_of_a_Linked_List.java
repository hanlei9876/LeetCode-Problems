package module_2_linked_list;

// constraints: the number of nodes in the list is in the range [1, 10^5]
public class LC_2095_Delete_the_Middle_Node_of_a_Linked_List {

    // solution 1: two passes
    // edge case: n == 1
    // time: O(N + N/2) = O(N)
    // space: O(1)
    public ListNode deleteMiddle(ListNode head) {
        // handle edge case: n == 1
        if (head.next == null)
            return null;

        int n = 0;
        ListNode p1 = head;
        while (p1 != null) {
            n++;
            p1 = p1.next;
        }

        // move to the node before the middle node to be deleted
        // if n == 4, index == 1 (0-based)
        // if n == 5, index == 2 (0-based)
        int index = n / 2 - 1;

        ListNode p2 = head;
        for (int i = 0; i <index; i++) {
            p2 = p2.next;
        }

        // delete node
        p2.next = p2.next.next;

        return head;
    }

    // solution 2: variation of two-pointers
    // edge case: n == 1
    // time: O(N/2) = O(N)
    // space: O(1)
    public ListNode deleteMiddle_2(ListNode head) {
        if (head.next == null)
            return null;

        ListNode fast = head.next.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }

}
