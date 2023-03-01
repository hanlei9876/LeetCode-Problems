package module_2_linked_list;

// edge case: length = 0, 1
public class LC_328_Odd_Even_Linked_List {

    // solution: one pass with three pointers + two sentinel nodes.
    // time: O(N)
    // space: O(1)
    public ListNode oddEvenList(ListNode head) {
        ListNode sentinel_odd = new ListNode(0);
        ListNode sentinel_even = new ListNode(0);

        ListNode p1 = sentinel_odd;
        ListNode p2 = sentinel_even;
        ListNode p = head;

        int n = 1;
        while (p != null) {
            if (n % 2 != 0) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }

            p = p.next;
            n++;
        }

        // when there are odd number of nodes in the list, the tail of even list is still pointing to the last node of odd list,
        // which will cause loop in the resulting list
        p2.next = null; // Important: this is to avoid cycle in the new list
        p1.next = sentinel_even.next;

        return sentinel_odd.next;
    }

}
