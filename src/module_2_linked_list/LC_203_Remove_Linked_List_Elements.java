package module_2_linked_list;

public class LC_203_Remove_Linked_List_Elements {

    // solution 1: brute force (see my notes)
    // use ArrayList to hold all qualified nodes. Then create a new linked list from the ArrayList
    // time: O(2N) >> O(N)
    // space: (N)


    // solution-2: sentinel node
    // the key is to keep the head when we need to remove the head node.
    // sentinel node is the solution to keep the head
    // edge cases (length == 0) are all covered
    // time: O(N)
    // space: O(1)
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr = curr.next;
            } else {
                prev = prev.next;
                curr = curr.next;
            }
        }

        return sentinel.next;
    }
}
