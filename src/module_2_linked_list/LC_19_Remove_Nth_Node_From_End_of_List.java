package module_2_linked_list;

public class LC_19_Remove_Nth_Node_From_End_of_List {

    // Solution 1: two passes
    // one pass to calculate the list's length; another pass find and remove the Xth node from head (0-based index)
    // time: O(N) + O(N - n) >> O(N) (worst case is O(2N))
    // space: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // calculate the length
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }

        // calculate xth node from the head, which is the one to remove (0-based index)
        int x = len - n;

        // edge case: if we need to remove head, then the head would change
        if (x == 0) {
            return head.next;
        }

        /**
        ListNode curr = head;
        ListNode prev = new ListNode(-1); // introduce sentinel node
        prev.next = curr;
        int i = 0;
        while (i < x) {
            prev = prev.next;
            curr = curr.next;

            i++;
        }

         prev.next = curr.next;
         **/

        ListNode curr = head;
        int i = 0;
        while (i < x - 1) {
            curr = curr.next;
            i++;
        }

        curr.next = curr.next.next;

        return head;
    }

    // Solution 2: two pointers (one-pass)
    // first, enable the two pointers have a gap of n+1,
    // then, move the two pointers at the same time until the further pointer hits the null (the end)
    // time: O(N)
    // space: O(1)
    public ListNode removeNthFromEnd_2(ListNode head, int n) {
        // 1 <= length <= 30
        // 1 <= n <= length

        // stage 1: move one pointer curr n steps
        ListNode curr = head;
        for (int i = 0; i < n; i++) {
            curr = curr.next;
        }
        // above, we cannot write "i < n + 1", as this will cause Null Pointer Exception on the following case:
        // given a Linked List, where length = 5, n = 5, which means we need to remove the first node (head).
        // In this case, if we use "i < n + 1", we will have null pointer exception at curr.next.

        // handle edge case: when the head is the one to remove, we need to update the head
        if (curr == null) {
            return head.next;
        }

        // stage 2: Goal -> to find the node before the removed node
        // move two pointers together until curr arrives at the last node,
        ListNode nodeBeforeRemove = head;
        while (curr != null && curr.next != null) {
            nodeBeforeRemove = nodeBeforeRemove.next;
            curr = curr.next;
        }

        nodeBeforeRemove.next = nodeBeforeRemove.next.next;

        return head;
    }

}
