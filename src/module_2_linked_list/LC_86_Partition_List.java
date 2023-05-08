package module_2_linked_list;

import java.util.List;

// The number of nodes in the list is in the range [0, 200].
// -100 <= Node.val <= 100
// -200 <= x <= 200
public class LC_86_Partition_List {

    // solution 1: in-place operation (not recommended, as need to check if the first node is to be moved)
    // use sentinel node for deletion & insertion
    // use 3 pointers:
    //     p - point to the left partition list
    //     prev & curr - iterate through input list, if necessary, use two pointers to delete curr node & insert it after p node
    // time: O(N)
    // space: O(1)
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        // now, the list has 2 nodes at least
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode p;
        if (head.val < x) {
            p = head;
        } else {
            p = sentinel;
        }
        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null) {
            if (curr.val < x) {
                // use two pointers to delete curr node & insert it after p node
                prev.next = curr.next;
                curr.next = p.next;
                p.next = curr;

                p = p.next;
                curr = prev.next;

            } else {
                curr = curr.next;
                prev = prev.next;
            }
        }

        return sentinel.next;
    }

    // solution 2 (recommended): in-place operation
    // create 2 sentinel nodes to hold input list and partitioned list, respectively, for deletion & insertion
    // use 3 pointers:
    //     p - point to the left partition list
    //     prev & curr - iterate through input list, if necessary, use two pointers to delete curr node & insert it after p node
    // time: O(N)
    // space: O(1)
    public ListNode partition_2(ListNode head, int x) {
        ListNode sentinel_1 = new ListNode(0);
        sentinel_1.next = head;
        ListNode sentinel_2 = new ListNode(0);
        sentinel_2.next = null;

        ListNode prev = sentinel_1;
        ListNode curr = head;
        ListNode p = sentinel_2;
        while (curr != null) {
            if (curr.val < x) {
                prev.next = curr.next;
                curr.next = p.next;
                p.next = curr;

                curr = prev.next;
                p = p.next;
            } else {
                curr = curr.next;
                prev = prev.next;
            }
        }

        p.next = sentinel_1.next;

        return sentinel_2.next;
    }
}
