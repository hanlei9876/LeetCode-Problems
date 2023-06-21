package module_3_recursion_I_and_II;

public class LC_24_Swap_Nodes_in_Pairs {

    // solution-1: iteration + sentinel node
    // time: O(N/2) >> O(N)
    // space: O(1)
    public ListNode swapPairs(ListNode head) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode p = sentinel;
        while (p.next != null && p.next.next != null) {
            // swap nodes
            ListNode p1 = p.next;
            ListNode p2 = p.next.next;

            p1.next = p2.next;
            p2.next = p1;
            p.next = p2;

            // move p for next iteration
            p = p.next.next;
        }

        return sentinel.next;
    }

    // solution-2: recursion
    public ListNode swapPairs_2(ListNode head) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode p = sentinel;
        swap(p);

        return sentinel.next;
    }

    // We have two independent sub-problems (can be in any order):
    //      1. swap the first 2 nodes after p
    //      2. handle the remaining list
    // Base case: if the nodes after p < 2, terminate the function
    private void swap(ListNode p) {
        // base case:
        if (p.next == null || p.next.next == null) {
            return;
        }

        // sub-problem 1: swap 2 nodes after p
        ListNode p1 = p.next;
        ListNode p2 = p.next.next;

        p1.next = p2.next;
        p2.next = p1;
        p.next = p2;

        // sub-problem 2: swap nodes in the remaining list
        p = p.next.next; // move p for next iteration
        swap(p);
    }

    // solution-3: recursion (recommended) we have notes for this solution
    //  - recursion function has returned value
    // in each recursive call, we can swap the firstNode and secondNode in the current recursive call
    // and then return the pointer to the secondNode since it will be the new head after swapping.
    public ListNode swapPairs_3(ListNode head) {
        // base case: if the list has no node or has only one node left
        if (head == null || head.next == null) {
            return head;
        }

        // sub-problems
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // swapping
        firstNode.next = swapPairs_3(secondNode.next); // the key - in this recursion, we use the return value of the next recursion call
        secondNode.next = firstNode;
        head = secondNode;

        return head;
    }
}




class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}