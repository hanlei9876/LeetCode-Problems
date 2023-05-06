package module_2_linked_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// constraints:
//      The number of nodes in the list is an even integer in the range [2, 105].
//      1 <= Node.val <= 105
// there are 3 solutions: 2 of them will not modify the input list, 1 will modify the intput list
// we should discuss with interviewer about if we are allowed to modify the inout list
public class LC_2130_Maximum_Twin_Sum_of_a_Linked_List {

    // solution-1: Arraylist to hold all values
    // copy values to a ArrayList, then access value from both ends
    // time: O(N + N/2) >> O(N)
    // space: O(N)
    public int pairSum(ListNode head) {
        List<Integer> list = new ArrayList<>();

        ListNode curr = head;
        while (curr != null) {
            int val = curr.val;
            list.add(val);

            curr = curr.next;
        }

        int max = 0; // ensure the value to always be updated by a pair of nodes
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            max = (sum > max) ? sum : max;

            i++;
            j--;
        }

        return max;
    }

    // solution-2: stack to hold values of the second half
    // time: O(N/2 + N/2 + N/2) >> O(N)
    // space: O(N/2) >> O(N)
    public int pairSum_2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // now slow is pointing to the last node of the first half

        slow = slow.next;
        Stack<Integer> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow.val);
            slow = slow.next;
        }

        int max = 0; // ensure the value to always be updated by a pair of nodes
        ListNode curr = head;
        while (!stack.isEmpty()) {
            int sum = curr.val + stack.pop();
            max = (sum > max) ? sum : max;

            curr = curr.next;
        }

        return max;
    }

    // solution-3: reverse the 2nd half of the list, then compare the 2 halves
    // operate on the input list
    // time: O(N/2 + N/2 + N/2) >> O(N)
    // space: O(1)
    public int pairSum_3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // now slow is pointing to the last node of the first half

        // now slow is acting as a sentinel node for the 2nd half of the list
        ListNode curr = slow.next;
        while (curr != null && curr.next != null) {
            ListNode post = curr.next;
            curr.next = post.next;
            post.next = slow.next;
            slow.next = post;
        }

        int max = 0;
        ListNode before = head;
        ListNode after = slow.next;
        while (after != null) {
            int sum = before.val + after.val;
            if (sum > max) {
                max = sum;
            }

            before = before.next;
            after = after.next;
        }

        return max;
    }

}
