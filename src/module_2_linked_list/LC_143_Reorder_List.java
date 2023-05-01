package module_2_linked_list;

// constraints: 1 <= n <= 5 * 10^4
public class LC_143_Reorder_List {

    // solution - 4 steps: find middle node -> -> break the two halves -> reverse 2nd half -> merge two lists
    // edge case: n = 1
    public void reorderList(ListNode head) {
        // find middle node
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // now slow is pointing the last node of the 1st half, we break the two halves
        ListNode head2 = slow.next;
        slow.next = null;

        // reverse 2nd half
        ListNode curr = head2;
        while (curr != null && curr.next != null) {
            ListNode post = curr.next;
            curr.next = post.next;
            post.next = head2;
            head2 = post;
        }

        // merge 2 lists: head & head2 (head is always in front)
        // two situations:
        //  if n is odd, then p1.next == null && p2 == null
        //  if n is even, then p1 == null && p2 == null
        ListNode sentinel = new ListNode(0);
        ListNode prev = sentinel;
        ListNode p1 = head;
        ListNode p2 = head2;
        while (p2 != null) {
            prev.next = p1;
            p1 = p1.next;
            prev = prev.next;

            prev.next = p2;
            p2 = p2.next;
            prev = prev.next;
        }

        if (p1 != null) // handle the odd length situation
            prev.next = p1;
        // head is always staying stiil, and therefore, is what we return
    }

}
