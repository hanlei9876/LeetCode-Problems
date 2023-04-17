package module_2_linked_list;

// constraints: The number of nodes in the list is in the range [0, 300].
public class LC_83_Remove_Duplicates_from_Sorted_List {

    // solution: two-pointers
    // edge case: N = 0
    public ListNode deleteDuplicates(ListNode head) {
        // handle edge case: N = 0
        if (head == null)
            return head;

        // use two pointers
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (prev.val == curr.val) {
                prev.next = curr.next;
                curr = curr.next;
            } else {
                prev = prev.next;
                curr = curr.next;
            }
        }

        return head;
    }

}
