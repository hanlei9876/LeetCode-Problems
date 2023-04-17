package module_2_linked_list;

// Constraints:
//      The number of nodes in the list is n.
//      1 <= n <= 500
//      1 <= left <= right <= n, where left & right are 0-based indices
//      one pass
public class LC_92_Reverse_Linked_List_II {

    // solution-1: iteration (reference to LC-206)
    // use sentinel node to cover edge case when the head node is the 1st node tp reverse
    // time: O(N)
    // space: O(1)
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        // find the node before left node (left is 1-based index)
        // this is the template of finding nth node in list (n is 0-based) using sentinel
        int i = 0;
        ListNode before = sentinel;
        while (i < left - 1) {
            before = before.next;
            i++;
        }

        // reverse the sub-list by run the loop for (right - left) times
        // no sentinel node is involved
        ListNode curr = before.next;
        int j = 0;
        while (j < (right - left)) {
            ListNode post = curr.next;
            curr.next = post.next;
            post.next = before.next;
            before.next = post;
            j++;
        }

        return sentinel.next;
    }

/**
 * template: find the Nth node in a list (N is 1-based index)
 *         int i = 0;
 *         ListNode p = sentinel;
 *         while (i < N) {
 *             p = p.next;
 *             i++;
 *         }
 *
 * now p is pointing to the Nth node
 * */

}
