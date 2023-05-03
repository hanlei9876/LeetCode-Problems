package module_2_linked_list;

import java.util.List;

// constraints: 1 <= k <= n <= 105
// k is 1-based index
public class LC_1721_Swapping_Nodes_in_a_Linked_List {

    // solution 1: 3-passes
    // time: O(N + k + (N - k)) -> O(2N) >> O(N)
    // space: O(1)
    public ListNode swapNodes_1(ListNode head, int k) {
        // pass-1: calculate length of list
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        // pass-2: find kth node (1-based)
        ListNode front = head;
        for (int i = 0; i < k - 1; i++) {
            front = front.next;
        }

        // pass-3: find (n-k+1)th node (1-based)
        ListNode after = head;
        for (int i = 0; i < size - k; i++) {
            after = after.next;
        }

        // swap values
        int temp = front.val;
        front.val = after.val;;
        after.val = temp;

        return head;
    }

    // solution 2: 2-passes
    // time: O(N + (N-k-1)) >> O(N)
    // space: O(1)
    public ListNode swapNodes_2(ListNode head, int k) {
        // pass-1: find kth node & calculate the length
        int size = 0;
        ListNode front = null;
        ListNode curr = head;
        while (curr != null) {
            if (size == k - 1)
                front = curr;

            size++;
            curr = curr.next;
        }

        // pass-2: find (n-k+1)th node (1-based)
        ListNode after = head;
        for (int i = 0; i < size - k; i++) {
            after = after.next;
        }

        // swap values
        int temp = front.val;
        front.val = after.val;;
        after.val = temp;

        return head;
    }

    // solution 3: 1-pass using special two-pointer technique
    // reference to LC-19
    // time: O(N)
    // space: O(1)
    public ListNode swapNodes_3(ListNode head, int k) {
        // two pointers: p1 & p2 initially both at head
        ListNode p1 = head;
        ListNode p2 = head;

        // move p1 to (k+1)th node & record the kth node
        for (int i = 0; i < k - 1; i++) {
            p1 = p1.next;
        }
        ListNode front = p1;
        p1 = p1.next;

        // move p1 & p2 at the same speed until p1 reach to null -> p2 is pointing to (n-k-1)th node
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode after = p2;

        // swap values
        int temp = front.val;
        front.val = after.val;;
        after.val = temp;

        return head;
    }
}
