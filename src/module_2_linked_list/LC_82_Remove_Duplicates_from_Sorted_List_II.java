package module_2_linked_list;

// constraints: The number of nodes in the list is in the range [0, 300].
public class LC_82_Remove_Duplicates_from_Sorted_List_II {

    // solution: optimized two-pointer technique
    // edge cases: n = 0, 1 are both covered
    // time: O(N) - one pass
    // space: O(1)
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel;
        ListNode curr = head;
        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }

                // delete sublist
                curr = curr.next;
                prev.next = curr;
            } else { // curr.next == null || curr.val != curr.next.val
                curr = curr.next;
                prev = prev.next;
            }
        }

        return sentinel.next;
    }
}
