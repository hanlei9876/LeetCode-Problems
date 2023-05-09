package module_2_linked_list;

// Constraints:
//  The number of nodes in the linked list is in the range [1, 100].
//  0 <= Node.val <= 9
//  The number represented by the linked list does not contain leading zeros except for the zero itself.
public class LC_369_Plus_One_Linked_List {

    // Solution - 1: Native approach
    // Not applicable for large integer where list’s size >= 32

    // solution-2: 3 passes
    //   - reverse list
    //   - perform plus 1
    //   - reverse back

    // solution-3: 2 passes
    // find the node pointing to the window of 9s at the end of the list
    // edge cases: all nodes are 9s
    // time: O(2N) >> O(N)
    // space: O(1)
    public ListNode plusOne(ListNode head) {
        ListNode sentinel = new ListNode(0); // must set value to 0, to deal with the case when all nodes are 9s
        sentinel.next = head;

        ListNode p = null;
        ListNode curr = sentinel;
        while (curr.next != null) {
            if (curr.next.val != 9) {
                p = null;
            } else {
                if (curr.val != 9) {
                    p = curr;
                }
            }
            curr = curr.next;
        }

        if (p == null) {
            curr.val++;
            return sentinel.next;
        }

        // else (p != null)
        p.val++;
        ListNode p1 = p.next;
        while (p1 != null) {
            p1.val = 0;
            p1 = p1.next;
        }

        if (p == sentinel) { // case: all nodes are 9s, so we should make sentinel node as the new added node
            return sentinel;
        } else { // case: not all nodes are 9s, no need to add additional node
            return sentinel.next;
        }
    }

}
