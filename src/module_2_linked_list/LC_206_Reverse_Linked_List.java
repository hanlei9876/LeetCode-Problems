package module_2_linked_list;

import java.util.List;

public class LC_206_Reverse_Linked_List {

    // solution 1: iteration
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

}
