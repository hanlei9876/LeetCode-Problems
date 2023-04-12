package module_2_linked_list;

// constraints: the number of nodes in the list is in the range [1, 100]
public class LC_876_Middle_of_the_Linked_List {

    // solution 1: two passes - calculate the list length, find the middle node
    // without sentinel node, edge case number of nodes == 1
    // time: O(N + N/2) = O(N)
    // space: O(1)
    public ListNode middleNode(ListNode head) {
        // count the list length n
        int n = 0;
        ListNode p1 = head;
        while (p1 != null) {
            n++;
            p1 = p1.next;
        }

        // find the middle node - need to move pointer for "mid" steps
        // we don't have to care about if n is odd or even
        ListNode p2 = head;
        int mid = n / 2;
        for (int i = 0; i < mid; i++) {
            p2 = p2.next;
        }

        return p2;
    }

    // solution 2: two pointers
    // time: O(N/2) = O(N)
    // space: O(1)
    public ListNode middleNode_2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast.next == null) { // there are odd number of nodes
            return slow;
        }

        return slow.next; // in this case, fast.next != null (fast.next.next == null), which means there are odd number of nodes
    }
}
