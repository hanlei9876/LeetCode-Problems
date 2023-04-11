package module_2_linked_list;

// constraints:
//      n >= 0
//      k >= 0
public class LC_61_Rotate_List {

    // solution - needs to simplify
    // first locate 4 nodes: head >> new_tail >> new_head >> tail. then perform the manipulations for them
    // the most tricky part is to deal with the edge case: k = 0
    // time: O(N)
    // space: O(1)
    public ListNode rotateRight(ListNode head, int k) {
        // edge cases: n == 0, 1
        if (head == null || head.next == null)
            return head;

        ListNode tail = head;
        int index = 0;
        while (tail.next != null) {
            tail = tail.next;
            index++;
        }
        int len = index + 1;
        // now tail is found

        // next, find new_tail & new_head
        // given n, when k == n, then the rotated list will be the same as original. so, it is meaningful when 0 < k < n
        ListNode new_tail = head;
        int index_new_tail = len - (k % len) - 1;
        for (int i = 0; i < index_new_tail; i++) {
            new_tail = new_tail.next;
        }
        // new tail is found
        ListNode new_head;
        if (new_tail.next != null) {
            new_head = new_tail.next;
        } else { // edge case: when k = 0
            new_head = head;
        }

        // do manipulation
        tail.next = head;
        new_tail.next = null; // note: we must keep the sequence of the two statements. Otherwise it will return null when k=0

        return new_head;
    }

    // solution (simplify last solution's code)
    // the key: when old_tail is found, need to instantly point old_tail.next to old_head
    // this way will cover the edge case: k = 0 (n >= 2, say 5), where old_tail and new_tail require to be the same, and old_head and new_head require to be the same
    public ListNode rotateRight_2(ListNode head, int k) {
        // edge cases: n == 0, 1
        if (head == null || head.next == null)
            return head;

        // find old_tail & length
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        tail.next = head; // optimization

        // find new_tail & new_head
        ListNode new_tail = head;
        for (int i = 0; i < len - (k % len) - 1; i++) {
            new_tail = new_tail.next;
        }
        ListNode new_head = new_tail.next; // edge case (k = 1) is covered, where new_tail is the last node in the list
        new_tail.next = null;

        return new_head;
    }

}



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
