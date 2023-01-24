package module_2_linked_list;

import java.util.HashSet;
import java.util.Set;

// the ListNode class has already been created in LC_141_Linked_List_Cycle
// The number of the nodes in the list is in the range [0, 10^4]
public class LC_142_Linked_List_Cycle_II {

    // solution 1: use HashSet
    // time: O(N)
    // space: O(N)
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return curr;
            }

            set.add(curr);
            curr = curr.next;
        }

        return null;
    }

    // solution 2: two-pointers (fast-slow) + two phases
    // time: O(N)
    // space: O(1)
    public ListNode detectCycle_2(ListNode head) {
        // phase-1: determine if there is a cycle. If yes, find the meeting node
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        // result 1: no cycle
        if (fast == null || fast.next == null) {
            return null;
        }

        // result 2: there is a cycle
        // phase 2: use two pointers at the same speed of one step
        ListNode p1 = head;
        ListNode p2 = fast;

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }
}
