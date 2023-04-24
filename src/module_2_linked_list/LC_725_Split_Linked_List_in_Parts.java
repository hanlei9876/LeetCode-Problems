package module_2_linked_list;

// constraints:
//  - the number of nodes in the list is in the range [0, 1000]
//  - 1 <= k <= 50
public class LC_725_Split_Linked_List_in_Parts {

    // solution-1: two passes, split the list in place
    // time: O(2N) >> O(N) - one for calculate list's length, one for operate the list
    // space: O(k) - costed by the array ans
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k]; // initial values are null

        // calculate length of list
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }

        // (code template) split the list
        int l = len / k;
        int r = len % k; // first l parts from the total k parts should have 1 more node

        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < k; k++, r--) {
            ans[i] = curr;
            int part_len = l + ( (r > 0) ? 1 : 0 );

            for (int j = 0; j < part_len; j++) {
                // move the two pointers
                prev = curr;
                curr = curr.next;
            }

            if (prev != null) // when part_len == 0, prev will stay to be the initial state - null
                prev.next = null;
        }

        return ans;
    }

    // solution-1: two passes, copy the nodes from input list to a new list
    // time: O(2N + k) >> O(N + k) - one for calculate list's length, one for operate the list, k for deleting dummy node
    // space: O(N k) - costed by the array ans
    public ListNode[] splitListToParts_2(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];

        // calculate length
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }

        // (code template) split the list
        int l = len / k;
        int r = len % k;

        ListNode curr = head;
        for (int i = 0; i < k; i++, r--) {
            ans[i] = new ListNode(0); // create a sentinel  node
            ListNode p1 = ans[i];

            int part_len = l + ( (r > 0) ? 1 : 0 );

            for (int j = 0; j < part_len; j++) {
                int val =  curr.val;
                p1.next = new ListNode(val);
                p1 = p1.next;

                curr = curr.next;
            }
        }

        // delete sentinel node for each ans[i]
        for (int i = 0; i < k; i++) {
            ans[i] = ans[i].next;
        }

        return ans;
    }


}
