package module_2_linked_list;

// constraints:
//  (1) two lists are not empty
//  (2) non-negative
//  (3) digits are in reverse order
public class LC_2_Add_Two_Numbers {

    // solution 1: pure math with 3 pointers
    // time: O(max(M, N))
    // space: O(max(M, N) + 1) = O(max(M, N)), if we count the resulting node
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {

        ListNode sentinel = new ListNode(0);

        ListNode p = sentinel;
        ListNode p1 = l1;
        ListNode p2 = l2;

        int carry = 0;

        while (p1 != null || p2 != null) {
            int sum = carry;
            if (p1 != null && p2 != null) {
                sum = sum + p1.val + p2.val;
                p1 = p1.next;
                p2 = p2.next;
            } else if (p1 != null) {
                sum = sum + p1.val;
                p1 = p1.next;
            } else {
                sum = sum + p2.val;
                p2 = p2.next;
            }

            p.next = new ListNode(sum % 10);
            carry = sum / 10;

            p = p.next;
        }

        if (carry == 1) {
            p.next = new ListNode(carry);
        }

        return sentinel.next;
    }

    // solution-2: recursion
    public ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        return null;
    }


    // Follow-up: What if the the digits in the linked list are stored in non-reversed order?
    // For example: (3 → 4→ 2) + (4 → 6 → 5)=8 → 0 → 7
    // Solution I could imagine so far:
    //  step-1: reverse both lists
    //  step-2: add the two lists into a resulting list
    //  step-3: reverse the resulting list
    // time: O((M + N) + max(M, N)) = O(M + N)
    // space: O(max(M, N) + 1) = O(max(M, N)), if we count the resulting node
}
